package SanctuaryManagement;

/**
 * Simulates an animal.
 */
public interface Animal {

  /**
   * getter method for id of the animal.
   * @return id of an animal.
   */
  public int getId();

  /**
   * getter method for species of the animal.
   * @return species of the animal.
   */
  public Species getSpecies();

  /**
   * getter method for sex of the animal.
   * @return sex of the animal.
   */
  public Sex getSex();

  /**
   * getter method for weight of the animal.
   * @return weight of the animal.
   */
  public float getWeight();

  /**
   * getter method for favourite food of the animal.
   * @return favourite food of the animal.
   */
  public Food getFavouriteFood();

  /**
   * getter method for needsMedicalAttention, return true if animal needs medical attention.
   * @return true if animal needs medical attention else false
   */
  public boolean getNeedsMedicalAttention();

  /**
   * setter method for size of the animal.
   * @param newSize the new size of the animal.
   */
  public void setSize(int newSize);

  /**
   * setter method for weight of the animal.
   * @param newWeight the new weight of the animal.
   */
  public void setWeight(int newWeight);

  /**
   * setter method for favourite food of the animal.
   * @param newFavouriteFood new favourite food of the animal.
   */
  public void setFavouriteFood(Food newFavouriteFood);
}
