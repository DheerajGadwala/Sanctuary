package sanctuarymanagement;

/**
 * A collection of similar species is classified as a genus.
 */
public interface Genus extends Comparable<Genus> {

  /**
   * getter method for genus name.
   * @return genus name.
   */
  public String getGenus();
}
