package sanctuarymanagement;

/**
 * This class will be used to report names along
 * which their housing in alphabetical order.
 */
public class NameReportUnit implements Comparable<NameReportUnit> {

  private final String name;
  private final Housing housing;

  /**
   * Structured data, stores name and housing.
   * @param name name stored in this object as a string
   * @param housing housing object
   */
  public NameReportUnit(String name, Housing housing) {
    this.name = name;
    this.housing = housing;
  }

  /**
   * getter method for name.
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * getter method for housing.
   * @return housing object
   */
  public Housing getHousing() {
    return housing;
  }

  @Override
  public int compareTo(NameReportUnit o) {
    int ret = name.compareTo(o.getName());
    if (ret == 0) {
      return housing.compareTo(o.getHousing());
    }
    return ret;
  }
}
