package sanctuarymanagement;

import javax.naming.LimitExceededException;

public class Driver {
  public static void main(String[] args) throws LimitExceededException {
    Animal sample = new Primate("Dheeraj", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    Cage s1 = new Cage();
    s1.addAnimal(sample);
    System.out.println(s1.getAnimal().toString());
  }
}
