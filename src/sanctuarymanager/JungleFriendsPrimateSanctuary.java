package sanctuarymanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simulates the jungle friends primate sanctuary.
 * Can add and remove animals.
 * Has an isolation and multiple enclosures.
 * This can generate various reports and lists.
 */
public class JungleFriendsPrimateSanctuary implements Sanctuary {

  private final Isolation isolation;
  private final List<Enclosure> enclosures;

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
  public int getTotalIsolationCapacity() {
    return isolation.getCapacity();
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
  public List<Housing> lookUpSpecies(Genus species) throws IllegalArgumentException {
    List<Housing> speciesHousing = isolation.lookUpSpecies(species);
    for (Enclosure k: enclosures) {
      try {
        if (k.getCurrentSpecies().compareTo(species) == 0) {
          speciesHousing.add(k);
        }
      }
      catch (IllegalArgumentException e) {
        continue;
      }
    }
    if (speciesHousing.size() == 0) {
      throw new IllegalArgumentException("No " + species.toString() + " in this sanctuary.");
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
      throw new IllegalArgumentException(e1.getMessage());
    }
    for (Enclosure k: enclosures) {
      try {
        k.addAnimal(animal);
        return;
      }
      catch (IllegalStateException e2) {
        continue;
      }
    }
    throw new IllegalStateException("No capacity in Enclosure.");
  }

  @Override
  public void giveMedicalAttention(int animalId) {
    isolation.giveMedicalAttention(animalId);
  }

  @Override
  public void moveAnimalToIsolation(int animalId)
      throws IllegalArgumentException, IllegalStateException {
    for (Enclosure k: enclosures) {
      try {
        Animal animal = k.getAnimal(animalId);
        if (isolation.isFull()) {
          throw new IllegalStateException("Isolation is full, can not move animal.");
        }
        isolation.addAnimal(k.removeAnimal(animalId));
        return;
      }
      catch (IllegalArgumentException e2) {
        continue;
      }
    }
    throw new IllegalArgumentException("Animal not found in enclosures.");
  }

  @Override
  public void requiresMedicalAttention(int animalId) {
    for (Enclosure k: enclosures) {
      try {
        Animal animal = k.getAnimal(animalId);
        animal.setNeedsMedicalAttention(true);
        return;
      }
      catch (IllegalArgumentException e2) {
        continue;
      }
    }
    throw new IllegalArgumentException("Animal not found in enclosures.");
  }

  private Enclosure getEnclosure(int id) {
    for (Enclosure k: enclosures) {
      if (k.getId() == id) {
        return k;
      }
    }
    throw new IllegalArgumentException("No enclosure with id: " + id);
  }

  @Override
  public String getEnclosureSign(int enclosureId) {
    return getEnclosure(enclosureId).generateSign();
  }

  @Override
  public String getAnimal(int id) throws IllegalArgumentException {
    Animal ret;
    try {
      ret = isolation.getAnimal(id);
      return ret.toString();
    }
    catch (IllegalArgumentException e1) {
      for (Enclosure k: enclosures) {
        try {
          ret = k.getAnimal(id);
          return ret.toString();
        }
        catch (IllegalArgumentException e2) {
          continue;
        }
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  private Animal getAnimalObject(int id) throws IllegalArgumentException {
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
          continue;
        }
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  @Override
  public List<Animal> getAnimals() {
    List<Animal> ret = isolation.getAnimals();
    for (Enclosure k: enclosures) {
      try {
        ret.addAll(k.getAnimals());
      }
      catch (NullPointerException e) {
        continue;
      }
    }
    return ret;
  }

  @Override
  public Housing getHousing(int animalId) throws IllegalArgumentException {
    Housing housing;
    try {
      housing = isolation.getHousing(animalId);
      return housing;
    }
    catch (IllegalArgumentException e1) {
      for (Enclosure k: enclosures) {
        try {
          k.getAnimal(animalId);
          housing = k;
          return housing;
        }
        catch (IllegalArgumentException e2) {
          continue;
        }
      }
    }
    throw new IllegalArgumentException("Animal not in sanctuary.");
  }

  @Override
  public List<Integer> getEnclosureIds() {
    List<Integer> ret = new ArrayList<Integer>();
    for (Enclosure k: enclosures) {
      ret.add(k.getId());
    }
    return ret;
  }

  @Override
  public String getSpeciesAndHousing() {
    SpeciesReport ret = isolation.getSpeciesAndHousing();
    for (Enclosure k: enclosures) {
      if (k.isOccupied()) {
        ret.addAll(k.getSpeciesAndHousing());
      }
    }
    ret.sort();
    return ret.toString();
  }

  @Override
  public String getNameAndHousing() {
    NameReport ret = isolation.getNameAndHousing();
    for (Enclosure k: enclosures) {
      if (k.isOccupied()) {
        ret.addAll(k.getNameAndHousing());
      }
    }
    ret.sort();
    return ret.toString();
  }

  @Override
  public String getShoppingList() {
    Map<Food, Integer> ret = isolation.getShoppingList();
    for (Enclosure k: enclosures) {
      if (k.isOccupied()) {
        Map<Food, Integer> temp = k.getShoppingList();
        for (Food f: Food.values()) {
          ret.put(f, ret.get(f) + temp.get(f));
        }
      }
    }
    return ret.toString();
  }

  @Override
  public void addAnimal(Animal animal) throws IllegalArgumentException {
    isolation.addAnimal(animal);
  }

  @Override
  public void addAnimal(String name, Genus species, Sex sex,
                        double weight, double height, int age,
                        Food favouriteFood, boolean needsMedicalAttention)
      throws IllegalStateException {
    if (!(species instanceof PrimateGenus)) {
      String errorMessage;
      errorMessage = "This is a primate sanctuary, can not add "
          + name + " as it is a " + species.getGenus() + "\n";
      throw new IllegalArgumentException(errorMessage);
    }
    isolation.addAnimal(
        new Primate(name, (PrimateGenus) species,
            sex, weight, height, age,
            favouriteFood, needsMedicalAttention)
    );
  }

  @Override
  public String removeAnimal(int id) throws IllegalStateException {
    Animal ret;
    try {
      ret = isolation.removeAnimal(id);
      return ret.toString();
    }
    catch (IllegalArgumentException e1) {
      for (Enclosure k: enclosures) {
        try {
          ret = k.removeAnimal(id);
          return ret.toString();
        }
        catch (IllegalArgumentException e2) {
          continue;
        }
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  private Animal removeAnimalGiveObject(int id) throws IllegalStateException {
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
          continue;
        }
      }
    }
    throw new IllegalArgumentException("Animal not found.");
  }

  /**
   * Transfer animal to a partner sanctuary.
   * @param id id of the animal to be transferred
   * @param partner partner sanctuary object.
   */
  public void transferAnimalToPartnerSanctuary(int id, Sanctuary partner) {
    Animal animal = getAnimalObject(id);
    if (animal.getNeedsMedicalAttention()) {
      throw new IllegalArgumentException("Can not transfer sick animal");
    }
    partner.addAnimal(removeAnimalGiveObject(id));
  }

  /**
   * this method can be used by partners to transfer animals to us.
   * @param animal animal that is being transferred.
   */
  public void receiveAnimalFromPartnerSanctuary(Animal animal) {
    if (animal.getNeedsMedicalAttention()) {
      throw new IllegalArgumentException("Can not receive sick animal.");
    }
    addAnimal(animal);
  }

  @Override
  public String toString() {
    String ret = String.format(
        "Isolation capacity: %d, Number of Enclosures: %d",
        getTotalIsolationCapacity(), enclosures.size()
    );
    return ret;
  }
}
