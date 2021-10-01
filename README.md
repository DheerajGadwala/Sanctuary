# PDP_Project_01


### 1. About
In this project we use the principles of object oriented programming to implement a solution for the management of a primate sanctuary.

### 2. List of features:
##### 1. We have a package private animal interface which is implemented by the Primate class.
  * A client need not concern himself with the existance of this interface. 
  * Each animal has various attributes like age, weight, height, size, favourite food, sex, species, etc.
  * We use enums for size, food and sex.
  * Since there are a lot of species and new species might be added later on, we use a Genus interface to represent species.
  * There are many Genus. In this project we are concerned with the PrimateGenus.
  * PrimateGenus is an abstract class that implements Genus.
  * all species that belong to the Primate Genus extend the PrimateGenus abstract class.
  * So we have Drill, Tamarin, Spider, Saki, etc... extending PrimateGenus.
  * To create a Primate object, the input species must be an instance of PrimateGenus.
  * We have a group abstract class, which represents a group of animals.
  * A troop is a type of group, specifically for primates. The troop class extends Group.
  ##### 2. We have a housing interface
  * The Housing interface is extended by the cage class. Each cage can store one animal.
  * Then we have a multipleHousing interface extending the housing interface, which as the name conveys, can house multiple animals.
  * We have 2 types of multiple housing:
    * Isolation: which contains multiple cages.
    * Enclosure: which has a capacity in area and can store multiple animals. Animals are assigned an area based on their size.
  * Multiple Housing objects can generate various lists: Alphabetically sorted species list, Alphabetically sorted name list and shopping list.
  ##### 3. We have a sanctuary abstract class that is extended by the PrimateSanctuary class.
  * A PrimateSanctuary has an Isolation Area and multiple enclosures.
  * The Isolation contains multiple cages. Each cage can house an animal.
  * Each Enclosure is made for a certain species, though it can be repurposed for other species if needed.
  * In a PrimateSanctuary, we enforce the condition that any animal added is of the Primate instance, and any enclosure added should be purposed for the PrimateGenus.
  * We can repurpose the enclosure if they are empty, but since this is a 'primate' sanctuary, we make sure that the new species is of the PrimateGenus instance.
  * We the client requests lists from this object, this inturn asks lists from its isolation and eclosures, then sorts and returns them.
  * A PrimateSanctuary can exchange its primates with other primate sanctuaries.

### 3. How to run:
  * the jar file is a simple test run.

### 4. Description of examples:
    Constructing a Primate Sanctuary:
    
    PrimateSanctuary sample = new PrimateSanctuary(20, Arrays.asList(16, 200, 150), Arrays.asList(new Drill(), new Tamarin(), new Mangabey()));
    argument 1: Isolation capacity
    argument 2: Areas of Enclosures
    argument 3: Default species of enclosures
    
    Adding an animal:
    
    sample.addAnimal("Dheeraj1", new Tamarin(), Sex.MALE, 15.6, 14, 8, Food.EGGS, false);
    argument 1: name ==> String
    argument 2: species ==> Species that have been implemented(drill, guereza, howler, mangabey, saki, spider, squirrel, and tamarin)
    argument 3: sex ==> enum Sex ->{MALE, FEMLAE}
    argument 4: weight ==> double
    argument 5: height ==> double
    argument 6: age ==> int
    argument 8: favourite food == > enum Food ->{EGGS, FRUITS, INSECTS, LEAVES, NUTS, SEEDS, TREE_SAP}
    
    an id is generated internally

###### these are the structures that a client will encounter. There are no other objects that are required to understand the driver.
###### Most methods in the PrimateSanctuary class return string as output and are easy to understand.
###### Used Structured data for internal operations.

### 6. Design changes:
###### added Genus, PrimateGenus, Group so that we can create classes for other animals without changing the current code.
###### made sanctuary abstractClass instead of interface, A lot of common code would be seen if we create other types of sanctuaries, like PatheraSanctuary for The Panthera Genus will have a lot of common code with the PrimateSanctuary.
###### Did not use enum for species, instead used classes and interfaces so they can be easily extended in the future without need for modification.
###### Added sign and signUNit for internal representation of Enclosure signs.

### 7. Assumptions:
###### That all sanctuaries follow the same model of having an isolation unit and a bunch of enclosures.
###### That a genus has a group name. For instance, all primate groups are called troops. IRL this is not always true.

### 8. How can this project be extened:
###### We can create different kinds of sanctuaries, even those that take totally different genus of animals.
###### We can create animals of different genuses and sill differentiate them.
###### We can create different groups for different genuses.

### 9. Citations:
###### [Genus Wiki](https://en.wikipedia.org/wiki/Genus)
###### [Effective Java](https://learning.oreilly.com/library/view/effective-java/9780134686097/)
