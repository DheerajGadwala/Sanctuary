package sanctuarymanagement;

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
public class Isolation implements MultipleHousing {

  private int capacity;
  private List<Cage> cages;

  /**
   * creates an isolation object.
   * @param capacity number of cages in the isolation.
   */
  public Isolation(int capacity) {
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

  @Override
  public List<SpeciesReportUnit> getSpeciesAndHousing() {
    List<SpeciesReportUnit> ret = new ArrayList<>();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        ret.add(new SpeciesReportUnit(currentAnimal.getSpecies(), currentCage));
      }
    }
    Collections.sort(ret);
    return ret;
  }

  @Override
  public List<NameReportUnit> getNameAndHousing() {
    List<NameReportUnit> ret = new ArrayList<>();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        ret.add(new NameReportUnit(currentAnimal.getName(), currentCage));
      }
    }
    Collections.sort(ret);
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
        ret.put(favFood, foodRequirement);
      }
    }
    return ret;
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
          return currentCage.removeAnimal(id);
        }
      }
    }
    throw new IllegalArgumentException("Animal was not found in isolation.");
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
}
