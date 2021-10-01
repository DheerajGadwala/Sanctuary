package sanctuarymanager;

import java.util.List;

/**
 * We can simulate sanctuaries using this class.
 * This object can add isolation capacity and more enclosures.
 * THis object can move animals from its isolation to its enclosures.
 * This object can look up species and return a list of their housing.
 */
interface Sanctuary {

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
  void addAnimal(
      String name, Genus species, Sex sex,
      double weight, double height, int age,
      Food favouriteFood, boolean needsMedicalAttention)
      throws IllegalStateException;

  /**
   * removes an animal from the housing.
   * @param id unique id of the primate to be removed.
   * @return return the string format of primate.
   */
  String removeAnimal(int id) throws IllegalArgumentException;

  /**
   * Get list of species of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Species and Housing.
   */
  String getSpeciesAndHousing();

  /**
   * Get list of names of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Names and Housing.
   */
  String getNameAndHousing();

  /**
   * Get the list of items to be purchased in a tree map.
   * @return a map with food as key and quantity as value.
   */
  String getShoppingList();

  /**
   * increases object's isolation's capacity.
   * @param capacity capacity to be added.
   */
  void addIsolationCapacity(int capacity);

  /**
   * gets the current isolation capacity.
   * @return current isolation capacity
   */
  int getTotalIsolationCapacity();

  /**
   * adds an enclosure to this object.
   * @param areaOfEnclosure area of enclosure to be added.
   * @throws IllegalArgumentException when area is non positive or when species is invalid.
   */
  void addEnclosure(int areaOfEnclosure, Genus species) throws IllegalArgumentException;

  /**
   * repurposes enclosure of given id to a different species.
   * @param enclosureId enclosure id
   * @param species new species
   * @throws IllegalArgumentException if species or enclosure id are invalid.
   */
  void repurposeEnclosure(int enclosureId, Genus species) throws IllegalArgumentException;

  /**
   * look up species in the sanctuary and return a list of where ever they are housed.
   * @param species species to be searched
   * @return string format of the list of housing which contain the given species.
   */
  String lookUpSpecies(Genus species);

  /**
   * moves an animal from isolation to enclosure.
   * @param animalId id of animal which is to be moved.
   */
  void moveAnimalToEnclosure(int animalId);

  /**
   * gives medical attention to animal with given id.
   * @param animalId id of animal to receive medical attention
   */
  void giveMedicalAttention(int animalId);

  /**
   * moves an animal from enclosure to isolation.
   * @param animalId id of animal which is to be moved.
   */
  void moveAnimalToIsolation(int animalId);

  /**
   * flags that an animal with given id requires medical attention.
   * @param animalId animal to be flagged.
   */
  void requiresMedicalAttention(int animalId);

  /**
   * get sign of the enclosure.
   * @param enclosureId id of the enclosure.
   * @return sign as a string.
   */
  String getEnclosureSign(int enclosureId);

  /**
   * <b>This will be used in testing.</b>
   * Get the details of an animal with given id from this object.
   * @param id id of the animal whose details are being requested.
   * @return string representation of animal.
   * @throws IllegalStateException when the animal does not exist in this object.
   */
  String getAnimal(int id) throws IllegalArgumentException;

  /**
   * <b>This will be used in testing.</b>
   * <b>This has to be removed in real implementation.</b>
   * returns list of all animals inside sanctuary.
   * @return list of all animals in the sanctuary.
   */
  List<Animal> getAnimals();

  /**
   * <b>This will be used in testing.</b>
   * <b>This has to be removed in real implementation.</b>
   * returns housing of the given animal.
   * @param animalId id of the animal.
   * @return housing of the animal.
   * @throws IllegalArgumentException if animal does not exist in our system.
   */
  Housing getHousing(int animalId) throws IllegalArgumentException;

  /**
   * <b>This will be used in testing.</b>
   * <b>This has to be removed in real implementation.</b>
   * @return list of ids of enclosures in this sanctuary.
   */
  List<Integer> getEnclosureIds();
}
