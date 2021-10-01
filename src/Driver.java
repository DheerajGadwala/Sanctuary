import sanctuarymanager.*;

import java.util.Arrays;

public class Driver {
  public static void main(String[] args) {

    JungleFriendsPrimateSanctuary sample = new JungleFriendsPrimateSanctuary(20, Arrays.asList(16, 200, 150));
    sample.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    sample.addAnimal("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    sample.addAnimal("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);
    sample.addAnimal("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    sample.addAnimal("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, true);
    sample.addAnimal("Dheeraj6", new Tamarin(), Sex.FEMALE, 14.9, 9, 7, Food.FRUITS, false);
    sample.giveMedicalAttention(2);
    sample.giveMedicalAttention(4);
    sample.moveAnimalToEnclosure(2);
    sample.moveAnimalToEnclosure(4);
    sample.moveAnimalToEnclosure(1);
    sample.moveAnimalToEnclosure(0);
    sample.addAnimal("Dheeraj7", new Howler(), Sex.FEMALE, 14.9, 14, 7, Food.FRUITS, false);
    System.out.println(sample.lookUpSpecies(new Tamarin())+"\n");
    System.out.println(sample.getSpeciesAndHousing());
    System.out.println(sample.getNameAndHousing());
    System.out.println(sample.getShoppingList());
  }
}
