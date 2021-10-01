package sanctuarymanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a collection of speciesReportUnits.
 */
class SpeciesReport {
  private List<SpeciesReportUnit> speciesReport;

  /**
   * Creates a species report object.
   */
  public SpeciesReport() {
    speciesReport = new ArrayList<>();
  }

  /**
   * add a species report unit.
   * @param e species report unit to be added
   */
  public void add(SpeciesReportUnit e) {
    speciesReport.add(e);
  }

  private List<SpeciesReportUnit> getReport() {
    return speciesReport;
  }

  /**
   * combine another species report with this one.
   * @param e species report which is to be added to this.
   */
  public void addAll(SpeciesReport e) {
    speciesReport.addAll(e.getReport());
  }

  /**
   * Sorts the sign array.
   */
  public void sort() {
    Collections.sort(speciesReport);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder("");
    for (SpeciesReportUnit k: speciesReport) {
      ret.append(k.toString() + "\n");
    }
    return new String(ret);
  }
}
