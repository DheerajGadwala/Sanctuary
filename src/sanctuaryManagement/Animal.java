package sanctuarymanagement;

/**
 * Simulates an animal.
 */
interface Animal {

  /**
   * getter method for id of the animal.
   * @return id of an animal.
   */
  public int getId();

  /**
   * getter method for sanctuarymanagement.species of the animal.
   * @return sanctuarymanagement.species of the animal.
   */
  public Genus getSpecies();

  /**
   * getter method for sex of the animal.
   * @return sex of the animal.
   */
  public Sex getSex();

  /**
   * getter for name of the animal.
   * @return name of the animal.
   */

  public String getName();

  /**
   * setter for name of the animal.
   * @param name new name of the animal.
   */
  public void setName(String name);

  /**
   * getter method for weight of the animal.
   * @return weight of the animal.
   */
  public double getWeight();

  /**
   * setter method for weight of the animal.
   * @param newWeight the new weight of the animal.
   */
  public void setWeight(double newWeight);

  /**
   * getter method for size of the animal.
   * @return size of the animal.
   */
  public Size getSize();

  /**
   * setter method for size of the animal.
   * @param height the new size of the animal.
   */
  public void setSize(double height);

  /**
   * setter method for size of the animal.
   * @param newSize the new size of the animal.
   */
  public void setSize(Size newSize);

  /**
   * getter method for age of the animal.
   * @return age of the animal.
   */
  public int getAge();

  /**
   * setter method for age of the animal.
   * @param newAge new age of the animal.
   */
  public void setAge(int newAge);

  /**
   * getter method for favourite food of the animal.
   * @return favourite food of the animal.
   */
  public Food getFavouriteFood();

  /**
   * setter method for favourite food of the animal.
   * @param newFavouriteFood new favourite food of the animal.
   */
  public void setFavouriteFood(Food newFavouriteFood);

  /**
   * getter method for needsMedicalAttention, return true if animal needs medical attention.
   * @return true if animal needs medical attention else false.
   */
  public boolean getNeedsMedicalAttention();

  /**
   * setter for needsMedicalAttention.
   * @param needsMedicalAttention give true if animal no longer needs
   *                              medical attention else give false.
   */
  public void setNeedsMedicalAttention(boolean needsMedicalAttention);

  /**
   * The type of group this animal is associated with.
   * @return an object of type group.
   */
  public Group getGroup();
}
