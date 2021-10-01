package sanctuarymanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PrimateSanctuaryTest {

  private PrimateSanctuary sampleA;
  private PrimateSanctuary sampleB;
  private PrimateSanctuary sampleC;
  private PrimateSanctuary sampleD;
  private PrimateSanctuary sampleE;
  private PrimateSanctuary sampleF;
  private PrimateSanctuary sampleG;
  private PrimateSanctuary sampleH;
  private PrimateSanctuary sampleI;

  /**
   * setup for tests.
   */
  @Before
  public void setUp() {
    //regular case
    sampleA = new PrimateSanctuary(20,
        Arrays.asList(16, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));
    sampleA.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sampleA.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sampleA.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);
    sampleA.addAnimal("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sampleA.addAnimal("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, true);
    sampleA.addAnimal("Dheeraj6", new Tamarin(), Sex.FEMALE, 14.9, 9, 7, Food.FRUITS, false);

    //For move to enclosure, animal is sick, so should not allow movement
    sampleB = new PrimateSanctuary(20,
        Arrays.asList(16, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));
    sampleB.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, true);

    //For move to isolation
    sampleC = new PrimateSanctuary(20,
        Arrays.asList(16, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));
    sampleC.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    int animalId_c_1 = sampleC.getAnimals().get(0).getId();
    sampleC.moveAnimalToEnclosure(animalId_c_1);

    //Full Isolation, for move to isolation
    sampleD = new PrimateSanctuary(2,
        Arrays.asList(16, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));
    sampleD.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sampleD.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    int animalId_d_1 = sampleD.getAnimals().get(0).getId();
    sampleD.moveAnimalToEnclosure(animalId_d_1);
    sampleD.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);

    //No space in enclosures
    sampleE = new PrimateSanctuary(2,
        Arrays.asList(10, 10),
        Arrays.asList(new Drill(), new Tamarin()));
    sampleE.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 21, 8, Food.EGGS, false);
    sampleE.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 22, 5, Food.NUTS, false);
    int animalId_e_1 = sampleE.getAnimals().get(0).getId();
    int animalId_e_2 = sampleE.getAnimals().get(1).getId();
    sampleE.moveAnimalToEnclosure(animalId_e_1);
    sampleE.moveAnimalToEnclosure(animalId_e_2);
    sampleE.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, false);

    //Enclosure sign
    sampleF = new PrimateSanctuary(20,
        Arrays.asList(100, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));
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

    sampleG = new PrimateSanctuary(20,
        Arrays.asList(100, 200, 150, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Squirrel(), new Spider(), new Mangabey()));
    sampleG.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sampleG.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sampleG.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, false);
    sampleG.addAnimal("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sampleG.addAnimal("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, false);
    sampleG.addAnimal("Dheeraj6", new Spider(), Sex.FEMALE, 14.9, 28, 10, Food.FRUITS, false);
    sampleG.addAnimal("Dheeraj7", new Tamarin(), Sex.MALE, 16.9, 22, 22, Food.INSECTS, false);
    sampleG.addAnimal("Dheeraj8", new Guereza(), Sex.FEMALE, 17.4, 4, 1, Food.SEEDS, false);
    sampleG.addAnimal("Dheeraj9", new Squirrel(), Sex.MALE, 22.9, 15, 7, Food.FRUITS, false);
    sampleG.addAnimal("Dheeraj10", new Squirrel(), Sex.FEMALE, 13.7, 9, 9, Food.LEAVES, false);

    //For repurpose enclosure
    sampleH = new PrimateSanctuary(20,
        Arrays.asList(20),
        Arrays.asList(new Drill()));
    sampleH.addAnimal("Dheeraj1", new Mangabey(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
  }

  /**
   * Testing if constructor throws error when isolation capacity is non positive.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_1() {
    new PrimateSanctuary(-10, Arrays.asList(10), Arrays.asList(new Mangabey()));
  }

  /**
   * Testing if constructor throws error when enclosure area is non positive.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_2() {
    new PrimateSanctuary(10,
        Arrays.asList(-10, 10),
        Arrays.asList(new Mangabey(), new Tamarin()));
  }

  /**
   * Testing if constructor throws error when one of species is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_3() {
    new PrimateSanctuary(10,
        Arrays.asList(20, 10),
        Arrays.asList(null, new Tamarin()));
  }

  /**
   * Testing if constructor throws error when unbalanced areas and species are given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_4() {
    new PrimateSanctuary(10,
        Arrays.asList(20),
        Arrays.asList(new Spider(), new Tamarin()));
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
    sampleA.addEnclosure(100, new Drill());
    Assert.assertEquals("Isolation capacity: 20, Number of Enclosures: 4", sampleA.toString());
  }

  /**
   * Test if addEnclosure throws exception when given 0 area of enclosure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddEnclosure_2() {
    sampleA.addEnclosure(0, new Tamarin());
  }

  /**
   * Test if addEnclosure throws exception when given negative area of enclosure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddEnclosure_3() {
    sampleA.addEnclosure(-10, new Mangabey());
  }

  /**
   * Test if lookUpSpecies returns correct output.
   */
  @Test
  public void testLookUpSpecies_1() {
    String housings = sampleA.lookUpSpecies(new Tamarin());
    List<Animal> animals = sampleA.getAnimals();
    String expected =
        "[" + sampleA.getHousing(animals.get(0).getId()).toString()
            + ", " + sampleA.getHousing(animals.get(2).getId()).toString()
            + ", " + sampleA.getHousing(animals.get(4).getId()).toString()
            + ", " + sampleA.getHousing(animals.get(5).getId()).toString()
            + "]";
    Assert.assertEquals(expected, housings);
  }

  /**
   * Test if lookUpSpecies throws error when asked for species not in the sanctuary.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLookUpSpecies_2() {
    sampleA.lookUpSpecies(new Spider());
  }

  /**
   * Test if animal is moves as expected.
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
   * Should not move animal if it needs medical attention.
   * Should throw error when animal is needs medical attention.
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
  public void testGiveMedicalAttention_1() {
    Animal animal = sampleB.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertTrue(animal.getNeedsMedicalAttention());
    sampleB.giveMedicalAttention(animalId);
    Assert.assertFalse(animal.getNeedsMedicalAttention());
  }

  /**
   * Test if give medical attention throws error when invalid animal id is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGiveMedicalAttention_2() {
    sampleB.giveMedicalAttention(123123);
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
   * Test if requires medical attention works as expected.
   */
  @Test
  public void testRequiresMedicalAttention_1() {
    Animal animal = sampleC.getAnimals().get(0);
    int animalId = animal.getId();
    Assert.assertFalse(animal.getNeedsMedicalAttention());
    sampleC.requiresMedicalAttention(animalId);
    Assert.assertTrue(animal.getNeedsMedicalAttention());
  }

  /**
   * Test if requires medical attention throws error when invalid input is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRequiresMedicalAttention_2() {
    sampleC.requiresMedicalAttention(18273);
  }

  /**
   * Test if Enclosure sign return the correct sign.
   */
  @Test
  public void testGetEnclosureSign_1() {
    int temp = sampleF.getEnclosureIds().get(1);
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
  public void testGetSpeciesAndHousing() {
    List<Animal> animals = sampleG.getAnimals();
    sampleG.moveAnimalToEnclosure(animals.get(1).getId());
    sampleG.moveAnimalToEnclosure(animals.get(2).getId());
    sampleG.moveAnimalToEnclosure(animals.get(3).getId());
    sampleG.moveAnimalToEnclosure(animals.get(4).getId());
    sampleG.moveAnimalToEnclosure(animals.get(5).getId());
    sampleG.moveAnimalToEnclosure(animals.get(6).getId());
    sampleG.moveAnimalToEnclosure(animals.get(8).getId());
    Assert.assertEquals(
        "Species: Drill, Housing: " + sampleG.getHousing(animals.get(1).getId()) + "\n"
            + "Species: Guereza, Housing: " + sampleG.getHousing(animals.get(7).getId()) + "\n"
            + "Species: Mangabey, Housing: " + sampleG.getHousing(animals.get(3).getId()) + "\n"
            + "Species: Spider, Housing: " + sampleG.getHousing(animals.get(5).getId()) + "\n"
            + "Species: Squirrel, Housing: " + sampleG.getHousing(animals.get(9).getId()) + "\n"
            + "Species: Squirrel, Housing: " + sampleG.getHousing(animals.get(8).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sampleG.getHousing(animals.get(0).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sampleG.getHousing(animals.get(2).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sampleG.getHousing(animals.get(4).getId()) + "\n"
            + "Species: Tamarin, Housing: " + sampleG.getHousing(animals.get(6).getId()) + "\n",
        sampleG.getSpeciesAndHousing());
  }

  /**
   * Test if getNameAndHousing works as expected.
   */
  @Test
  public void testGetNameAndHousing() {
    List<Animal> animals = sampleG.getAnimals();
    sampleG.moveAnimalToEnclosure(animals.get(1).getId());
    sampleG.moveAnimalToEnclosure(animals.get(2).getId());
    sampleG.moveAnimalToEnclosure(animals.get(3).getId());
    sampleG.moveAnimalToEnclosure(animals.get(4).getId());
    sampleG.moveAnimalToEnclosure(animals.get(5).getId());
    sampleG.moveAnimalToEnclosure(animals.get(6).getId());
    sampleG.moveAnimalToEnclosure(animals.get(8).getId());
    Assert.assertEquals(
        "Name: Dheeraj1, Housing: " + sampleG.getHousing(animals.get(0).getId()) + "\n"
            + "Name: Dheeraj10, Housing: " + sampleG.getHousing(animals.get(9).getId()) + "\n"
            + "Name: Dheeraj2, Housing: " + sampleG.getHousing(animals.get(1).getId()) + "\n"
            + "Name: Dheeraj3, Housing: " + sampleG.getHousing(animals.get(2).getId()) + "\n"
            + "Name: Dheeraj4, Housing: " + sampleG.getHousing(animals.get(3).getId()) + "\n"
            + "Name: Dheeraj5, Housing: " + sampleG.getHousing(animals.get(4).getId()) + "\n"
            + "Name: Dheeraj6, Housing: " + sampleG.getHousing(animals.get(5).getId()) + "\n"
            + "Name: Dheeraj7, Housing: " + sampleG.getHousing(animals.get(6).getId()) + "\n"
            + "Name: Dheeraj8, Housing: " + sampleG.getHousing(animals.get(7).getId()) + "\n"
            + "Name: Dheeraj9, Housing: " + sampleG.getHousing(animals.get(8).getId()) + "\n",
        sampleG.getNameAndHousing());
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

  /**
   * test if repurposeEnclosure works as expected.
   * repurposed enclosure to mangabey and added a mangabey.
   */
  @Test
  public void testRepurposeEnclosure_1() {
    int enclosureId = sampleH.getEnclosureIds().get(0);
    sampleH.repurposeEnclosure(enclosureId, new Mangabey());
    int animalId = sampleH.getAnimals().get(0).getId();
    sampleH.moveAnimalToEnclosure(animalId);
  }

  /**
   * test invalid arguments om repurpose Enclosure.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRepurposeEnclosure_2() {
    sampleH.repurposeEnclosure(1231, new Mangabey());
  }

  /**
   * Test if transferAnimalToPartnerSanctuary returns the animal object to be transferred.
   */
  @Test
  public void testTransferAnimalToPartnerSanctuary_1() {
    Animal animal = sampleA.getAnimals().get(0);
    Assert.assertEquals(animal, sampleA.transferAnimalToPartnerSanctuary(animal.getId()));
  }

  /**
   * Test if transferAnimalToPartnerSanctuary throws error when given sick animal.
   */
  @Test(expected = IllegalStateException.class)
  public void testTransferAnimalToPartnerSanctuary_2() {
    Animal animal = sampleA.getAnimals().get(2);
    sampleA.transferAnimalToPartnerSanctuary(animal.getId());
  }

  /**
   * Test if transferAnimalToPartnerSanctuary throws error when given invalid animalId.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTransferAnimalToPartnerSanctuary_3() {
    sampleA.transferAnimalToPartnerSanctuary(123123123);
  }

  /**
   * Test if receive animal from partner works as expected.
   */
  @Test
  public void receiveAnimalFromPartnerSanctuary_1() {
    Assert.assertEquals(6, sampleA.getAnimals().size());
    sampleA.receiveAnimalFromPartnerSanctuary(
        new Primate("Dheeraj10", new Squirrel(), Sex.FEMALE, 13.7, 9, 9, Food.LEAVES, false)
    );
    Assert.assertEquals(7, sampleA.getAnimals().size());
  }

  /**
   * Test if receive animal from partner throws error if received animal is sick.
   */
  @Test(expected = IllegalArgumentException.class)
  public void receiveAnimalFromPartnerSanctuary_2() {
    sampleA.receiveAnimalFromPartnerSanctuary(
        new Primate("Dheeraj10", new Squirrel(), Sex.FEMALE, 13.7, 9, 9, Food.LEAVES, true)
    );
  }

  /**
   * Test if receive animal from partner throws error if null was received.
   */
  @Test(expected = IllegalArgumentException.class)
  public void receiveAnimalFromPartnerSanctuary_3() {
    sampleA.receiveAnimalFromPartnerSanctuary(null);
  }

  /**
   * Test if receive animal from partner does not use the animal object
   * but instead creates a shallow copy for itself.
   * We can test this by comparing references.
   */
  @Test
  public void receiveAnimalFromPartnerSanctuary_4() {
    Animal animal = new Primate("Dheeraj10",
        new Squirrel(),
        Sex.FEMALE,
        13.7,
        9,
        9,
        Food.LEAVES,
        false
    );
    sampleH.receiveAnimalFromPartnerSanctuary(animal);
    Animal animalFromSanctuary = sampleH.getAnimals().get(0);
    Assert.assertFalse(animal == animalFromSanctuary); // comparing references.
  }
}