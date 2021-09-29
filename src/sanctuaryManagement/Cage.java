package sanctuarymanagement;

import javax.naming.LimitExceededException;

/**
 * A cage is a type of housing.
 * A cage can house a single animal.
 */
public class Cage implements Housing {
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

  @Override
  public void addAnimal(Animal animal) throws LimitExceededException {
    if (isEmpty()) {
      this.animal = animal;
    }
    throw new LimitExceededException("A cage can have only one animal.");
  }

  @Override
  public Animal removeAnimal(int id) throws IllegalStateException {
    if (isOccupied() && animal.getId() == id) {
      Animal temp = animal;
      animal = null;
      return temp;
    }
    throw new IllegalStateException();
  }

  @Override
  public int compareTo(Housing o) {
    if (o instanceof Cage) {
      if (id > ((Cage)o).getId()) {
        return 1;
      }
      else {
        return -1;
      }
    }
    else {
      return -1;
    }
  }
}
