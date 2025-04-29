package main.java.com.db;

import java.util.*;

public class Database {

    private final Map<String, Person> persons;
    private final Map<Integer, Animal> animals;


    public Database() {
        this(new HashMap<>(), new HashMap<>());
    }

    public Database(Map<String, Person> persons, Map<Integer, Animal> animals) {
        this.persons = persons;
        this.animals = animals;
    }


    public void addPerson(String name) {
        Person per = new Person(name);
        this.persons.put(per.getName(), per);
    }

    public void addAnimal(Person owner, int id, String name, Animal father, Animal mother) {
        Animal newAnimal = new Animal(owner, id, name, father, mother);

        this.animals.put(newAnimal.getId(), newAnimal);
        owner.add(newAnimal);
        if (father != null) father.addChild(newAnimal);
        if (mother != null) mother.addChild(newAnimal);
    }

    public void tradeAnimal(Animal animal, Person newOwner) {
        animal.getOwner().remove(animal);
        animal.setOwner(newOwner);
        newOwner.add(animal);
    }

    public SortedSet<Person> getPersons() {
        SortedSet<Person> sortedSet = new TreeSet<>();
        for (Map.Entry<String, Person> entry : this.persons.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    public SortedSet<Person> getPersons(Comparator<Person> comparator) {
        SortedSet<Person> sortedSet = new TreeSet<>(comparator);
        for (Map.Entry<String, Person> entry : this.persons.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    public SortedSet<Animal> getAnimals() {
        SortedSet<Animal> sortedSet = new TreeSet<>();
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    public SortedSet<Animal> getAnimals(Comparator<Animal> comparator) {
        SortedSet<Animal> sortedSet = new TreeSet<>(comparator);
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            sortedSet.add(entry.getValue());
        }
        return sortedSet;
    }

    public Person getPerson(String name) {
        for (Map.Entry<String, Person> entry : this.persons.entrySet()) {
            if (entry.getKey().equals(name)) return entry.getValue();
        }
        return new Person("Null");
    }

    public Animal getAnimal(int id) {
        for (Map.Entry<Integer, Animal> entry : this.animals.entrySet()) {
            if (entry.getKey() == id) return entry.getValue();
        }
        return new Animal("Null");
    }
}
