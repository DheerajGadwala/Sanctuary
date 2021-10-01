package sanctuarymanager;

import java.util.ArrayList;

/**
 * Troop is a type of group specifically for primates.
 */
class Troop extends Group {

  /**
   * Creates a troop object.
   */
  public Troop() {
    super(new ArrayList<Animal>());
  }

  @Override
  public void addAnimal(Animal animal) throws IllegalArgumentException {
    if (animal instanceof Primate) {
      if (isEmpty()) {
        animals.add(animal);
        return;
      }
      else if (animal.getSpecies().compareTo(getSpecies()) == 0) {
        animals.add(animal);
        return;
      }
      String errorMessage = String.format("This troop contains primates of %s "
                                          + "species, you gave a primate of %s species",
                                          getSpecies(), animal.getSpecies());
      throw new IllegalArgumentException(errorMessage);
    }
    else if (animal != null) {
      throw new IllegalArgumentException("Only animals of primate genus can be added to troops.");
    }
    else {
      throw new IllegalArgumentException("Arguments can not be empty.");
    }
  }
}
