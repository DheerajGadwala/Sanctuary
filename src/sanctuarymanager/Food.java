package sanctuarymanager;

/**
 * Available types of food.
 */
public enum Food {
  EGGS("eggs"),
  FRUITS("fruits"),
  INSECTS("insects"),
  LEAVES("leaves"),
  NUTS("nuts"),
  SEEDS("seeds"),
  TREE_SAP("tree sap");

  private final String food;

  Food(String food) {
    this.food = food;
  }

  @Override
  public String toString() {
    return this.food;
  }
}
