package sanctuarymanager;

/**
 * This class will be used to report species along
 * with their housing in alphabetical order.
 */
class SpeciesReportUnit implements Comparable<SpeciesReportUnit> {
  private final Genus species;
  private final Housing housing;

  /**
   * Structured data, stores species and housing.
   * @param species species object
   * @param housing housing object
   */
  public SpeciesReportUnit(Genus species, Housing housing) {
    this.species = species;
    this.housing = housing;
  }

  /**
   * getter method for the species.
   * @return species
   */
  public Genus getSpecies() {
    return species;
  }

  /**
   * getter method for housing.
   * @return housing
   */
  public Housing getHousing() {
    return housing;
  }

  @Override
  public int compareTo(SpeciesReportUnit o) {
    int ret = species.compareTo(o.getSpecies());
    if (ret == 0) {
      return housing.compareTo(o.getHousing());
    }
    else {
      return ret;
    }
  }

  @Override
  public String toString() {
    String ret = String.format("Species: %s, Housing: %s", species.toString(), housing.toString());
    return ret;
  }
}
