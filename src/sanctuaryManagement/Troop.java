package sanctuarymanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Troop is a type of group specifically for primates.
 */
public class Troop implements Group {

  private List<Animal> primates;

  /**
   * Creates a troop object.
   */
  public Troop() {
    primates = new ArrayList<>();
  }

  @Override
  public void addAnimal(Animal animal) throws IllegalArgumentException {
    if (animal instanceof Primate) {
      if (isEmpty()) {
        primates.add(animal);
        return;
      }
      else if (animal.getSpecies().compareTo(getSpecies()) == 0) {
        primates.add(animal);
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

  @Override
  public Animal removeAnimal(int id) throws IllegalArgumentException {
    for (int i = 0; i < primates.size(); i++) {
      if (primates.get(i).getId() == id) {
        return primates.remove(i);
      }
    }
    throw new IllegalArgumentException("Primate not found.");
  }

  @Override
  public Animal getAnimal(int id) {
    for (int i = 0; i < primates.size(); i++) {
      if (primates.get(i).getId() == id) {
        return primates.get(i);
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  @Override
  public List<Animal> getAllAnimals() {
    return primates;
  }

  @Override
  public boolean isEmpty() {
    return primates == null || primates.size() == 0;
  }

  @Override
  public int getCurrentAreaRequirement() {
    if (primates == null) {
      return 0;
    }
    int areaRequirement = 0;
    for (Animal currentPrimate: primates) {
      Size currentSize = currentPrimate.getSize();
      areaRequirement += currentSize == Size.SMALL ? 1 : currentSize == Size.MEDIUM ? 5 : 10;
    }
    return areaRequirement;
  }

  @Override
  public Genus getSpecies() {
    if (isEmpty()) {
      return null;
    }
    return primates.get(0).getSpecies();
  }
}
