import sanctuarymanager.Drill;
import sanctuarymanager.Food;
//import sanctuarymanager.Genus;
import sanctuarymanager.Guereza;
import sanctuarymanager.Howler;
import sanctuarymanager.Mangabey;
import sanctuarymanager.PrimateSanctuary;
import sanctuarymanager.Saki;
import sanctuarymanager.Sex;
import sanctuarymanager.Spider;
//import sanctuarymanager.Squirrel;
import sanctuarymanager.Tamarin;

import java.util.Arrays;

/**
 * This is the driver class.
 */
public class Driver {

  /**
   * Main method.
   */
  public static void main(String[] args) {

    /*
      classes with public accessibility:
        PrimateSanctuary
        Food Enum
        Sex Enum
        Genus Interface: A group of species which are similar to each other.
        All species [Eg: Howler, Spider, etc.] which are an instance of Genus.
        More species can be added by extensions.
    */

    PrimateSanctuary jfps = new PrimateSanctuary(
        20, Arrays.asList(16, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));
    // jungle friends primate sanctuary.

    jfps.addAnimal("Emilie Merrill", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    jfps.addAnimal("Emanuel Armstrong", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    jfps.addAnimal("Fiza Muir", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);
    jfps.addAnimal("Agnes Conrad", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    jfps.addAnimal("Suhail Daly", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, true);
    jfps.addAnimal("Reegan Prentice", new Tamarin(), Sex.FEMALE, 14.9, 9, 7, Food.FRUITS, false);

    jfps.giveMedicalAttention(2);
    jfps.giveMedicalAttention(4); // Give medical attention

    jfps.moveAnimalToEnclosure(2);
    jfps.moveAnimalToEnclosure(4); // Move Animal to enclosure
    jfps.moveAnimalToEnclosure(1);
    jfps.moveAnimalToEnclosure(0);

    jfps.addIsolationCapacity(5); // add Isolation Capacity

    jfps.addEnclosure(250, new Spider()); // add Enclosure

    jfps.repurposeEnclosure(3, new Guereza()); // repurpose Enclosure

    jfps.addAnimal("Dheeraj Gadwala", new Howler(), Sex.FEMALE, 14.9, 14, 7, Food.FRUITS, false);
    // add animal

    System.out.println("remove animal: \n" + jfps.removeAnimal(4) + "\n"); //remove animal

    System.out.println("get animal: \n" + jfps.getAnimal(3)); // getAnimal
    // get animal, returns string format of animal.
    // Animal is package private and can not be accessed from the driver.


    jfps.requiresMedicalAttention(0); // flag animal 0 as requires medical attention

    jfps.moveAnimalToIsolation(0); // move animal 0 from enclosure to isolation


    System.out.println("sign of enclosure 0: \n" + jfps.getEnclosureSign(0));
    System.out.println("sign of enclosure 1: \n" + jfps.getEnclosureSign(1));
    // sign of enclosures
    // sign for 2 and 3 will throw an error because they are empty.


    System.out.println("Look up species Tamarin: ");
    System.out.println(jfps.lookUpSpecies(new Tamarin()) + "\n"); //Look up species

    try {
      System.out.println("try looking for saki species: ");
      System.out.println(jfps.lookUpSpecies(new Saki()) + "\n"); //Look up species
    }
    catch (Exception e) {
      System.out.println(e);
      System.out.println("No Saki in sanctuary, therefore throws an error.\n");
    }
    System.out.println("Species Report: ");
    System.out.println(jfps.getSpeciesAndHousing()); //Species Report

    System.out.println("Names Report: ");
    System.out.println(jfps.getNameAndHousing());  // Name Report

    System.out.println("Shopping List: ");
    System.out.println(jfps.getShoppingList() + "\n"); // Shopping List

    PrimateSanctuary partner = new PrimateSanctuary(
        20, Arrays.asList(15, 20),
        Arrays.asList(new Drill(), new Tamarin()));
    // partner sanctuary


    System.out.println("Transfer animal to partner: ");
    //transfer to partner shallow copies animals, so they get new ids.
    System.out.println("animal in jfps: \n" + jfps.getAnimal(2));
    // this will be transferred to partner sanctuary.
    jfps.transferAnimalToPartnerSanctuary(2, partner);
    // Transfer animal to partner
    int newId = partner.getAnimalIds().get(0);
    // only one animal in partner.
    System.out.println("animal in partner: \n" + partner.getAnimal(newId));
    // Animal with new id in partner's isolation.
    try {
      System.out.println("try looking for the moved animal: ");
      System.out.println(jfps.getAnimal(2)); //this will throw an error.
      System.out.println(jfps.getAnimal(newId)); // Won't reach this line.
    }
    catch (Exception e) {
      System.out.println(e);
      System.out.println("Animal moved to partner, therefore not in jfps.");
    }
  }
}
