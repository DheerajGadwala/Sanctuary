package sanctuarymanager;

/**
 * A collection of similar species is classified as a genus.
 */
interface Genus extends Comparable<Genus> {

  /**
   * getter method for genus name.
   * @return genus name.
   */
  public String getGenus();
}
