package sanctuarymanagement;

import javax.naming.LimitExceededException;

/**
 * An interface to help classes simulate housing.
 */
interface Housing extends Comparable<Housing> {
  /**
   * adds a primate to the housing.
   * @param animal primate object to be added.
   */
  public void addAnimal(Animal animal) throws LimitExceededException;

  /**
   * removes a primate from the housing.
   * @param id unique id of the primate to be removed.
   * @return return the primate object.
   */
  public Animal removeAnimal(int id) throws IllegalStateException;
}
