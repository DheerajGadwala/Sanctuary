package sanctuarymanager;

/**
 * An interface to help classes simulate housing.
 * This object can add and remove animals.
 */
interface Housing extends Comparable<Housing> {

  /**
   * adds an animal to the housing.
   * @param animal primate object to be added.
   * @throws IllegalStateException when this object can not fit more animals
   *        or is incompatible with animal
   */
  void addAnimal(Animal animal) throws IllegalStateException;

  /**
   * removes an animal from the housing.
   * @param id unique id of the primate to be removed.
   * @return return the primate object.
   * @throws IllegalArgumentException when given id does not match any animal in this object.
   */
  Animal removeAnimal(int id) throws IllegalArgumentException;

}
