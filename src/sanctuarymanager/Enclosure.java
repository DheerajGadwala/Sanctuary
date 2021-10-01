package sanctuarymanager;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Enclosures are a type of multiple housing.
 * It contains a group of animals.
 * An enclosure can house animals depending on their size and it's capacity.
 * All the animals of the group are of a single species.
 * If the enclosure is empty, animals of other species can be housed if needed.
 * This can generate a sign, which contains details of all animals housed in this.
 */
class Enclosure implements MultipleHousing {

  private static int count = 0;
  private final int id;
  private int capacity;
  private Group group;

  /**
   * Creates an enclosure object.
   * @param capacity capacity of the enclosure in area.
   */
  public Enclosure(int capacity) {
    id = count;
    count++;
    this.capacity = capacity;
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
  public Genus getCurrentSpecies() throws IllegalArgumentException {
    if (group == null || group.isEmpty()) {
      throw new IllegalArgumentException("Enclosure is empty");
    }
    return group.getSpecies();
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
   */
  public String generateSign() {
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
  public void addAnimal(Animal animal) throws IllegalStateException {
    if (group == null || group.isEmpty()) {
      group = animal.getGroup();
    }
    else if (group.getSpecies().compareTo(animal.getSpecies()) != 0) {
      Genus groupSpecies = group.getSpecies();
      Genus animalSpecies = animal.getSpecies();
      String errorMessage = String.format("This enclosure is currently housing %s species. "
                                          + "The given animal is of %s species",
                                          groupSpecies.toString(), animalSpecies.toString());
      throw new IllegalStateException(errorMessage);
    }
    Size animalSize = animal.getSize();
    int requiredCapacity = animalSize == Size.LARGE ? 10 : animalSize == Size.MEDIUM ? 5 : 1;
    if (getSpareCapacity() < requiredCapacity) {
      throw new IllegalStateException("No space for animal.");
    }
    group.addAnimal(animal);
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
