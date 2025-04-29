package main.java.com.db;

import java.util.*;

/**
 * The {@code Person} class represents a person who owns animals.
 * <p>
 * Each person has a name and a collection of animals they own. The class provides
 * methods for accessing the person's animals, sorting them by various criteria,
 * adding animals to the person's ownership, and removing animals.
 * </p>
 *
 * <b>Main Features:</b>
 * <ul>
 *   <li>Stores the name and animals associated with a person.</li>
 *   <li>Supports operations to sort animals by name, ancestor count, and descendant count.</li>
 *   <li>Implements {@code Comparable} to allow ordering of persons by name.</li>
 * </ul>
 *
 * @see Animal
 */
public class Person implements Comparable<Person> {
    private final String name;
    private final Map<Integer, Animal> animals;


    /**
     * Constructs a {@code Person} with the specified name and an empty collection of animals.
     *
     * @param name The name of the person.
     */
    public Person(String name) {
        this(name, new HashMap<>());
    }

    /**
     * Constructs a {@code Person} with the specified name and a given collection of animals.
     *
     * @param name    The name of the person.
     * @param animals A map of animal IDs to animals owned by the person.
     */
    public Person(String name, Map<Integer, Animal> animals) {
        this.name = name;
        this.animals = animals;
    }


    /**
     * Retrieves the name of this person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves all animals owned by this person in a sorted set.
     * <p>
     * The animals are sorted by their natural order, as defined by the {@code Comparable}
     * implementation of the {@code Animal} class.
     * </p>
     *
     * @return A {@code SortedSet} of all animals owned by this person, sorted by their natural order.
     */
    public SortedSet<Animal> getAnimals() {
        SortedSet<Animal> local = new TreeSet<>();
        for (Map.Entry<Integer, Animal> entry : animals.entrySet()) {
            local.add(entry.getValue());
        }
        return local;
    }

    /**
     * Retrieves all animals owned by this person, sorted by their names.
     * <p>
     * Animals are sorted in descending order by the length of their names. If two names
     * have the same length, they are sorted lexicographically in reverse order.
     * </p>
     *
     * @return A {@code SortedSet} of animals sorted by name.
     */
    public SortedSet<Animal> getAnimalsSortedByName() {
        SortedSet<Animal> nameSorted = new TreeSet<>((o1, o2) -> {
            if (o1.getName().length() != o2.getName().length())
                return o2.getName().length() - o1.getName().length();
            else {
                for (int i = 0; i < o1.getName().length(); i++) {
                    if (o1.getName().charAt(i) != o2.getName().charAt(i))
                        return o2.getName().charAt(i) - o1.getName().charAt(i);
                }
            }
            return 0;
        });
        for (Map.Entry<Integer, Animal> en : this.animals.entrySet()) {
            nameSorted.add(en.getValue());
        }
        return nameSorted;
    }

    /**
     * Retrieves all animals owned by this person, sorted by their ancestor count.
     * <p>
     * Animals are sorted in descending order based on the number of ancestors they have.
     * </p>
     *
     * @return A {@code SortedSet} of animals sorted by ancestor count.
     */
    public SortedSet<Animal> getAnimalsSortedByAncestorCount() {
        SortedSet<Animal> ancestorSorted = new TreeSet<>((o1, o2) -> {
            if (o1.getNumberOfAncestors() > o2.getNumberOfAncestors()) return -1;
            if (o1.getNumberOfAncestors() < o2.getNumberOfAncestors()) return 1;
            return 0;
        });
        for (Map.Entry<Integer, Animal> en : this.animals.entrySet()) {
            ancestorSorted.add(en.getValue());
        }
        return ancestorSorted;
    }

    /**
     * Retrieves all animals owned by this person, sorted by their descendant count.
     * <p>
     * Animals are sorted in descending order based on the number of descendants they have.
     * </p>
     *
     * @return A {@code SortedSet} of animals sorted by descendant count.
     */
    public SortedSet<Animal> getAnimalsSortedByDescendantCount() {
        SortedSet<Animal> descendantSorted = new TreeSet<>((o1, o2) -> {
            if (o1.getNumberOfDescendants() > o2.getNumberOfDescendants()) return -1;
            if (o1.getNumberOfDescendants() < o2.getNumberOfDescendants()) return 1;
            return 0;
        });
        for (Map.Entry<Integer, Animal> en : this.animals.entrySet()) {
            descendantSorted.add(en.getValue());
        }
        return descendantSorted;
    }

    /**
     * Adds an animal to this person's ownership.
     * <p>
     * If the animal is currently owned by another person, it is removed from the previous owner
     * before being added to this person.
     * </p>
     *
     * @param animal The animal to be added.
     */
    void add(Animal animal) {
        Person previousOwner = animal.getOwner();
        animal.setOwner(this);
        if (previousOwner != null) {
            for (Map.Entry<Integer, Animal> entry : previousOwner.animals.entrySet()) {
                if (entry.getValue().equals(animal)) {
                    previousOwner.animals.remove(entry.getKey());
                    break;
                }
            }
            this.animals.put(animal.getId(), animal);

        } else this.animals.put(animal.getId(), animal);
    }

    /**
     * Removes an animal from this person's ownership.
     *
     * @param animal The animal to be removed.
     */
    void remove(Animal animal) {
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            if (entry.getValue().equals(animal)) {
                this.animals.remove(entry.getKey());
                break;
            }
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two persons are considered equal if their names are equal.
     * </p>
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the {@code o} argument, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    /**
     * Returns a hash code value for this person.
     * <p>
     * The hash code is computed based on the person's name.
     * </p>
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Compares this person with the specified person for order.
     * <p>
     * Comparison is based on the first character of their names.
     * </p>
     *
     * @param o The person to be compared.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Person o) {
        if (this.getName().charAt(0) < o.getName().charAt(0)) return -1;
        if (this.getName().charAt(0) > o.getName().charAt(0)) return 1;
        return 0;
    }

    /**
     * Returns a string representation of this person.
     * <p>
     * The string representation consists of the person's name.
     * </p>
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return getName();
    }
}
