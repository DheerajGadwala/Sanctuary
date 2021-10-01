package sanctuarymanager;

/**
 * Different sizes of animals.
 */
enum Size {
  SMALL("small"),
  MEDIUM("medium"),
  LARGE("large");

  private final String size;

  Size(String size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return this.size;
  }
}
