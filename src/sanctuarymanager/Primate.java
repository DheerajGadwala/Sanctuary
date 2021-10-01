package sanctuarymanager;

/**
 * This class simulates Primates. Primates are a type of animals.
 *
 */
class Primate implements Animal {

  private static int count = 0;
  private final int id;
  private final Genus species;
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
  public Primate(String name, PrimateGenus species,
                 Sex sex, double weight,
                 double height, int age,
                 Food favouriteFood, boolean needsMedicalAttention) {
    StringBuilder errorMessage = new StringBuilder("");
    if (name == null || name.equals("")) {
      errorMessage.append("Name can not be empty\n");
    }
    if (!(species instanceof PrimateGenus)) {
      if (species == null) {
        errorMessage.append(" Species can not be null.");
      }
      else {
        errorMessage.append(species.toString() + " does not belong to the primate genus.");
      }
    }
    if (sex == null) {
      errorMessage.append("Sex can not be null\n");
    }
    if (weight < 0) {
      errorMessage.append("Weight can not be negative\n");
    }
    if (height < 0) {
      errorMessage.append("Height can not be negative\n");
    }
    if (age < 0) {
      errorMessage.append("Age can not be negative\n");
    }
    if (favouriteFood == null) {
      errorMessage.append("Favourite food can not be null\n");
    }
    if (errorMessage.toString().compareTo("") != 0) {
      throw new IllegalArgumentException(new String(errorMessage));
    }
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
    return height < 10 ? Size.SMALL : height <= 20 ? Size.MEDIUM : Size.LARGE;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public Genus getSpecies() {
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

  @Override
  public Group getGroup() {
    return new Troop();
  }

  @Override
  public String toString() {
    String ret = String.format(
                "id: %d, species: %s,\n sex: %s,\n name: %s,"
                + "\n size: %s,\n age: %d,\n weight: %.2f,"
                + "\n favourite food: %s\n",
                id, species.toString(), sex.toString(),name,
                size.toString(), age, weight,
                favouriteFood.toString()
    );
    return ret;
  }
}
