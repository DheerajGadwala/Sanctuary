package sanctuarymanagement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This interface represents housing objects that can house more than one animal.
 * Since they house multiple animals they can produce lists like species and housing,
 * names and housing and shopping lists.
 */
public interface MultipleHousing extends Housing {

  /**
   * Get the details of an animal with given id from this object.
   * @param id id of the animal whose details are being requested.
   * @return an Animal object.
   * @throws IllegalStateException when the animal does not exist in this object.
   */
  public Animal getAnimal(int id) throws IllegalArgumentException;

  /**
   * Get list of species of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Species and Housing.
   */
  public List<SpeciesReportUnit> getSpeciesAndHousing();

  /**
   * Get list of names of the animals inside this object sorted
   * alphabetically along with their housing.
   * @return Structured data representing Names and Housing.
   */
  public List<NameReportUnit> getNameAndHousing();

  /**
   * Get the list of items to be purchased in a tree map.
   * @return a map with food as key and quantity as value.
   */
  public Map<Food, Integer> getShoppingList();
}
