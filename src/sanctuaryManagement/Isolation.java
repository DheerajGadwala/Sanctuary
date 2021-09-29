package sanctuarymanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import javax.naming.LimitExceededException;

/**
 * Isolation is a type of multiple housing.
 * It contains cages, which in turn contain the animals.
 * The capacity of isolation objects can be increases if needed.
 */
public class Isolation implements MultipleHousing {

  private int capacity;
  private ArrayList<Cage> cages;

  /**
   * creates an isolation object.
   * @param capacity number of cages in the isolation.
   */
  public Isolation(int capacity) {
    this.capacity = capacity;
    for (int i = 0; i < capacity; i++) {
      cages.add(new Cage());
    }
  }

  private int getSpareCapacity() {
    int currentAnimal = this.capacity;
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        currentAnimal--;
      }
    }
    return currentAnimal;
  }

  /**
   * increases the capacity of this isolation.
   * @param capacity number of cages to be added
   * @throws IllegalArgumentException when capacity is not positive
   */
  public void addCapacity(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    this.capacity += capacity;
    for (int i = 0; i < capacity; i++) {
      cages.add(new Cage());
    }
  }

  @Override
  public ArrayList<SpeciesReportUnit> getSpeciesAndHousing() {
    ArrayList<SpeciesReportUnit> ret = new ArrayList<SpeciesReportUnit>();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        ret.add(new SpeciesReportUnit(currentAnimal.getSpecies(), currentCage));
      }
    }
    Collections.sort(ret);
    return ret;
  }

  @Override
  public ArrayList<NameReportUnit> getSNameAndHousing() {
    ArrayList<NameReportUnit> ret = new ArrayList<NameReportUnit>();
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        ret.add(new NameReportUnit(currentAnimal.getName(), currentCage));
      }
    }
    Collections.sort(ret);
    return ret;
  }

  @Override
  public TreeMap<Food, Integer> getShoppingList() {
    TreeMap<Food, Integer> ret = new TreeMap<Food, Integer>();
    for (Food f: Food.values()) {
      ret.put(f, 0);
    }
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        Size size = currentAnimal.getSize();
        Food favFood = currentAnimal.getFavouriteFood();
        if (size == Size.LARGE) {
          ret.put(favFood, ret.get(favFood) + 500);
        }
        else if (size == Size.MEDIUM) {
          ret.put(favFood, ret.get(favFood) + 250);
        }
        else {
          ret.put(favFood, ret.get(favFood) + 100);
        }
      }
    }
    return ret;
  }

  @Override
  public void addAnimal(Animal animal) throws LimitExceededException {
    if (getSpareCapacity() == 0) {
      throw new LimitExceededException();
    }
  }

  @Override
  public Animal removeAnimal(int id) throws IllegalStateException {
    for (Cage currentCage: cages) {
      if (currentCage.isOccupied()) {
        Animal currentAnimal = currentCage.getAnimal();
        if (currentAnimal.getId() == id) {
          return currentCage.removeAnimal(id);
        }
      }
    }
    throw new IllegalStateException("Animal was not found in isolation.");
  }

  @Override
  public int compareTo(Housing o) {
    if (true) {
      return -1;
    }
    else if (o instanceof Isolation) {
      return 0;
    }
    else if (o instanceof Cage) {
      return 1;
    }
    else {
      return 0;
    }
  }
}
