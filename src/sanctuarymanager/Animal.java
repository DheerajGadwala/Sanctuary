package sanctuarymanager;

/**
 * Simulates an animal.
 */
interface Animal {

  /**
   * getter method for id of the animal.
   * @return id of an animal.
   */
  int getId();

  /**
   * getter method for sanctuarymanagement.species of the animal.
   * @return sanctuarymanagement.species of the animal.
   */
  Genus getSpecies();

  /**
   * getter method for sex of the animal.
   * @return sex of the animal.
   */
  Sex getSex();

  /**
   * getter for name of the animal.
   * @return name of the animal.
   */

  String getName();

  /**
   * setter for name of the animal.
   * @param name new name of the animal.
   * @throws IllegalArgumentException when name is null or empty.
   */
  void setName(String name) throws IllegalArgumentException;

  /**
   * getter method for weight of the animal.
   * @return weight of the animal.
   */
  double getWeight();

  /**
   * setter method for weight of the animal.
   * @param newWeight the new weight of the animal.
   * @throws IllegalArgumentException when input is non positive.
   */
  void setWeight(double newWeight) throws IllegalArgumentException;

  /**
   * return height of the monkey.
   * @return height
   */
  double getHeight();

  /**
   * set new height of monkey.
   * @param newHeight new height
   * @throws IllegalArgumentException when height is non positive.
   */
  void setHeight(double newHeight) throws IllegalArgumentException;

  /**
   * getter method for size of the animal.
   * @return size of the animal.
   */
  Size getSize();

  /**
   * setter method for size of the animal.
   * @param height the new size of the animal.
   * @throws  IllegalArgumentException when input is non positive.
   */
  void setSize(double height) throws IllegalArgumentException;

  /**
   * setter method for size of the animal.
   * @param newSize the new size of the animal.
   * @throws IllegalArgumentException when input is null.
   */
  void setSize(Size newSize) throws IllegalArgumentException;

  /**
   * getter method for age of the animal.
   * @return age of the animal.
   */
  int getAge();

  /**
   * setter method for age of the animal.
   * @param newAge new age of the animal.
   * @throws IllegalArgumentException when input is negative.
   */
  void setAge(int newAge) throws IllegalArgumentException;

  /**
   * getter method for favourite food of the animal.
   * @return favourite food of the animal.
   */
  Food getFavouriteFood();

  /**
   * setter method for favourite food of the animal.
   * @param newFavouriteFood new favourite food of the animal.
   * @throws IllegalArgumentException if input is null.
   */
  void setFavouriteFood(Food newFavouriteFood) throws IllegalArgumentException;

  /**
   * getter method for needsMedicalAttention, return true if animal needs medical attention.
   * @return true if animal needs medical attention else false.
   */
  boolean getNeedsMedicalAttention();

  /**
   * setter for needsMedicalAttention.
   * @param needsMedicalAttention give true if animal no longer needs
   *                              medical attention else give false.
   */
  void setNeedsMedicalAttention(boolean needsMedicalAttention);

  /**
   * The type of group this animal is associated with.
   * @return an object of type group.
   */
  Group getGroup();

  /**
   * <b>This method is written for testing purposes.</b>
   * String representation of object without id.
   * @return string representation of this without id.
   */
  String getDetails();

  /**
   * returns a shallow copy of this animal.
   * @return shallow copy.
   * @throws IllegalArgumentException when animal is null.
   */
  Animal shallowCopy() throws IllegalArgumentException;
}
