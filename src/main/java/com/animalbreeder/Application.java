package main.java.com.animalbreeder;

import main.java.com.db.Animal;
import main.java.com.db.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedSet;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Database db = new Database();

        db.addPerson("Alf");
        db.addPerson("Beate");
        db.addPerson("David");
        db.addPerson("Christine");

        db.addAnimal(db.getPerson("Alf"), 0, "Hansi", null, null);
        db.addAnimal(db.getPerson("Beate"), 1, "Mausi", null, null);
        db.addAnimal(db.getPerson("David"), 2, "Fritzi", db.getAnimal(0), db.getAnimal(1));
        db.addAnimal(db.getPerson("David"), 3, "Frauli", db.getAnimal(0), db.getAnimal(1));
        db.addAnimal(db.getPerson("Christine"), 4, "Fratzi", db.getAnimal(0), db.getAnimal(1));
        db.addAnimal(db.getPerson("Christine"), 5, "Brummer", db.getAnimal(2), db.getAnimal(3));
        db.addAnimal(db.getPerson("Christine"), 6, "Mini", db.getAnimal(4), db.getAnimal(3));
        db.addAnimal(db.getPerson("Alf"), 7, "Jack", db.getAnimal(5), db.getAnimal(1));
        db.addAnimal(db.getPerson("Beate"), 8, "Beate the Second", db.getAnimal(0), db.getAnimal(6));

        db.tradeAnimal(db.getAnimal(0), db.getPerson("David"));

        logger.info("The descendants of Hansi (ID 0): ");
        SortedSet<Animal> descendantsHansi = db.getAnimal(0).getDescendants();
        for (Animal animal : descendantsHansi) {
            if (animal.equals(descendantsHansi.first())) logger.info("Descendants of Hansi: [{}, ", animal);
            else if (animal.equals(descendantsHansi.last())) logger.info("{}]", animal);
            else logger.info("{}, ", animal);
        }

        logger.info("The descendants of Frauli (ID 3): ");
        SortedSet<Animal> descendantsFrauli = db.getAnimal(3).getDescendants();
        for (Animal animal : descendantsFrauli) {
            if (animal.equals(descendantsFrauli.first())) logger.info("Descendants of Frauli: [{}, ", animal);
            else if (animal.equals(descendantsFrauli.last())) logger.info("{}]", animal);
            else logger.info("{}, ", animal);
        }


        logger.info("The descendants of Jack (ID 7): ");
        SortedSet<Animal> descendantsJack = db.getAnimal(7).getDescendants();
        for (Animal animal : descendantsJack) {
            if (animal.equals(descendantsJack.first())) logger.info("Descendants of Jack: [{}, ", animal);
            else if (animal.equals(descendantsJack.last())) logger.info("{}]", animal);
            else logger.info("{}, ", animal);
        }

        logger.info("------------------------------------------------------------------------");

        logger.info("Which animal of Beate has the most descendants? {}", db.getPerson("Beate").getAnimalsSortedByDescendantCount().first());
        logger.info("Which animal of Alf has the fewest ancestors? {}", db.getPerson("Alf").getAnimalsSortedByAncestorCount().last());
        logger.info("Which animal of Christine has the lexicographically smallest name? {}", db.getPerson("Christine").getAnimalsSortedByName().first());
        logger.info("Which animal has the most ancestors? {}", db.getAnimals((o1, o2) -> {
            if (o1.getNumberOfAncestors() != o2.getNumberOfAncestors())
                return o2.getNumberOfAncestors() - o1.getNumberOfAncestors();
            else if (o1.getNumberOfAncestors() == o2.getNumberOfAncestors()) return 1;
            return 0;
        }).first());

        logger.info("Which breeder has the longest name? {}", db.getPersons((o1, o2) -> {
            if (o1.getName().length() < o2.getName().length()) return -1;
            if (o1.getName().length() > o2.getName().length()) return 1;
            return 0;
        }).last());
        logger.info("Which breeder has the fewest animals? {}", db.getPersons((o1, o2) -> {
            if (o1.getAnimals().size() < o2.getAnimals().size()) return -1;
            if (o1.getAnimals().size() > o2.getAnimals().size()) return 1;
            return 0;
        }).first());
    }
}