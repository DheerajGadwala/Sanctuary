package sanctuarymanager;

/**
 * Primate genus is the collection of all primate species.
 * We can compare species alphabetically.
 */
abstract class PrimateGenus implements Genus {

  private final String species;
  private final String genus = "Primate";

  PrimateGenus(String species) {
    this.species = species;
  }

  @Override
  public String getGenus() {
    return genus;
  }

  @Override
  public String toString() {
    String ret = String.format("%s", species);
    return ret;
  }

  @Override
  public int compareTo(Genus o) {
    if (o instanceof PrimateGenus) {
      return this.toString().compareTo(o.toString());
    }
    return this.getGenus().compareTo(o.getGenus());
  }
}
