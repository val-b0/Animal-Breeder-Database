package main.java.com.db;

import java.util.*;

public class Animal implements Comparable<Animal> {

    private final int id;
    private final String name;
    private final Animal father;
    private final Animal mother;
    private final Map<Integer, Animal> children;
    private Person owner;


    public Animal(String name) {
        this(null, 0, name, null, null, new HashMap<>());
    }

    public Animal(Person owner, int id, String name, Animal father, Animal mother) {
        this(owner, id, name, father, mother, new HashMap<>());
    }

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


    void addChild(Animal child) {
        children.put(child.getId(), child);
    }

    public SortedSet<Animal> getChildren() {
        SortedSet<Animal> local = new TreeSet<>();
        for (Map.Entry<Integer, Animal> entry : children.entrySet()) {
            local.add(entry.getValue());
        }
        return local;
    }

    public SortedSet<Animal> getAncestors() {
        SortedSet<Animal> ancestors = new TreeSet<>();
        recursiveAncestorAdder(this, ancestors);
        return ancestors;
    }

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

    public SortedSet<Animal> getDescendants() {
        SortedSet<Animal> descendants = new TreeSet<>();
        recursiveChildrenAdder(this, descendants);

        return descendants;
    }

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
