package sanctuarymanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test suite for JungleFriendsPrimateSanctuary.
 */
public class JungleFriendsPrimateSanctuaryTest {

  private JungleFriendsPrimateSanctuary sampleA;
  private JungleFriendsPrimateSanctuary sampleB;
  private JungleFriendsPrimateSanctuary sampleC;
  private JungleFriendsPrimateSanctuary sampleD;
  private JungleFriendsPrimateSanctuary sampleE;
  private JungleFriendsPrimateSanctuary sampleF;

  /**
   * setup for tests.
   */
  @Before
  public void setUp() {
    //regular case
    sampleA = new JungleFriendsPrimateSanctuary(20, Arrays.asList(16, 200, 150));
    sampleA.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sampleA.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sampleA.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);
    sampleA.addAnimal("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sampleA.addAnimal("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, true);
    sampleA.addAnimal("Dheeraj6", new Tamarin(), Sex.FEMALE, 14.9, 9, 7, Food.FRUITS, false);

    //For move to enclosure, animal is sick, so should not allow movement
    sampleB = new JungleFriendsPrimateSanctuary(20, Arrays.asList(16, 200, 150));
    sampleB.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, true);

    //For move to isolation
    sampleC = new JungleFriendsPrimateSanctuary(20, Arrays.asList(16, 200, 150));
    sampleC.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    int animalId_c_1 = sampleC.getAnimals().get(0).getId();
    sampleC.moveAnimalToEnclosure(animalId_c_1);

    //Full Isolation, for move to isolation
    sampleD = new JungleFriendsPrimateSanctuary(2, Arrays.asList(16, 200, 150));
    sampleD.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sampleD.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    int animalId_d_1 = sampleD.getAnimals().get(0).getId();
    sampleD.moveAnimalToEnclosure(animalId_d_1);
    sampleD.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);

    //No space in enclosures
    sampleE = new JungleFriendsPrimateSanctuary(2, Arrays.asList(10, 10));
    sampleE.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 21, 8, Food.EGGS, false);
    sampleE.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 22, 5, Food.NUTS, false);
    int animalId_e_1 = sampleE.getAnimals().get(0).getId();
    int animalId_e_2 = sampleE.getAnimals().get(1).getId();
    sampleE.moveAnimalToEnclosure(animalId_e_1);
    sampleE.moveAnimalToEnclosure(animalId_e_2);
    sampleE.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, false);

    //Enclosure sign
    sampleF = new JungleFriendsPrimateSanctuary(20, Arrays.asList(100, 200, 150));
    sampleF.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sampleF.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sampleF.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, false);
    sampleF.addAnimal("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sampleF.addAnimal("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, false);
    sampleF.addAnimal("Dheeraj6", new Tamarin(), Sex.FEMALE, 14.9, 9, 7, Food.FRUITS, false);
    int animalId_f_1 = sampleF.getAnimals().get(0).getId();
    int animalId_f_2 = sampleF.getAnimals().get(1).getId();
    int animalId_f_3 = sampleF.getAnimals().get(2).getId();
    int animalId_f_4 = sampleF.getAnimals().get(3).getId();
    int animalId_f_5 = sampleF.getAnimals().get(4).getId();
    int animalId_f_6 = sampleF.getAnimals().get(5).getId();
    sampleF.moveAnimalToEnclosure(animalId_f_1);
    sampleF.moveAnimalToEnclosure(animalId_f_2);
    sampleF.moveAnimalToEnclosure(animalId_f_3);
    sampleF.moveAnimalToEnclosure(animalId_f_4);
    sampleF.moveAnimalToEnclosure(animalId_f_5);
    sampleF.moveAnimalToEnclosure(animalId_f_6);
  }

  /**
   * Test if addIsolationCapacity works.
   */
  @Test
  public void testAddIsolationCapacity_1() {
    sampleA.addIsolationCapacity(40);
    Assert.assertEquals("20+40 should be 60.", 60, sampleA.getTotalIsolationCapacity());
  }

  /**
   * Test if addIsolationCapacity throws illegal Argument Exception when given 0 as argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddIsolationCapacity_2() {
    sampleA.addIsolationCapacity(0);
  }

  /**
   * Test if addIsolationCapacity throws illegal Argument Exception when given negative as argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddIsolationCapacity_3() {
    sampleA.addIsolationCapacity(-10);
  }
  
  /**
   * Test if addEnclosure works.
   */
  @Test
  public void testAddEnclosure_1() {
    sampleA.addEnclosure(100);
    Assert.assertEquals("Isolation capacity: 20, Number of Enclosures: 4", sampleA.toString());
  }

  /**
   * Test if addEnclosure throws exception when given 0 area of enclosure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddEnclosure_2() {
    sampleA.addEnclosure(0);
  }

  /**
   * Test if addEnclosure throws exception when given negative area of enclosure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddEnclosure_3() {
    sampleA.addEnclosure(-10);
  }

  /**
   * Test if lookUpSpecies returns housing objects containing given species.
   */
  @Test
  public void testLookUpSpecies_1() {
    List<Housing> housings = sampleA.lookUpSpecies(new Tamarin());
    for (Housing k: housings) {
      if (k instanceof Cage) {
        //asserting that
        Assert.assertTrue(((Cage)k).getAnimalSpecies() instanceof Tamarin);
      }
      else if (k instanceof Enclosure) {
        Assert.assertTrue(((Enclosure)k).getCurrentSpecies() instanceof Tamarin);
      }
    }
  }

  /**
   * Test if lookUpSpecies throws error when asked for species not in the sanctuary.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLookUpSpecies_2() {
    sampleA.lookUpSpecies(new Spider());
  }

  /**
   * Test if animal is moved as expected.
   * Before moving, it is housed in a cage.
   * After moving, it is housed in an enclosure.
   */
  @Test
  public void testMoveAnimalToEnclosure_1() {
    Animal animal = sampleA.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertTrue(sampleA.getHousing(animalId) instanceof Cage);
    sampleA.moveAnimalToEnclosure(animal.getId());
    Assert.assertTrue(sampleA.getHousing(animalId) instanceof Enclosure);
  }

  /**
   * Should throw error when animal is not found in isolation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveAnimalToEnclosure_2() {
    sampleA.moveAnimalToEnclosure(123123);
  }

  /**
   * Should throw error when animal is sick/ needs medical attention.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveAnimalToEnclosure_3() {
    Animal animal = sampleB.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertTrue(animal.getNeedsMedicalAttention());
    sampleB.moveAnimalToEnclosure(animalId);
  }

  /**
   * Should throw error when no enclosures are available.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveAnimalToEnclosure_4() {
    Animal animal = sampleE.getAnimals().get(0);
    int animalId = animal.getId();
    sampleE.moveAnimalToEnclosure(animalId);
  }

  /**
   * Test if give medical attention method works as expected.
   */
  @Test
  public void testGiveMedicalAttention() {
    Animal animal = sampleB.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertTrue(animal.getNeedsMedicalAttention());
    sampleB.giveMedicalAttention(animalId);
    Assert.assertFalse(animal.getNeedsMedicalAttention());
  }

  /**
   * Test if moving animal to enclosure works as expected.
   */
  @Test
  public void testMoveAnimalToIsolation_1() {
    Animal animal = sampleC.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertTrue(sampleC.getHousing(animalId) instanceof Enclosure);
    sampleC.moveAnimalToIsolation(animalId);
    Assert.assertTrue(sampleC.getHousing(animalId) instanceof Cage);
  }

  /**
   * Should throw error when animal not found in enclosures.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMoveAnimalToIsolation_2() {
    sampleD.moveAnimalToIsolation(86758);
  }

  /**
   * Should throw error when isolation is full.
   */
  @Test (expected = IllegalStateException.class)
  public void testMoveAnimalToIsolation_3() {
    Animal animal = sampleD.getAnimals().get(2);
    int animalId = animal.getId();
    Assert.assertTrue(sampleD.getHousing(animalId) instanceof Enclosure);
    sampleD.moveAnimalToIsolation(animalId);
  }

  /**
   * Test if required medical attention works as expected.
   */
  @Test
  public void testRequiresMedicalAttention() {
    Animal animal = sampleC.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertFalse(animal.getNeedsMedicalAttention());
    sampleC.requiresMedicalAttention(animalId);
    Assert.assertTrue(animal.getNeedsMedicalAttention());
  }

  /**
   * Test if Enclosure sign return the correct sign.
   */
  @Test
  public void testGetEnclosureSign_1() {
    int temp = sampleF.getEnclosureIds().get(0);
    Assert.assertEquals(
        "Name: Dheeraj1, Sex: male, Favourite food: eggs\n"
            + "Name: Dheeraj3, Sex: male, Favourite food: nuts\n"
            + "Name: Dheeraj5, Sex: female, Favourite food: eggs\n"
            + "Name: Dheeraj6, Sex: female, Favourite food: fruits\n",
        sampleF.getEnclosureSign(temp));
  }

  /**
   * Test if getEnclosure sign throws an error if there are no animals in the enclosure.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetEnclosureSign_2() {
    int temp = sampleA.getEnclosureIds().get(0);
    sampleA.getEnclosureSign(temp);
  }

  /**
   * Test if getEnclosure sign throws an error when invalid enclosure id is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetEnclosureSign_3() {
    sampleA.getEnclosureSign(12312);
  }

  /**
   * test if get animal throws exception when animal does not exist in sanctuary.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetAnimal_1() {
    sampleA.getAnimal(100000);
  }

  /**
   * test if get animal works as expected.
   */
  @Test
  public void testGetAnimal_2() {
    Animal animal = sampleA.getAnimals().get(0);
    Assert.assertEquals(animal.toString(), sampleA.getAnimal(animal.getId()));
  }

  /**
   * Test that removeAnimal works as expected.
   */
  @Test
  public void testRemoveAnimal_1() {
    Animal animal = sampleA.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertEquals(sampleA.removeAnimal(animalId), animal.toString());
  }

  /**
   * Test that removeAnimal throws error when invalid id [non existent] is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveAnimal_2() {
    sampleA.removeAnimal(1703439);
  }

  /**
   * Test if getSpeciesAndHousing works as expected.
   */
  @Test
  public void getSpeciesAndHousing() {
    JungleFriendsPrimateSanctuary sample = new JungleFriendsPrimateSanctuary(
        20, Arrays.asList(100, 200, 150, 200, 150)
    );
    sample.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sample.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sample.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, false);
    sample.addAnimal("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sample.addAnimal("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, false);
    sample.addAnimal("Dheeraj6", new Spider(), Sex.FEMALE, 14.9, 28, 10, Food.FRUITS, false);
    sample.addAnimal("Dheeraj7", new Tamarin(), Sex.MALE, 16.9, 22, 22, Food.INSECTS, false);
    sample.addAnimal("Dheeraj8", new Guereza(), Sex.FEMALE, 17.4, 4, 1, Food.SEEDS, false);
    sample.addAnimal("Dheeraj9", new Squirrel(), Sex.MALE, 22.9, 15, 7, Food.FRUITS, false);
    sample.addAnimal("Dheeraj10", new Squirrel(), Sex.FEMALE, 13.7, 9, 9, Food.LEAVES, false);
    List<Animal> animals = sample.getAnimals();
    sample.moveAnimalToEnclosure(animals.get(1).getId());
    sample.moveAnimalToEnclosure(animals.get(2).getId());
    sample.moveAnimalToEnclosure(animals.get(3).getId());
    sample.moveAnimalToEnclosure(animals.get(4).getId());
    sample.moveAnimalToEnclosure(animals.get(5).getId());
    sample.moveAnimalToEnclosure(animals.get(6).getId());
    sample.moveAnimalToEnclosure(animals.get(7).getId());
    Assert.assertEquals(
        "Species: Drill, Housing: " + sample.getHousing(animals.get(1).getId()) + "\n"
            + "Species: Guereza, Housing: " + sample.getHousing(animals.get(7).getId()) + "\n"
            + "Species: Mangabey, Housing: " + sample.getHousing(animals.get(3).getId()) + "\n"
            + "Species: Spider, Housing: " + sample.getHousing(animals.get(5).getId()) + "\n"
            + "Species: Squirrel, Housing: " + sample.getHousing(animals.get(8).getId()) + "\n"
            + "Species: Squirrel, Housing: " + sample.getHousing(animals.get(9).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sample.getHousing(animals.get(0).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sample.getHousing(animals.get(2).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sample.getHousing(animals.get(4).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sample.getHousing(animals.get(6).getId()) + "\n",
        sample.getSpeciesAndHousing());
  }

  /**
   * Test if getNameAndHousing works as expected.
   */
  @Test
  public void getNameAndHousing() {
    JungleFriendsPrimateSanctuary sample = new JungleFriendsPrimateSanctuary(
        20, Arrays.asList(100, 200, 150, 200, 150)
    );
    sample.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sample.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sample.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, false);
    sample.addAnimal("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sample.addAnimal("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, false);
    sample.addAnimal("Dheeraj6", new Spider(), Sex.FEMALE, 14.9, 28, 10, Food.FRUITS, false);
    sample.addAnimal("Dheeraj7", new Tamarin(), Sex.MALE, 16.9, 22, 22, Food.INSECTS, false);
    sample.addAnimal("Dheeraj8", new Guereza(), Sex.FEMALE, 17.4, 4, 1, Food.SEEDS, false);
    sample.addAnimal("Dheeraj9", new Squirrel(), Sex.MALE, 22.9, 15, 7, Food.FRUITS, false);
    sample.addAnimal("Dheeraj10", new Squirrel(), Sex.FEMALE, 13.7, 9, 9, Food.LEAVES, false);
    List<Animal> animals = sample.getAnimals();
    sample.moveAnimalToEnclosure(animals.get(1).getId());
    sample.moveAnimalToEnclosure(animals.get(2).getId());
    sample.moveAnimalToEnclosure(animals.get(3).getId());
    sample.moveAnimalToEnclosure(animals.get(4).getId());
    sample.moveAnimalToEnclosure(animals.get(5).getId());
    sample.moveAnimalToEnclosure(animals.get(6).getId());
    sample.moveAnimalToEnclosure(animals.get(7).getId());
    Assert.assertEquals(
        "Name: Dheeraj1, Housing: " + sample.getHousing(animals.get(0).getId()) + "\n"
            + "Name: Dheeraj10, Housing: " + sample.getHousing(animals.get(9).getId()) + "\n"
            + "Name: Dheeraj2, Housing: " + sample.getHousing(animals.get(1).getId()) + "\n"
            + "Name: Dheeraj3, Housing: " + sample.getHousing(animals.get(2).getId()) + "\n"
            + "Name: Dheeraj4, Housing: " + sample.getHousing(animals.get(3).getId()) + "\n"
            + "Name: Dheeraj5, Housing: " + sample.getHousing(animals.get(4).getId()) + "\n"
            + "Name: Dheeraj6, Housing: " + sample.getHousing(animals.get(5).getId()) + "\n"
            + "Name: Dheeraj7, Housing: " + sample.getHousing(animals.get(6).getId()) + "\n"
            + "Name: Dheeraj8, Housing: " + sample.getHousing(animals.get(7).getId()) + "\n"
            + "Name: Dheeraj9, Housing: " + sample.getHousing(animals.get(8).getId()) + "\n",
        sample.getNameAndHousing());
  }

  /**
   * Test if get shopping list works as expected.
   */
  @Test
  public void testGetShoppingList() {
    Assert.assertEquals(
        "{eggs=750, fruits=100, insects=0, leaves=0, nuts=350, seeds=0, tree sap=250}",
        sampleA.getShoppingList()
    );
  }

  /**
   * Test that addAnimal works as expected.
   */
  @Test
  public void testAddAnimal_1() {
    Assert.assertEquals(sampleA.getAnimals().size(), 6);
    sampleA.addAnimal("Dheeraj7", new Tamarin(), Sex.FEMALE, 14.9, 9, 7, Food.FRUITS, false);
    Assert.assertEquals(sampleA.getAnimals().size(), 7);
  }

  /**
   * Test that addAnimal throws error on invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddAnimal_2() {
    sampleA.addAnimal("Dheeraj7", new Tamarin(), Sex.FEMALE, -10, 9, -7, null, false);
  }

  /**
   * When an animal is added when the isolation capacity is already full.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddAnimal_3() {
    sampleD.addAnimal("Dheeraj7", new Tamarin(), Sex.FEMALE, 10, 9, 17, Food.EGGS, false);
  }

}