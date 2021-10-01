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
   * @throws IllegalArgumentException if isolation capacity is non positive
   *        or areas and species are unbalanced.
   */
  public JungleFriendsPrimateSanctuary(int isolationCapacity,
                                       List<Integer> areaOfEnclosures,
                                       List<Genus> speciesOfEnclosures)
      throws IllegalArgumentException {
    isolation = new Isolation(isolationCapacity);
    enclosures = new ArrayList<>();
    if (areaOfEnclosures.size() != speciesOfEnclosures.size()) {
      throw new IllegalArgumentException("Invalid input");
    }
    for (int i = 0; i < areaOfEnclosures.size(); i++) {
      enclosures.add(new Enclosure(areaOfEnclosures.get(i), speciesOfEnclosures.get(i)));
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
  public void addEnclosure(int areaOfEnclosure, Genus species) {
    if (areaOfEnclosure <= 0) {
      throw new IllegalArgumentException("area of Enclosure can not be non-positive");
    }
    if (!(species instanceof PrimateGenus)) {
      throw new IllegalArgumentException("This is a primate sanctuary, given species "
          + "does not belong to the primate genus.");
    }
    enclosures.add(new Enclosure(areaOfEnclosure, species));
  }

  /**
   * repurposes enclosure of given id to a different species.
   *
   * @param enclosureId enclosure id
   * @param species     new species
   * @throws IllegalArgumentException if species or enclosure id are invalid.
   */
  @Override
  public void repurposeEnclosure(int enclosureId, Genus species)
      throws IllegalArgumentException, IllegalStateException {
    for (Enclosure k: enclosures) {
      if (k.getId() == enclosureId) {
        k.repurposeEnclosure(species);
        return;
      }
    }
    throw new IllegalArgumentException("No enclosure with given id.");
  }

  @Override
  public String lookUpSpecies(Genus species) throws IllegalArgumentException {
    List<Housing> speciesHousing = isolation.lookUpSpecies(species);
    for (Enclosure k: enclosures) {
      try {
        if (k.getCurrentSpecies().compareTo(species) == 0 && k.isOccupied()) {
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
    return speciesHousing.toString();
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
    throw new IllegalStateException("No compatible enclosure or not enough space.");
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
    try {
      isolation.setNeedsMedicalAttention(animalId);
      return;
    }
    catch (IllegalArgumentException e1) {
      for (Enclosure k: enclosures) {
        try {
          k.setNeedsMedicalAttention(animalId);
          return;
        }
        catch (IllegalArgumentException e2) {
          continue;
        }
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

  private void addAnimal(Animal animal) throws IllegalArgumentException {
    if (animal instanceof Primate) {
      isolation.addAnimal(animal);
      return;
    }
    throw new IllegalArgumentException("This is a primate sanctuary. Given animal is a "
        +animal.getSpecies().getGenus());
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
   */
  public Animal transferAnimalToPartnerSanctuary(int id) throws IllegalStateException, IllegalArgumentException {
    Animal animal = getAnimalObject(id);
    if (animal.getNeedsMedicalAttention()) {
      throw new IllegalStateException("Can not transfer sick animal");
    }
    return removeAnimalGiveObject(id);
  }

  /**
   * this method can be used by partners to transfer animals to us.
   * @param animal animal that is being transferred.
   */
  public void receiveAnimalFromPartnerSanctuary(Animal animal) {
    if (animal == null) {
      throw new IllegalArgumentException("animal can not be null.");
    }
    if (animal.getNeedsMedicalAttention()) {
      throw new IllegalArgumentException("Can not receive sick animal.");
    }
    addAnimal(animal.shallowCopy());
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
