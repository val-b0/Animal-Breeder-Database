package main.java.com.db;

import java.util.*;

/**
 * The {@code Animal} class represents an animal in a family hierarchy.
 * <p>
 * Each animal is uniquely identified by an {@code id} and has attributes
 * such as a name, owner, parents, and children. The class provides methods
 * for accessing ancestors, descendants, and managing hierarchical relationships.
 * </p>
 *
 * <b>Main Features:</b>
 * <ul>
 *   <li>Stores the animal's name, ID, owner, parents, and children.</li>
 *   <li>Supports hierarchical operations like retrieving ancestors and descendants.</li>
 *   <li>Implements {@code Comparable} to allow ordering by ID.</li>
 * </ul>
 * <p>
 * Key Methods:
 * <ul>
 *   <li>{@link #getAncestors()} - Get this animal's ancestors.</li>
 *   <li>{@link #getDescendants()} - Get this animal's descendants.</li>
 *   <li>{@link #addChild(Animal)} - Add a child to this animal.</li>
 *   <li>{@link #getNumberOfAncestors()} - Get the count of ancestors.</li>
 *   <li>{@link #getNumberOfDescendants()} - Get the count of descendants.</li>
 * </ul>
 * <p>
 * Note: Equality of animals depends solely on their {@code id}.
 *
 * @see java.util.SortedSet
 */

public class Animal implements Comparable<Animal> {


    private final int id;
    private final String name;
    private final Animal father;
    private final Animal mother;
    private final Map<Integer, Animal> children;
    private Person owner;


    /**
     * Constructs an {@code Animal} instance with a specified name.
     * <p>
     * Default values are assigned for other attributes like owner, ID, parents, and children.
     *
     * @param name The name of the animal.
     */
    public Animal(String name) {
        this(null, 0, name, null, null, new HashMap<>());
    }

    /**
     * Constructs an {@code Animal} instance with specified owner, ID, name, and parental relationships.
     * <p>
     * Default values are assigned for the children attribute.
     *
     * @param owner  The owner of the animal.
     * @param id     The unique identifier of the animal.
     * @param name   The name of the animal.
     * @param father The father of the animal.
     * @param mother The mother of the animal.
     */
    public Animal(Person owner, int id, String name, Animal father, Animal mother) {
        this(owner, id, name, father, mother, new HashMap<>());
    }

    /**
     * Constructs an {@code Animal} instance with all attributes specified.
     *
     * @param owner    The owner of the animal.
     * @param id       The unique identifier of the animal.
     * @param name     The name of the animal.
     * @param father   The father of the animal.
     * @param mother   The mother of the animal.
     * @param children A map containing the animal's children keyed by their ID.
     */
    public Animal(Person owner, int id, String name, Animal father, Animal mother, Map<Integer, Animal> children) {
        this.owner = owner;
        this.id = id;
        this.name = name;
        this.father = father;
        this.mother = mother;
        this.children = children;
    }

    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }


    /**
     * Adds a child to this animal's list of children.
     *
     * @param child The {@code Animal} instance representing the child.
     */
    void addChild(Animal child) {
        children.put(child.getId(), child);
    }


    /**
     * Retrieves the children of this animal in a sorted set.
     * <p>
     * The children are returned in natural order, as defined by the {@code Comparable}
     * implementation of the {@code Animal} class.
     *
     * @return A {@code SortedSet} containing the children of this animal, sorted by their natural order.
     */
    public SortedSet<Animal> getChildren() {

        SortedSet<Animal> local = new TreeSet<>();
        for (Map.Entry<Integer, Animal> entry : children.entrySet()) {
            local.add(entry.getValue());
        }
        return local;
    }

    /**
     * Retrieves all ancestors of this animal.
     * <p>
     * The ancestors are ordered by their natural order, as defined by the {@code Comparable} implementation.
     *
     * @return A {@code SortedSet} of ancestors for this animal.
     */
    public SortedSet<Animal> getAncestors() {

        SortedSet<Animal> ancestors = new TreeSet<>();
        recursiveAncestorAdder(this, ancestors);
        return ancestors;
    }

    /**
     * Recursively adds ancestors of the given animal to a {@code SortedSet}.
     *
     * @param animal    The animal whose ancestors are to be added.
     * @param ancestors The {@code SortedSet} to which ancestors will be added.
     */
    private void recursiveAncestorAdder(Animal animal, SortedSet<Animal> ancestors) {
        if (animal.father != null) {
            ancestors.add(animal.father);
            recursiveAncestorAdder(animal.father, ancestors);
        }
        if (animal.mother != null) {
            ancestors.add(animal.mother);
            recursiveAncestorAdder(animal.mother, ancestors);
        }
    }

    /**
     * Retrieves all descendants of this animal.
     * <p>
     * The descendants are ordered by their natural order, as defined by the {@code Comparable} implementation.
     *
     * @return A {@code SortedSet} of descendants for this animal.
     */
    public SortedSet<Animal> getDescendants() {
        SortedSet<Animal> descendants = new TreeSet<>();
        recursiveChildrenAdder(this, descendants);

        return descendants;
    }

    /**
     * Recursively adds descendants of the given animal to a {@code SortedSet}.
     *
     * @param animal      The animal whose descendants are to be added.
     * @param descendants The {@code SortedSet} to which descendants will be added.
     */
    private void recursiveChildrenAdder(Animal animal, SortedSet<Animal> descendants) {
        for (Map.Entry<Integer, Animal> child : animal.children.entrySet()) {
            descendants.add(child.getValue());
            recursiveChildrenAdder(child.getValue(), descendants);
        }
    }

    public int getNumberOfAncestors() {
        return getAncestors().size();
    }

    public int getNumberOfDescendants() {
        return getDescendants().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Animal o) {
        if (this.id > o.id) return 1;
        if (this.id < o.id) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }
}
