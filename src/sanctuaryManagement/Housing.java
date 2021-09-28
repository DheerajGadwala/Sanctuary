package sanctuarymanagement;

/**
 * An interface to help classes simulate housing.
 */
public interface Housing {
  /**
   * adds a primate to the housing.
   * @param object primate object to be added.
   */
  public void addPrimate(Primate object);

  /**
   * removes a primate from the housing.
   * @param id unique id of the primate to be removed.
   * @return return the primate object.
   */
  public Primate removePrimate(int id);
}
