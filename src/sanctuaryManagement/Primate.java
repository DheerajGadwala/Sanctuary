package sanctuarymanagement;

import sanctuarymanagement.species.*;

/**
 * This class simulates Primates. Primates are a type of animals.
 *
 */
public class Primate implements Animal {

  private static int count = 0;
  private final int id;
  private final Species species;
  private final Sex sex;
  private String name;
  private double weight;
  private Size size;
  private int age;
  private Food favouriteFood;
  private boolean needsMedicalAttention;

  /**
   * This a constructor of the Primate class.
   * @param name name of the primate
   * @param species sanctuarymanagement.species of the primate
   * @param sex sex of the primate
   * @param weight weight of the primate
   * @param height height of the primate
   * @param age age of the primate
   * @param favouriteFood favourite food of the primate
   * @param needsMedicalAttention flag, to check if primate needs medical attention
   */
  public Primate(String name, Species species,
                 Sex sex, double weight,
                 double height, int age,
                 Food favouriteFood, boolean needsMedicalAttention) {
    this.id = count;
    this.name = name;
    this.species = species;
    this.sex = sex;
    this.size = findSize(height);
    this.weight = weight;
    this.age = age;
    this.favouriteFood = favouriteFood;
    this.needsMedicalAttention = needsMedicalAttention;
    count += 1;
  }

  private Size findSize(double height) {
    if (height < 10) {
      return Size.SMALL;
    }
    else if (height <= 20) {
      return Size.MEDIUM;
    }
    else {
      return Size.LARGE;
    }
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public Species getSpecies() {
    return species;
  }

  @Override
  public Sex getSex() {
    return sex;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public double getWeight() {
    return weight;
  }

  @Override
  public void setWeight(double newWeight) {
    this.weight = newWeight;
  }

  @Override
  public Size getSize() {
    return size;
  }

  @Override
  public void setSize(double height) {
    this.size = findSize(height);
  }

  @Override
  public void setSize(Size newSize) {
    this.size = newSize;
  }

  @Override
  public int getAge() {
    return age;
  }

  @Override
  public void setAge(int newAge) {
    this.age = age;
  }

  @Override
  public Food getFavouriteFood() {
    return favouriteFood;
  }

  @Override
  public void setFavouriteFood(Food newFavouriteFood) {
    this.favouriteFood = newFavouriteFood;
  }

  @Override
  public boolean getNeedsMedicalAttention() {
    return needsMedicalAttention;
  }

  @Override
  public void setNeedsMedicalAttention(boolean needsMedicalAttention) {
    this.needsMedicalAttention = needsMedicalAttention;
  }
}
