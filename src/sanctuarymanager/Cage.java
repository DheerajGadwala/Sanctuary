package sanctuarymanager;

/**
 * A cage is a type of housing.
 * A cage can house a single animal.
 */
class Cage implements Housing {
  private Animal animal;
  private static int counter = 0;
  private final int id;

  Cage() {
    this.id = counter;
    counter += 1;
  }

  /**
   * getter method for the id of this cage.
   * @return id of this cage.
   */
  public int getId() {
    return id;
  }

  /**
   * checks if cage is empty.
   * @return true if cage is empty, else false.
   */
  public boolean isEmpty() {
    return animal == null;
  }

  /**
   * checks if cage is occupied.
   * @return true if cage is occupied, else false.
   */
  public boolean isOccupied() {
    return animal != null;
  }

  /**
   * returns details of animal housed inside this cage.
   * @return animal object stored in this object.
   */
  public Animal getAnimal() {
    return animal;
  }

  /**
   * returns the id of the animal housed in the cage.
   * @return id of the animal housed in the cage.
   */
  public int getAnimalId() {
    return animal.getId();
  }

  public Genus getAnimalSpecies() {
    return animal.getSpecies();
  }

  @Override
  public void addAnimal(Animal animal) throws IllegalStateException {
    if (isEmpty()) {
      this.animal = animal;
      return;
    }
    throw new IllegalStateException("A cage can have only one animal.");
  }

  @Override
  public Animal removeAnimal(int id) throws IllegalStateException {
    if (isOccupied() && animal.getId() == id) {
      Animal temp = animal;
      animal = null;
      return temp;
    }
    throw new IllegalStateException("Animal of given id does not exist in this cage.");
  }

  @Override
  public int compareTo(Housing o) {
    if (o instanceof Cage) {
      int oId = ((Cage)o).getId();
      return id > oId ? 1 : id == oId ? 0 : -1;
    }
    return -1;
  }

  @Override
  public String toString() {
    String ret = String.format("Cage id:- %d", id);
    return ret;
  }
}
