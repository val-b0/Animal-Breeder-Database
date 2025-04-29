package main.java.com.db;

import java.util.*;

public class Person implements Comparable<Person> {
    private final String name;
    private final Map<Integer, Animal> animals;


    public Person(String name) {
        this(name, new HashMap<>());
    }

    public Person(String name, Map<Integer, Animal> animals) {
        this.name = name;
        this.animals = animals;
    }


    public String getName() {
        return name;
    }

    public SortedSet<Animal> getAnimals() {
        SortedSet<Animal> local = new TreeSet<>();
        for (Map.Entry<Integer, Animal> entry : animals.entrySet()) {
            local.add(entry.getValue());
        }
        return local;
    }

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

    void remove(Animal animal) {
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            if (entry.getValue().equals(animal)) {
                this.animals.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Person o) {
        if (this.getName().charAt(0) < o.getName().charAt(0)) return -1;
        if (this.getName().charAt(0) > o.getName().charAt(0)) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }
}
