package sanctuarymanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simulates the jungle friends primate sanctuary.
 */
public class JungleFriendsPrimateSanctuary implements Sanctuary {

  private Isolation isolation;
  private List<Enclosure> enclosures;

  /**
   * creates a Jungle friends primate sanctuary object.
   * @param isolationCapacity Capacity of isolation.
   * @param areaOfEnclosures Capacities of enclosures.
   */
  public JungleFriendsPrimateSanctuary(int isolationCapacity, List<Integer> areaOfEnclosures) {
    isolation = new Isolation(isolationCapacity);
    enclosures = new ArrayList<>();
    for (int k: areaOfEnclosures) {
      addEnclosure(k);
    }
  }

  @Override
  public void addIsolationCapacity(int capacity) {
    isolation.addCapacity(capacity);
  }

  @Override
  public void addEnclosure(int areaOfEnclosure) {
    if (areaOfEnclosure > 0) {
      enclosures.add(new Enclosure(areaOfEnclosure));
      return;
    }
    throw new IllegalArgumentException("area of Enclosure can not be non-positive");
  }

  @Override
  public void addEnclosure(Enclosure enclosure) {
    enclosures.add(enclosure);
  }

  @Override
  public List<Housing> lookUpSpecies(Genus species) {
    List<Housing> speciesHousing = isolation.lookUpSpecies(species);
    for (Enclosure k: enclosures) {
      if (k.getCurrentSpecies().compareTo(species) == 0) {
        speciesHousing.add(k);
      }
    }
    return speciesHousing;
  }

  @Override
  public void moveAnimalToEnclosure(int animalId) {
    Animal animal;
    try {
      animal = isolation.removeAnimal(animalId);
    }
    catch (IllegalArgumentException e1) {
      throw new IllegalArgumentException("Animal not found in isolation.");
    }
    for (Enclosure k: enclosures) {
      try {
        k.addAnimal(animal);
        return;
      }
      catch (IllegalStateException e2) {
      }
    }
    throw new IllegalArgumentException("No capacity in Enclosure.");
  }

  @Override
  public Animal getAnimal(int id) throws IllegalArgumentException {
    Animal ret;
    try {
      ret = isolation.getAnimal(id);
      return ret;
    }
    catch (IllegalArgumentException e1) {
      for (Enclosure k: enclosures) {
        try {
          ret = k.getAnimal(id);
          return ret;
        }
        catch (IllegalArgumentException e2) {
        }
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  @Override
  public List<SpeciesReportUnit> getSpeciesAndHousing() {
    List<SpeciesReportUnit> ret = isolation.getSpeciesAndHousing();
    for (Enclosure k: enclosures) {
      if (k.isOccupied()) {
        ret.addAll(k.getSpeciesAndHousing());
      }
    }
    return ret;
  }

  @Override
  public List<NameReportUnit> getNameAndHousing() {
    List<NameReportUnit> ret = isolation.getNameAndHousing();
    for (Enclosure k: enclosures) {
      if (k.isOccupied()) {
        ret.addAll(k.getNameAndHousing());
      }
    }
    return ret;
  }

  @Override
  public Map<Food, Integer> getShoppingList() {
    Map<Food, Integer> ret = isolation.getShoppingList();
    for (Enclosure k: enclosures) {
      if (k.isOccupied()) {
        Map<Food, Integer> temp = k.getShoppingList();
        for (Food f: Food.values()) {
          ret.put(f, ret.get(f) + temp.get(f));
        }
      }
    }
    return ret;
  }

  @Override
  public void addAnimal(Animal animal) throws IllegalStateException {
    isolation.addAnimal(animal);
  }

  @Override
  public Animal removeAnimal(int id) throws IllegalStateException {
    Animal ret;
    try {
      ret = isolation.removeAnimal(id);
      return ret;
    }
    catch (IllegalArgumentException e1) {
      for (Enclosure k: enclosures) {
        try {
          ret = k.removeAnimal(id);
          return ret;
        }
        catch (IllegalArgumentException e2) {
        }
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  @Override
  public int compareTo(Housing o) {
    return 0;
  }
}
