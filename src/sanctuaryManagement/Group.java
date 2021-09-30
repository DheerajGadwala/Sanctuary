package sanctuarymanagement;

import java.util.List;

/**
 * Represents a group of animals of the same species.
 * This can add and remove animals from itself.
 * This can be used to keep track of area requirements of the group.
 * This can be used to generate a signature containing details of all animals in the group.
 */
public interface Group {

  /**
   * Add animal to the group.
   * @param animal animal to be added.
   * @throws IllegalArgumentException when animal is of a different species.
   */
  public void addAnimal(Animal animal) throws IllegalArgumentException;

  /**
   * Remove animal from the group.
   * @param id id of the animal to be removed.
   * @return animal that will be removed.
   * @throws  IllegalArgumentException when animal is not found in the group.
   */
  public Animal removeAnimal(int id) throws IllegalArgumentException;

  /**
   * Gets an details of an animal from the group. Does not remove the animal from the group.
   * @param id id of the animal whose details are to be be given.
   * @return animal object.
   * @throws IllegalArgumentException when animal is not found in the group.
   */
  public Animal getAnimal(int id) throws IllegalArgumentException;

  /**
   * checks if this group is empty.
   * @return return true if group is empty else false.
   */
  public boolean isEmpty();

  /**
   * returns all animals inside the group.
   * @return list of animals inside the group.
   */
  public List<Animal> getAllAnimals();

  /**
   * Total area required by all the animals currently in the group.
   * @return total area required.
   */
  public int getCurrentAreaRequirement();

  /**
   * Species of the animals inside the group.
   * @return species of animals inside the group.
   */
  public Genus getSpecies();
}
