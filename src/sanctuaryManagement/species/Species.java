package sanctuarymanagement.species;

public  abstract class Species implements Comparable<Species> {

  private final String species;

  protected Species(String species) {
    this.species = species;
  }

  public String getSpeciesName(){
    return species;
  }

  @Override
  public int compareTo(Species o) {
    return this.getSpeciesName().compareTo(o.getSpeciesName());
  }
}
