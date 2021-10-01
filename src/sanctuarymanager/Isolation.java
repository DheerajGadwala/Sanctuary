package sanctuarymanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Isolation is a type of multiple housing.
 * It contains cages, which in turn contain the animals.
 * The capacity of isolation objects can be increased if needed.
 */
class Isolation implements MultipleHousing {

  private int capacity;
  private final List<Cage> cages;

  /**
   * creates an isolation object.
   * @param capacity number of cages in the isolation.
   * @throws IllegalArgumentException when capacity is less than zero.
   */
  public Isolation(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity can not be negative");
    }
    this.capacity = capacity;
    cages = new ArrayList<>();
    for (int i = 0; i < capacity; i++) {
      cages.add(new Cage());
    }
  }

  private int getSpareCapacity() {
    int currentAnimal = this.capacity;
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        currentAnimal--;
      }
    }
    return currentAnimal;
  }

  /**
   * increases the capacity of this isolation.
   * @param capacity number of cages to be added
   * @throws IllegalArgumentException when capacity is not positive
   */
  public void addCapacity(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    this.capacity += capacity;
    for (int i = 0; i < capacity; i++) {
      cages.add(new Cage());
    }
  }

  /**
   * looks up a species in this isolation and returns all housing
   * objects that contain animals of these species.
   * @param species species to be searched.
   * @return list of housing objects which have an animal of the given species.
   */
  public List<Housing> lookUpSpecies(Genus species) {
    List<Housing> ret = new ArrayList<>();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Genus currentSpecies = currentCage.getAnimalSpecies();
        if (currentSpecies.compareTo(species) == 0) {
          ret.add(currentCage);
        }
      }
    }
    Collections.sort(ret);
    return ret;
  }

  /**
   * Returns list of animals that need medical attention.
   * @return List of animals that need medical attention.
   */
  public List<Animal> getSickAnimals() {
    List<Animal> ret = new ArrayList<>();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        if (currentAnimal.getNeedsMedicalAttention()) {
          ret.add(currentAnimal);
        }
      }
    }
    return ret;
  }

  /**
   * Gives medical attention to an animal.
   * @param animalId id of animal that has received medical attention.
   * @throws IllegalArgumentException when animal is not found in isolation.
   */
  public void giveMedicalAttention(int animalId) throws IllegalArgumentException {
    try {
      Animal animal = getAnimal(animalId);
      animal.setNeedsMedicalAttention(false);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Animal does not exist in isolation.");
    }
  }

  /**
   * return true if isolation is full else returns false.
   * @return true if isolation is full.
   */
  public boolean isFull() {
    for (Cage k: cages) {
      if (k.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /**
   * <b>This method is written for testing purposes.</b>
   * returns housing of an animal.
   * @param animalId id of animal whose id is to be returned
   * @return Housing object of the animal.
   * @throws IllegalArgumentException when animal is not in isolation
   */
  public Housing getHousing(int animalId) throws IllegalArgumentException {
    for (Cage k: cages) {
      if (k.isOccupied() && k.getAnimalId() == animalId) {
        return k;
      }
    }
    throw new IllegalArgumentException("Animal not in isolation");
  }

  @Override
  public SpeciesReport getSpeciesAndHousing() {
    SpeciesReport ret = new SpeciesReport();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        ret.add(new SpeciesReportUnit(currentAnimal.getSpecies(), currentCage));
      }
    }
    ret.sort();
    return ret;
  }

  @Override
  public NameReport getNameAndHousing() {
    NameReport ret = new NameReport();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        ret.add(new NameReportUnit(currentAnimal.getName(), currentCage));
      }
    }
    ret.sort();
    return ret;
  }

  @Override
  public Map<Food, Integer> getShoppingList() {
    Map<Food, Integer> ret = new TreeMap<>();
    for (Food f: Food.values()) {
      ret.put(f, 0);
    }
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        Size size = currentAnimal.getSize();
        Food favFood = currentAnimal.getFavouriteFood();
        int foodRequirement = size == Size.LARGE ? 500 : size == Size.MEDIUM ? 250 : 100;
        ret.put(favFood, ret.get(favFood) + foodRequirement);
      }
    }
    return ret;
  }

  @Override
  public void setNeedsMedicalAttention(int animalId) throws IllegalArgumentException {
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied() && currentCage.getAnimalId() == animalId) {
        Animal currentAnimal = currentCage.getAnimal();
        currentAnimal.setNeedsMedicalAttention(true);
        return;
      }
    }
    throw new IllegalArgumentException("animal not found in Isolation");
  }

  @Override
  public void addAnimal(Animal animal) throws IllegalStateException {
    if (getSpareCapacity() == 0) {
      throw new IllegalStateException("No spare capacity in isolation");
    }
    for (Cage currentCage: cages) {
      if (currentCage.isEmpty()) {
        currentCage.addAnimal(animal);
        break;
      }
    }
  }

  @Override
  public Animal removeAnimal(int id) throws IllegalArgumentException {
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        if (currentCage.getAnimalId() == id) {
          Animal animal = currentCage.getAnimal();
          if (animal.getNeedsMedicalAttention()) {
            String errorMessage = String.format("Can not remove animal %d from Isolation "
                                                + "because it requires medical attention.", id);
            throw new IllegalArgumentException(errorMessage);
          }
          return currentCage.removeAnimal(id);
        }
      }
    }
    String errorMessage = String.format("Animal %d was not found in isolation.", id);
    throw new IllegalArgumentException(errorMessage);
  }

  @Override
  public Animal getAnimal(int id) throws IllegalArgumentException {
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        if (currentCage.getAnimalId() == id) {
          return currentCage.getAnimal();
        }
      }
    }
    throw new IllegalArgumentException("Animal not found in isolation");
  }

  @Override
  public List<Animal> getAnimals() {
    List<Animal> animals = new ArrayList<>();
    for (Cage k: cages) {
      if (k.isOccupied()) {
        animals.add(k.getAnimal());
      }
    }
    return animals;
  }

  @Override
  public int compareTo(Housing o) {
    if (o instanceof Enclosure) {
      return -1;
    }
    else if (o instanceof Isolation) {
      return 0;
    }
    else {
      return 1;
    }
  }

  @Override
  public String toString() {
    String ret = String.format("Isolation, capacity: %d", capacity);
    return ret;
  }

  /**
   * returns capacity of this isolation object.
   * @return capacity
   */
  public int getCapacity() {
    return capacity;
  }
}
