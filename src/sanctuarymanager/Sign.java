package sanctuarymanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a collection of SignUnits.
 */
class Sign {

  private List<SignUnit> sign;

  /**
   * creates a Sign object.
   */
  public Sign() {
    sign = new ArrayList<>();
  }

  /**
   * add a sign unit.
   * @param e sign unit to be added
   */
  public void add(SignUnit e) {
    sign.add(e);
  }

  /**
   * Sorts the sign array.
   */
  public void sort() {
    Collections.sort(sign);
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder("");
    for (SignUnit k: sign) {
      ret.append(k.toString() + "\n");
    }
    return new String(ret);
  }
}
