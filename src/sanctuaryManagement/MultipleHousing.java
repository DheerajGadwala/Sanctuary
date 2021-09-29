package sanctuarymanagement;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This interface represents housing objects that can house more than one animal.
 * Since they house multiple animals they can produce lists like species and housing,
 * names and housing and shopping lists.
 */
public interface MultipleHousing extends Housing {

  /**
   * Get list of species of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Species and Housing.
   */
  public ArrayList<SpeciesReportUnit> getSpeciesAndHousing();

  /**
   * Get list of names of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Names and Housing.
   */
  public ArrayList<NameReportUnit> getSNameAndHousing();

  /**
   * Get the list of items to be purchased in a tree map.
   * @return a map with food as key and quantity as value.
   */
  public TreeMap<Food, Integer> getShoppingList();

}
