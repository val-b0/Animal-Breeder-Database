# Animal-Breeder-Database

## Overview

The `Animal-Breeder-Database` program is a breeder management database system designed to track animals, their owners (
breeders), and their genealogical relationships. This program provides functionality for managing and querying animal
and breeder data in various ways, utilizing Java's Collections framework to simulate database functionality without
relying on SQL or an actual database.

---

## Features

- **Animal Management**
    - Add, update, and delete animals from the system.
    - Track animal details including name, species, age, and genealogy.

- **Breeder Management**
    - Add, update, and delete breeders from the database.
    - Associate breeders with the animals they own.

- **Genealogical Relationships**
    - Track parent-offspring relationships among animals.
    - Query and display genealogical trees for selected animals.

- **Data Sorting and Querying**
    - Sort animals or breeders based on attributes like name, species, or ownership.
    - Query relationships and retrieve specific subsets of data efficiently.

---

## Technologies Used

- **Language**: Java
- **Standard Library**: Java Collections Framework
- **Tools/Frameworks**: IntelliJ IDEA
- **Database**: Simulated database implementation using in-memory collections (e.g., `HashMap`, `TreeSet`, etc.).

---

## How to Run

1. Clone this repository to your local machine:
   ```bash
   git clone <repository-url>
   ```
2. Open the project in IntelliJ IDEA or your preferred Java IDE.
3. Compile and run the program by executing the `main()` method in the main class (e.g., `AnimalBreederDatabaseApp` or
   equivalent).
4. Follow the console prompts to interact with the breeder database.

---

## Example Usage

1. **Adding New Animals**:
    - Specify attributes such as name, species, and associated breeder.
2. **Adding Genealogical Relationships**:
    - Link child animals to their parents.
3. **Querying Data**:
    - Sort animals by name or species.
    - View all animals owned by a breeder or display family trees.
4. **Deleting Records**:
    - Remove breeders or animals no longer active in the system.

---

## Folder Structure

```plaintext
src/
├── animals/                # Classes for managing Animal entities
├── breeders/               # Breeder-related classes
├── relationships/          # Genealogical management classes
├── utils/                  # Utility classes like data sorting and validation
└── AnimalBreederDatabaseApp.java    # Main entry point for the program
```

---

## Future Improvements

- Integrate a proper database (e.g., MySQL, PostgreSQL) for persistent data storage.
- Add a graphical user interface (GUI) for enhanced usability.
- Allow export/import of data in formats like CSV or JSON.
- Add advanced genealogical analysis tools (e.g., inbreeding calculations, family network analysis).

