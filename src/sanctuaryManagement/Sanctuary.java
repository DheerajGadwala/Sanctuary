package sanctuarymanagement;

import java.util.List;

/**
 * We can simulate sanctuaries using this class.
 * This object can add isolation capacity and more enclosures.
 * THis object can move animals from its isolation to its enclosures.
 * This object can look up species and return a list of their housing.
 */
public interface Sanctuary extends MultipleHousing {
  /**
   * increases object's isolation's capacity.
   * @param capacity capacity to be added.
   */
  public void addIsolationCapacity(int capacity);

  /**
   * adds an enclosure to this object.
   * @param areaOfEnclosure area of enclosure to be added.
   */
  public void addEnclosure(int areaOfEnclosure);

  /**
   * adds an enclosure to this object.
   * @param enclosure enclosure object to be added.
   */
  public void addEnclosure(Enclosure enclosure);

  /**
   * look up species in the sanctuary and return a list of where ever they are housed.
   * @param species species to be searched
   * @return list of housing which contain the given species.
   */
  public List<Housing> lookUpSpecies(Genus species);

  /**
   * moves an animal from isolation to enclosure.
   * @param animalId id of animal which is to be moved.
   */
  public void moveAnimalToEnclosure(int animalId);

}
