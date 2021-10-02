package sanctuarymanager;

import java.util.List;

/**
 * Primate sanctuary is a type of sanctuary.
 * Primate sanctuary can only add animals which are an instance of Primate.
 * They can have enclosures that are specifically designed for the primate genus.
 * Their enclosures can be repurposed for the various species of the primate genus.
 */
public class PrimateSanctuary extends Sanctuary {
  /**
   * Primate sanctuary is a type of sanctuary that houses only primates.
   * Add animals that are instances of primates
   * Enclosures can be repurposed only to suit the primate genus.
   * @param isolationCapacity number of cages in isolation.
   * @param areaOfEnclosures area of enclosures.
   * @param speciesOfEnclosures default species for each enclosure.
   * @throws IllegalArgumentException when species do not belong to the primate genus or
   *                                   when other inputs are invalid
   */
  public PrimateSanctuary(
      int isolationCapacity,
      List<Integer> areaOfEnclosures,
      List<Genus> speciesOfEnclosures
  ) throws IllegalArgumentException {
    super(isolationCapacity, areaOfEnclosures, speciesOfEnclosures);
    for (Genus k: speciesOfEnclosures) {
      if (!(k instanceof PrimateGenus)) {
        throw new IllegalArgumentException(
            "Enclosures can house only species of the primate genus."
        );
      }
    }
  }

  private void addAnimal(Animal animal) throws IllegalArgumentException, IllegalStateException {
    if (animal instanceof Primate) {
      isolation.addAnimal(animal);
      return;
    }
    throw new IllegalArgumentException("This is a primate sanctuary. Given animal is a "
        + animal.getSpecies().getGenus());
  }

  /**
   * add animal by data.
   *
   * @param name                  name of the animal
   * @param species               species of the animal
   * @param sex                   sex of the animal
   * @param weight                weight of the animal
   * @param height                height of the animal
   * @param age                   age of the animal
   * @param favouriteFood         favourite food of the animal
   * @param needsMedicalAttention boolean if animals needs medical attention or not.
   * @throws IllegalStateException if any argument is invalid, species must be in the primate genus
   */
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

  /**
   * adds an enclosure to this object.
   * @param areaOfEnclosure area of enclosure to be added.
   * @param species species to be housed in the enclosure.
   * @throws IllegalArgumentException when area is non positive or when species is invalid.
   */
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
  void receiveAnimalFromPartnerSanctuary(Animal animal)
      throws IllegalArgumentException, IllegalStateException {
    if (animal == null) {
      throw new IllegalArgumentException("animal can not be null.");
    }
    if (!(animal instanceof Primate)) {
      throw new IllegalStateException("This is a primate sanctuary and can receive only primates.");
    }
    if (animal.getNeedsMedicalAttention()) {
      throw new IllegalArgumentException("Can not receive sick animal.");
    }
    addAnimal(animal.shallowCopy());
  }
}
