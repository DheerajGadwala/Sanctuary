package sanctuarymanagement;

/**
 * This class is used to store a single unit of sign produced by enclosures.
 */
public class SignUnit implements Comparable<SignUnit> {
  private final String name;
  private final Sex sex;
  private final Food favouriteFood;

  /**
   * constructor to create a signUnit object.
   * @param name name of the animal.
   * @param sex sex of the animal.
   * @param favouriteFood favourite food of the animal.
   */
  public SignUnit(String name, Sex sex, Food favouriteFood) {
    this.name = name;
    this.sex = sex;
    this.favouriteFood = favouriteFood;
  }

  /**
   * getter method for name.
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * getter method for sex.
   * @return sex
   */
  public Sex getSex() {
    return sex;
  }

  /**
   * getter method for favourite food.
   * @return favourite food
   */
  public Food getFavouriteFood() {
    return favouriteFood;
  }

  @Override
  public int compareTo(SignUnit o) {
    int nameC = name.compareTo(o.getName());
    if (nameC == 0) {
      return favouriteFood.compareTo(o.getFavouriteFood());
    }
    return nameC;
  }

  @Override
  public String toString() {
    String ret = String.format("Name: %s, Sex: %s, Favourite food: %s",
                              name, sex.toString(), favouriteFood.toString());
    return ret;
  }
}
