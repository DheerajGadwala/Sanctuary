package sanctuarymanager;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Enclosures are a type of multiple housing.
 * It contains a group of animals.
 * An enclosure can house animals depending on their size and it's capacity.
 * All the animals of the group are of a single species.
 * If the enclosure is empty, they can be repurposed to house other animals.
 * This can generate a sign, which contains details of all animals housed in this.
 */
class Enclosure implements MultipleHousing {

  private static int count = 0;
  private final int id;
  private int capacity;
  private Group group;
  private Genus species;

  /**
   * Creates an enclosure object.
   * @param capacity capacity of the enclosure in area.
   * @throws IllegalArgumentException when capacity is non positive or species is null
   */
  public Enclosure(int capacity, Genus species) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity ca not be non negative.");
    }
    if (species == null) {
      throw new IllegalArgumentException("species can not be null.");
    }
    id = count;
    count++;
    this.capacity = capacity;
    this.species = species;
  }

  /**
   * returns true if this object does not house an animals.
   * @return returns true if empty else returns false.
   */
  public boolean isEmpty() {
    return getSpareCapacity() == capacity;
  }

  /**
   * returns true if enclosure contains at least one animal.
   * @return return true if not empty else false.
   */
  public boolean isOccupied() {
    return getSpareCapacity() != capacity;
  }

  /**
   * return the species currently being housed in the enclosure.
   * @return the current species
   */
  public Genus getCurrentSpecies() {
    return species;
  }

  /**
   * getter method for id.
   * @return id.
   */
  public int getId() {
    return id;
  }

  /**
   * generates a sign, which contains details of all animals housed in this.
   * @return Sign of this enclosure in string format.
   * @throws IllegalStateException when there are no animals in the enclosure.
   */
  public String generateSign() throws IllegalStateException {
    Sign sign = new Sign();
    if (isEmpty()) {
      throw new IllegalStateException("Can generate sign when there are no animals in enclosure.");
    }
    List<Animal> animals  = group.getAllAnimals();
    for (Animal k: animals) {
      sign.add(new SignUnit(k.getName(), k.getSex(), k.getFavouriteFood()));
    }
    sign.sort();
    return sign.toString();
  }

  /**
   * Repurpose enclosure to a different species.
   * @param species new species
   * @throws IllegalStateException if enclosure is not empty.
   */
  public void repurposeEnclosure(Genus species) throws IllegalStateException {
    if (isEmpty()) {
      this.species = species;
      return;
    }
    throw new IllegalStateException("Enclosure can be repurposed only when empty.");
  }

  private int getSpareCapacity() {
    if (group == null) {
      return capacity;
    }
    return capacity - group.getCurrentAreaRequirement();
  }

  @Override
  public Animal getAnimal(int id) throws IllegalArgumentException {
    if (isEmpty()) {
      throw new IllegalArgumentException("Enclosure is empty.");
    }
    return group.getAnimal(id);
  }

  @Override
  public List<Animal> getAnimals() {
    return group.getAllAnimals();
  }

  @Override
  public SpeciesReport getSpeciesAndHousing() {
    List<Animal> temp = group.getAllAnimals();
    SpeciesReport ret = new SpeciesReport();
    for (Animal k: temp) {
      ret.add(new SpeciesReportUnit(getCurrentSpecies(), this));
    }
    ret.sort();
    return ret;
  }

  @Override
  public NameReport getNameAndHousing() {
    List<Animal> temp = group.getAllAnimals();
    NameReport ret = new NameReport();
    for (Animal k: temp) {
      ret.add(new NameReportUnit(k.getName(), this));
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
    List<Animal> temp = group.getAllAnimals();
    for (Animal currentAnimal: temp) {
      Size size = currentAnimal.getSize();
      Food favFood = currentAnimal.getFavouriteFood();
      int foodRequirement = size == Size.LARGE ? 500 : size == Size.MEDIUM ? 250 : 100;
      ret.put(favFood, ret.get(favFood) + foodRequirement);
    }
    return ret;
  }

  @Override
  public void setNeedsMedicalAttention(int animalId) throws IllegalArgumentException {
    getAnimal(animalId).setNeedsMedicalAttention(true);
  }

  @Override
  public void addAnimal(Animal animal) throws IllegalStateException {
    if (species.compareTo(animal.getSpecies()) == 0) {
      if (group == null) {
        group = animal.getGroup();
      }
      Size animalSize = animal.getSize();
      int requiredCapacity = animalSize == Size.LARGE ? 10 : animalSize == Size.MEDIUM ? 5 : 1;
      if (getSpareCapacity() >= requiredCapacity) {
        group.addAnimal(animal);
        return;
      }
      throw new IllegalStateException("No space for animal.");
    }
    throw new IllegalStateException("Animal and Enclosure are not compatible.");
  }

  @Override
  public Animal removeAnimal(int id) throws IllegalArgumentException {
    if (isEmpty()) {
      throw new IllegalArgumentException("Enclosure is empty.");
    }
    return group.removeAnimal(id);
  }

  @Override
  public int compareTo(Housing o) {
    if (o instanceof Enclosure) {
      int oId = ((Enclosure)o).getId();
      return id > oId ? 1 : id == oId ? 0 : -1;
    }
    return 1;
  }

  @Override
  public String toString() {
    String ret = String.format("Enclosure id:- %d", id);
    return ret;
  }
}
