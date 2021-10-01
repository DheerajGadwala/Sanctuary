package sanctuarymanager;

/**
 * types of sex.
 */
public enum Sex {
  MALE("male"),
  FEMALE("female");

  private final String sex;

  Sex(String sex) {
    this.sex = sex;
  }

  @Override
  public String toString() {
    return this.sex;
  }
}
