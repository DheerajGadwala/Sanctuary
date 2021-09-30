package sanctuarymanagement;

import java.util.Arrays;

public class Driver {
  public static void main(String[] args) {
    Animal primate1 = new Primate("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    Animal primate2 = new Primate("Dheeraj2", new Drill(), Sex.FEMALE, 9.6, 9, 5, Food.NUTS, false);
    Animal primate3 = new Primate("Dheeraj3", new Tamarin(), Sex.MALE, 11.3, 10, 7, Food.NUTS, true);
    Animal primate4 = new Primate("Dheeraj4", new Mangabey(), Sex.MALE, 19.1, 11, 9, Food.TREE_SAP, false);
    Animal primate5 = new Primate("Dheeraj5", new Tamarin(), Sex.FEMALE, 11.9, 22, 7, Food.EGGS, true);

    Sanctuary sample = new JungleFriendsPrimateSanctuary(20, Arrays.asList(120, 200, 150));
    sample.addAnimal(primate1);
    sample.addAnimal(primate2);
    sample.addAnimal(primate3);
    sample.moveAnimalToEnclosure(0);
    sample.addAnimal(primate4);
    sample.addAnimal(primate5);
    sample.moveAnimalToEnclosure(2);
    sample.moveAnimalToEnclosure(1);
    sample.moveAnimalToEnclosure(3);
    sample.moveAnimalToEnclosure(4);
    System.out.println(sample.getShoppingList());
  }
}
