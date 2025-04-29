package main.java.com.db;

import java.util.*;

/**
 * The {@code Database} class serves as a container for managing collections of {@link Person} and {@link Animal}.
 * <p>
 * It provides functionality to create, retrieve, and manipulate stored entities, maintaining hierarchical relationships
 * between animals and their owners.
 * </p>
 *
 * <b>Main Responsibilities:</b>
 * <ul>
 *   <li>Manage collections of people and animals.</li>
 *   <li>Facilitate addition of new entities and ownership updates.</li>
 *   <li>Provide sorted access to stored entities with optional comparators.</li>
 * </ul>
 */
public class Database {

    private final Map<String, Person> persons;
    private final Map<Integer, Animal> animals;

    /**
     * Constructs a {@code Database} with empty collections of {@link Person} and {@link Animal}.
     */
    public Database() {
        this(new HashMap<>(), new HashMap<>());
    }

    /**
     * Constructs a {@code Database} with specified collections of {@link Person} and {@link Animal}.
     *
     * @param persons A map representing the collection of people, indexed by their names.
     * @param animals A map representing the collection of animals, indexed by their unique IDs.
     */
    public Database(Map<String, Person> persons, Map<Integer, Animal> animals) {
        this.persons = persons;
        this.animals = animals;
    }

    /**
     * Adds a {@link Person} to the database.
     *
     * @param name The name of the person to be added.
     */
    public void addPerson(String name) {
        Person per = new Person(name);
        this.persons.put(per.getName(), per);
    }

    /**
     * Adds a new {@link Animal} to the database with its associated owner, parents, and hierarchical relationships.
     *
     * @param owner  The {@link Person} who owns the animal.
     * @param id     The unique identifier of the animal.
     * @param name   The name of the animal.
     * @param father The father of the animal (nullable).
     * @param mother The mother of the animal (nullable).
     */
    public void addAnimal(Person owner, int id, String name, Animal father, Animal mother) {
        Animal newAnimal = new Animal(owner, id, name, father, mother);
        this.animals.put(newAnimal.getId(), newAnimal);
        owner.add(newAnimal);
        if (father != null) father.addChild(newAnimal);
        if (mother != null) mother.addChild(newAnimal);
    }

    /**
     * Transfers the ownership of an {@link Animal} from its current owner to a specified new owner.
     *
     * @param animal   The animal to be transferred.
     * @param newOwner The new owner who will take ownership of the animal.
     */
    public void tradeAnimal(Animal animal, Person newOwner) {
        animal.getOwner().remove(animal);
        animal.setOwner(newOwner);
        newOwner.add(animal);
    }

    /**
     * Retrieves all stored {@link Person} entities in a sorted set using their natural ordering.
     *
     * @return A {@code SortedSet} containing all people, sorted in natural order.
     */
    public SortedSet<Person> getPersons() {
        SortedSet<Person> sortedSet = new TreeSet<>();
        for (Map.Entry<String, Person> entry : this.persons.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    /**
     * Retrieves all stored {@link Person} entities in a sorted set using the specified comparator.
     *
     * @param comparator The comparator to define the sorting order.
     * @return A {@code SortedSet} of people, sorted according to the specified comparator.
     */
    public SortedSet<Person> getPersons(Comparator<Person> comparator) {
        SortedSet<Person> sortedSet = new TreeSet<>(comparator);
        for (Map.Entry<String, Person> entry : this.persons.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    /**
     * Retrieves all stored {@link Animal} entities in a sorted set using their natural ordering.
     *
     * @return A {@code SortedSet} containing all animals, sorted in natural order.
     */
    public SortedSet<Animal> getAnimals() {
        SortedSet<Animal> sortedSet = new TreeSet<>();
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    /**
     * Retrieves all stored {@link Animal} entities in a sorted set using the specified comparator.
     *
     * @param comparator The comparator to define the sorting order.
     * @return A {@code SortedSet} of animals, sorted according to the specified comparator.
     */
    public SortedSet<Animal> getAnimals(Comparator<Animal> comparator) {
        SortedSet<Animal> sortedSet = new TreeSet<>(comparator);
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    /**
     * Retrieves a {@link Person} from the database by their name.
     *
     * @param name The name of the person to retrieve.
     * @return The person with the specified name, or a placeholder if not found.
     */
    public Person getPerson(String name) {
        for (Map.Entry<String, Person> entry : this.persons.entrySet()) {
            if (entry.getKey().equals(name)) return entry.getValue();
        }
        return new Person("Null");
    }

    /**
     * Retrieves an {@link Animal} from the database by its unique ID.
     *
     * @param id The ID of the animal to retrieve.
     * @return The animal with the specified ID, or a placeholder if not found.
     */
    public Animal getAnimal(int id) {
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            if (entry.getKey() == id) return entry.getValue();
        }
        return new Animal("Null");
    }
}
