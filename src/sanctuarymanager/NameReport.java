package sanctuarymanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a collection of NameReportUnits.
 */
class NameReport {
  private List<NameReportUnit> nameReport;

  /**
   * Creates name report object.
   */
  public NameReport() {
    nameReport = new ArrayList<>();
  }

  /**
   * add a name report unit.
   * @param e name report unit to be added
   */
  public void add(NameReportUnit e) {
    nameReport.add(e);
  }

  private List<NameReportUnit> getReport() {
    return nameReport;
  }

  /**
   * combine another name report with this one.
   * @param e name report to be combined
   */
  public void addAll(NameReport e) {
    nameReport.addAll(e.getReport());
  }

  /**
   * Sorts the sign array.
   */
  public void sort() {
    Collections.sort(nameReport);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder("");
    for (NameReportUnit k: nameReport) {
      ret.append(k.toString() + "\n");
    }
    return new String(ret);
  }
}
