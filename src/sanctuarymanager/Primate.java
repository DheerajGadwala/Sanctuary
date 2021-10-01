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
  private double height;
  private Size size;
  private int age;
  private Food favouriteFood;
  private boolean needsMedicalAttention;

  /**
   * This a constructor of the Primate class.
   * @param name name of the primate
   * @param species species of the primate
   * @param sex sex of the primate
   * @param weight weight of the primate
   * @param height height of the primate
   * @param age age of the primate
   * @param favouriteFood favourite food of the primate
   * @param needsMedicalAttention flag, to check if primate needs medical attention
   * @throws IllegalArgumentException when invalid arguments are received
   */
  public Primate(String name, PrimateGenus species,
                 Sex sex, double weight,
                 double height, int age,
                 Food favouriteFood, boolean needsMedicalAttention)
      throws IllegalArgumentException {
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
    this.height = height;
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
  public void setName(String name) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name can not be empty.");
    }
    this.name = name;
  }

  @Override
  public double getWeight() {
    return weight;
  }

  @Override
  public void setWeight(double newWeight) throws IllegalArgumentException {
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight can not be non positive.");
    }
    this.weight = newWeight;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public void setHeight(double newHeight) throws IllegalArgumentException {
    height = newHeight;
  }

  @Override
  public Size getSize() {
    return size;
  }

  @Override
  public void setSize(double height) throws IllegalArgumentException {
    if (height <= 0) {
      throw new IllegalArgumentException("height can not be non positive.");
    }
    this.size = findSize(height);
  }

  @Override
  public void setSize(Size newSize) throws IllegalArgumentException {
    if (newSize == null) {
      throw new IllegalArgumentException("size can not be null");
    }
    this.size = newSize;
  }

  @Override
  public int getAge() {
    return age;
  }

  @Override
  public void setAge(int newAge)  throws IllegalArgumentException  {
    if (age < 0) {
      throw new IllegalArgumentException("age can not be less than zero.");
    }
    this.age = age;
  }

  @Override
  public Food getFavouriteFood() {
    return favouriteFood;
  }

  @Override
  public void setFavouriteFood(Food newFavouriteFood) throws IllegalArgumentException {
    if (newFavouriteFood == null) {
      throw new IllegalArgumentException("Favourite food can not be null.");
    }
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
  public Primate shallowCopy() throws IllegalArgumentException {
    return new Primate(
        getName(),
        (PrimateGenus) getSpecies(),
        getSex(),
        getWeight(),
        getHeight(),
        getAge(),
        getFavouriteFood(),
        getNeedsMedicalAttention()
    );
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
