package SanctuaryManagement;

public class Primate implements Animal {
  /**
   * getter method for id of the animal.
   *
   * @return id of an animal.
   */
  @Override
  public int getId() {
    return 0;
  }

  /**
   * getter method for species of the animal.
   *
   * @return species of the animal.
   */
  @Override
  public Species getSpecies() {
    return null;
  }

  /**
   * getter method for sex of the animal.
   *
   * @return sex of the animal.
   */
  @Override
  public Sex getSex() {
    return null;
  }

  /**
   * getter method for weight of the animal.
   *
   * @return weight of the animal.
   */
  @Override
  public float getWeight() {
    return 0;
  }

  /**
   * getter method for favourite food of the animal.
   *
   * @return favourite food of the animal.
   */
  @Override
  public Food getFavouriteFood() {
    return null;
  }

  /**
   * getter method for needsMedicalAttention, return true if animal needs medical attention.
   *
   * @return true if animal needs medical attention else false
   */
  @Override
  public boolean getNeedsMedicalAttention() {
    return false;
  }

  /**
   * setter method for size of the animal.
   *
   * @param newSize the new size of the animal.
   */
  @Override
  public void setSize(int newSize) {

  }

  /**
   * setter method for weight of the animal.
   *
   * @param newWeight the new weight of the animal.
   */
  @Override
  public void setWeight(int newWeight) {

  }

  /**
   * setter method for favourite food of the animal.
   *
   * @param newFavouriteFood new favourite food of the animal.
   */
  @Override
  public void setFavouriteFood(Food newFavouriteFood) {

  }
}
