package sanctuarymanager;

import org.junit.Test;


/**
 * Test suite for Primate class.
 */
public class PrimateTest {

  /**
   * Test is exception is thrown when name is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_1() {
    new Primate(null, new Tamarin(), null, 15.6, 14, 8, Food.EGGS, false);
  }

  /**
   * Test is exception is thrown when name is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_2() {
    new Primate("", new Tamarin(), null, 15.6, 14, 8, Food.EGGS, false);
  }

  /**
   * Test is exception is thrown when species is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_3() {
    new Primate("Dheeraj", null, Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
  }

  /**
   * Test is exception is thrown when sex is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_4() {
    new Primate("Dheeraj", new Tamarin(), null, 15.6, 14, 8, Food.EGGS, false);
  }

  /**
   * Test is exception is thrown when weight is non positive.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_5() {
    new Primate("Dheeraj", new Tamarin(), null, -5, 14, 8, Food.EGGS, false);
  }

  /**
   * Test is exception is thrown when height is non positive.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_6() {
    new Primate("Dheeraj", new Tamarin(), null, 5, 0, 8, Food.EGGS, false);
  }

  /**
   * Test is exception is thrown when age is non positive.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_7() {
    new Primate("Dheeraj", new Tamarin(), null, 5, 10, -1, Food.EGGS, false);
  }

  /**
   * Test is exception is thrown when favourite food is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_8() {
    new Primate("Dheeraj", new Tamarin(), null, 5, 3, 8, null, false);
  }
}