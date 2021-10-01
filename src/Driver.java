import sanctuarymanager.Drill;
import sanctuarymanager.Food;
//import sanctuarymanager.Genus;
import sanctuarymanager.Guereza;
import sanctuarymanager.Howler;
import sanctuarymanager.JungleFriendsPrimateSanctuary;
import sanctuarymanager.Mangabey;
//import sanctuarymanager.Saki;
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
        JungleFriendsPrimateSanctuary
        Food Enum
        Sex Enum
        Genus Interface: A group of species which are similar to each other.
        All species [Eg: Howler, Spider, etc.] which are an instance of Genus.
    */

    JungleFriendsPrimateSanctuary sample = new JungleFriendsPrimateSanctuary(
        20, Arrays.asList(16, 200, 150),
        Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));

    sample.addAnimal("Emilie Merrill", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sample.addAnimal("Emanuel Armstrong", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sample.addAnimal("Fiza Muir", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);
    sample.addAnimal("Agnes Conrad", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sample.addAnimal("Suhail Daly", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, true);
    sample.addAnimal("Reegan Prentice", new Tamarin(), Sex.FEMALE, 14.9, 9, 7, Food.FRUITS, false);

    sample.giveMedicalAttention(2);
    sample.giveMedicalAttention(4); // Give medical attention

    sample.moveAnimalToEnclosure(2);
    sample.moveAnimalToEnclosure(4); // Move Animal to enclosure
    sample.moveAnimalToEnclosure(1);
    sample.moveAnimalToEnclosure(0);

    sample.addIsolationCapacity(5); // add Isolation Capacity

    sample.addEnclosure(250, new Spider()); // add Enclosure

    sample.repurposeEnclosure(3, new Guereza()); // repurpose Enclosure

    sample.addAnimal("Dheeraj Gadwala", new Howler(), Sex.FEMALE, 14.9, 14, 7, Food.FRUITS, false);


    System.out.println("get animal: \n"+sample.getAnimal(3)); // get animal, returns string format of animal.
    // Animal is package private and can not be accessed from the driver.


    sample.requiresMedicalAttention(0); // flag animal 0 as requires medical attention

    sample.moveAnimalToIsolation(0); // move animal 0 from enclosure to isolation


    System.out.println("sign of enclosure 0: \n" + sample.getEnclosureSign(0));
    System.out.println("sign of enclosure 1: \n" + sample.getEnclosureSign(1));
    // sign of enclosures
    // sign for 2 and 3 will throw an error because they are empty.


    System.out.println("Look up species Tamarin: ");
    System.out.println(sample.lookUpSpecies(new Tamarin()) + "\n"); //Look up species

    System.out.println("Species Report: ");
    System.out.println(sample.getSpeciesAndHousing()); //Species Report

    System.out.println("Names Report: ");
    System.out.println(sample.getNameAndHousing());  // Name Report

    System.out.println("Shopping List: ");
    System.out.println(sample.getShoppingList()); // Shopping List
  }
}
