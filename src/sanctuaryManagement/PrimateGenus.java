package sanctuarymanagement;

/**
 * Primate genus is the collection of all primate species.
 * We can compare species alphabetically.
 */
abstract class PrimateGenus implements Genus {

  private final String species;
  private final String genus = "Primates";

  PrimateGenus(String species) {
    this.species = species;
  }

  public String getGenus() {
    return genus;
  }

  @Override
  public String toString() {
    return species;
  }

  @Override
  public int compareTo(Genus o) {
    if (o instanceof PrimateGenus) {
      return this.toString().compareTo(o.toString());
    }
    return this.getGenus().compareTo(o.getGenus());
  }
}
