package sanctuarymanager;

import java.util.List;

/**
 * Represents a group of animals of the same species.
 * This can add and remove animals from itself.
 * This can be used to keep track of area requirements of the group.
 * This can be used to generate a signature containing details of all animals in the group.
 */
abstract class Group {

  protected List<Animal> animals;

  protected Group(List<Animal> animals) {
    this.animals = animals;
  }

  /**
   * Add animal to the group.
   * @param animal animal to be added.
   * @throws IllegalArgumentException when animal is of a different species.
   */
  public abstract void addAnimal(Animal animal) throws IllegalArgumentException;

  /**
   * Remove animal from the group.
   * @param id id of the animal to be removed.
   * @return animal that will be removed.
   * @throws  IllegalArgumentException when animal is not found in the group.
   */
  public Animal removeAnimal(int id) throws IllegalArgumentException {
    for (int i = 0; i < animals.size(); i++) {
      if (animals.get(i).getId() == id) {
        return animals.remove(i);
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  /**
   * Gets an details of an animal from the group. Does not remove the animal from the group.
   * @param id id of the animal whose details are to be be given.
   * @return animal object.
   * @throws IllegalArgumentException when animal is not found in the group.
   */
  public Animal getAnimal(int id) {
    for (int i = 0; i < animals.size(); i++) {
      if (animals.get(i).getId() == id) {
        return animals.get(i);
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  /**
   * checks if this group is empty.
   * @return return true if group is empty else false.
   */
  public boolean isEmpty() {
    return animals == null || animals.size() == 0;
  }

  /**
   * returns all animals inside the group.
   * @return list of animals inside the group.
   */
  public List<Animal> getAllAnimals() {
    return animals;
  }

  /**
   * Total area required by all the animals currently in the group.
   * @return total area required.
   */
  public int getCurrentAreaRequirement() {
    if (isEmpty()) {
      return 0;
    }
    int areaRequirement = 0;
    for (Animal currentAnimal: animals) {
      Size currentSize = currentAnimal.getSize();
      areaRequirement += currentSize == Size.SMALL ? 1 : currentSize == Size.MEDIUM ? 5 : 10;
    }
    return areaRequirement;
  }

  /**
   * Species of the animals inside the group.
   * @return species of animals inside the group.
   */
  public Genus getSpecies() {
    if (isEmpty()) {
      return null;
    }
    return animals.get(0).getSpecies();
  }
}
