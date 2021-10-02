package sanctuarymanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * We can simulate sanctuaries using this class.
 * This object can add isolation capacity and more enclosures.
 * THis object can move animals from its isolation to its enclosures.
 * This object can look up species and return a list of their housing.
 * This object can generate alphabetically ordered lists of species along with their housing.
 * This object can generate alphabetically ordered lists of names of animals along with
 * their housing.
 * This can transfer its animals to other sanctuaries.
 */
abstract class Sanctuary {

  protected final Isolation isolation;
  protected final List<Enclosure> enclosures;

  protected Sanctuary(int isolationCapacity,
                      List<Integer> areaOfEnclosures,
                      List<Genus> speciesOfEnclosures
  )
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

  /**
   * add animal by data.
   * @param name name of the animal
   * @param species species of the animal
   * @param sex sex of the animal
   * @param weight weight of the animal
   * @param height height of the animal
   * @param age age of the animal
   * @param favouriteFood favourite food of the animal
   * @param needsMedicalAttention boolean if animals needs medical attention or not.
   * @throws IllegalStateException if any argument is invalid.
   */
  abstract void addAnimal(
      String name, Genus species, Sex sex,
      double weight, double height, int age,
      Food favouriteFood, boolean needsMedicalAttention)
      throws IllegalStateException;

  /**
   * removes an animal from the housing.
   * @param id unique id of the primate to be removed.
   * @return return the string format of primate.
   */
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

  /**
   * Get list of species of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Species and Housing.
   */
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

  /**
   * Get list of names of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Names and Housing.
   */
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

  /**
   * Get the list of items to be purchased in a tree map.
   * @return a map with food as key and quantity as value.
   */
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

  /**
   * increases object's isolation's capacity.
   * @param capacity capacity to be added.
   */
  public void addIsolationCapacity(int capacity) {
    isolation.addCapacity(capacity);
  }

  /**
   * gets the current isolation capacity.
   * @return current isolation capacity
   */
  public int getTotalIsolationCapacity() {
    return isolation.getCapacity();
  }

  /**
   * adds an enclosure to this object.
   * @param areaOfEnclosure area of enclosure to be added.
   * @throws IllegalArgumentException when area is non positive or when species is invalid.
   */
  abstract void addEnclosure(int areaOfEnclosure, Genus species) throws IllegalArgumentException;

  /**
   * repurposes enclosure of given id to a different species.
   * @param enclosureId enclosure id
   * @param species new species
   * @throws IllegalArgumentException if species or enclosure id are invalid.
   */
  abstract void repurposeEnclosure(int enclosureId, Genus species) throws IllegalArgumentException;

  /**
   * look up species in the sanctuary and return a list of where ever they are housed.
   * @param species species to be searched
   * @return list of housing which contain the given species.
   * @throws IllegalArgumentException when species not found in any of the sanctuaries.
   */
  public String lookUpSpecies(Genus species) throws IllegalStateException {
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
      throw new IllegalStateException("No " + species.toString() + " in this sanctuary.");
    }
    return speciesHousing.toString();
  }

  /**
   * moves an animal from isolation to enclosure.
   * @param animalId id of animal which is to be moved.
   */
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

  /**
   * gives medical attention to animal with given id.
   * @param animalId id of animal to receive medical attention
   */
  public void giveMedicalAttention(int animalId) {
    isolation.giveMedicalAttention(animalId);
  }

  /**
   * moves an animal from enclosure to isolation.
   * @param animalId id of animal which is to be moved.
   */
  public void moveAnimalToIsolation(int animalId)
      throws IllegalStateException {
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
    throw new IllegalStateException("Animal not found in enclosures.");
  }

  /**
   * flags that an animal with given id requires medical attention.
   * @param animalId animal to be flagged.
   */
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

  protected Enclosure getEnclosure(int id) {
    for (Enclosure k: enclosures) {
      if (k.getId() == id) {
        return k;
      }
    }
    throw new IllegalArgumentException("No enclosure with id: " + id);
  }

  /**
   * get sign of the enclosure.
   * @param enclosureId id of the enclosure.
   * @return sign as a string.
   */
  public String getEnclosureSign(int enclosureId) {
    return getEnclosure(enclosureId).generateSign();
  }

  /**
   * <b>This will be used in testing.</b>
   * Get the details of an animal with given id from this object.
   * @param id id of the animal whose details are being requested.
   * @return string representation of animal.
   * @throws IllegalStateException when the animal does not exist in this object.
   */
  public String getAnimal(int id) throws IllegalStateException {
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
    throw new IllegalStateException("Animal not found.");
  }

  /**
   * <b>This will be used in testing.</b>
   * <b>This has to be removed in real implementation.</b>
   * returns list of all animals inside sanctuary.
   * @return list of all animals in the sanctuary.
   */
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

  /**
   * Returns list of all animal ids in sanctuary.
   * @return list of animal ids.
   */
  public List<Integer> getAnimalIds() {
    List<Animal> animals = getAnimals();
    List<Integer> ret = new ArrayList<Integer>();
    for (Animal k: animals) {
      ret.add(k.getId());
    }
    return ret;
  }

  /**
   * <b>This will be used in testing.</b>
   * <b>This has to be removed in real implementation.</b>
   * returns housing of the given animal.
   * @param animalId id of the animal.
   * @return housing of the animal.
   * @throws IllegalArgumentException if animal does not exist in our system.
   */
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

  /**
   * <b>This will be used in testing.</b>
   * <b>This has to be removed in real implementation.</b>
   * @return list of ids of enclosures in this sanctuary.
   */
  public List<Integer> getEnclosureIds() {
    List<Integer> ret = new ArrayList<Integer>();
    for (Enclosure k: enclosures) {
      ret.add(k.getId());
    }
    return ret;
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
  public void transferAnimalToPartnerSanctuary(int id, Sanctuary partner)
      throws IllegalStateException, IllegalArgumentException {
    Animal animal = getAnimalObject(id);
    if (animal.getNeedsMedicalAttention()) {
      throw new IllegalStateException("Can not transfer sick animal");
    }
    partner.receiveAnimalFromPartnerSanctuary(removeAnimalGiveObject(id));
  }

  /**
   * Impose constraints on the type of animal.
   * @param animal animal object being received.
   */
  abstract void receiveAnimalFromPartnerSanctuary(Animal animal);

  @Override
  public String toString() {
    String ret = String.format(
        "Isolation capacity: %d, Number of Enclosures: %d",
        getTotalIsolationCapacity(), enclosures.size()
    );
    return ret;
  }
}
