package ui;

import model.PartyPokemon;
import model.Pokemon;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Pokemon application
public class PokemonApp {
    private static final String JSON_STORE = "./data/partyPokemon.json";
    private Scanner input;
    private PartyPokemon partyPokemon;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Pokemon application
    public PokemonApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        partyPokemon = new PartyPokemon();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPokemon();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPokemon() {
        boolean keepGoing = true;
        String command = "";

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nDone!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addToParty();
        } else if (command.equals("r")) {
            removeFromParty();
        } else if (command.equals("g")) {
            giveItemTo();
        } else if (command.equals("t")) {
            takeItemFrom();
        } else if (command.equals("v")) {
            viewPartyPokemon();
        } else if (command.equals("s")) {
            savePartyPokemon();
        } else if (command.equals("l")) {
            loadPartyPokemon();
        } else {
            System.out.println("Selection invalid.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a new party of Pokemon
    private void initialize() {
        partyPokemon = new PartyPokemon();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prints the main menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Pokemon");
        System.out.println("\tr -> remove pokemon");
        System.out.println("\tg -> give item");
        System.out.println("\tt -> take item");
        System.out.println("\tv -> view party");
        System.out.println("\ts -> save party");
        System.out.println("\tl -> load party");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a Pokemon to the party
    private void addToParty() {
        Pokemon pokemonToAdd = addPokemon();
        if (partyPokemon.numPokemon() < 6) {
            partyPokemon.addPokemon(pokemonToAdd);
            if (pokemonToAdd.getName().equals("") && pokemonToAdd.getType().equals("")) {
                System.out.println("Empty slot added.");
            } else {
                System.out.println(pokemonToAdd.getName() + " has been added to the party!");
            }
        } else {
            System.out.println("Party is full... Cannot add more Pokemon.");
        }
    }

    // EFFECTS: prompts user to add Pokemon
    private Pokemon addPokemon() {
        String name = "";
        String type = "";
        System.out.println("Enter Pokemon name: ");
        name = input.next();
        System.out.println("Enter Pokemon type: ");
        type = input.next();

        return new Pokemon(name, type);
    }

    // MODIFIES: this
    // EFFECTS: removes Pokemon from party
    private void removePokemon(Pokemon pokemon) {
        if (partyPokemon.getPartyPokemon().contains(pokemon)) {
            partyPokemon.removePokemon(pokemon);
            if (!pokemon.getName().equals("")) {
                System.out.println(pokemon.getName() + " has been removed from the party.");
            } else {
                System.out.println("Removed empty string.");
            }
        }
    }

    // EFFECTS: prompts user to enter the index of the Pokemon to remove
    private void removeFromParty() {
        int position;
        System.out.println("Enter the index of Pokemon you want removed (whole number between 0-5): ");
        position = input.nextInt();

        if (0 <= position && position <= (partyPokemon.numPokemon() - 1)) {
            removePokemon(partyPokemon.getIndex(position));
        } else {
            System.out.println("No Pokemon at this index..");
        }
    }

    // REQUIRES: !hasItem
    // MODIFIES: this
    // EFFECTS: prompts user to input the index of the Pokemon that will be given an item
    private void giveItemTo() {
        int position;
        System.out.println("Enter the index of Pokemon you want to give an item to (whole number between 0-5): ");
        position = input.nextInt();

        if (0 <= position && position <= (partyPokemon.numPokemon() - 1)) {
            if (!partyPokemon.getIndex(position).getName().equals("")
                    && !partyPokemon.getIndex(position).getType().equals("")) {
                if (!partyPokemon.getIndex(position).getItem()) {
                    partyPokemon.getIndex(position).giveItem();
                    System.out.println(partyPokemon.getIndex(position).getName() + " was given an item!");
                } else {
                    System.out.println(partyPokemon.getIndex(position).getName() + " already has an item.");
                }
            } else {
                System.out.println("No Pokemon to give an item to...");
            }
        } else {
            System.out.println("No Pokemon at this index..");
        }
    }

    // REQUIRES: hasItem
    // MODIFIES: this
    // EFFECTS: prompts user to input the index of the Pokemon that will have an item taken away
    private void takeItemFrom() {
        int position;
        System.out.println("Enter the index of Pokemon you want to take an item from (whole number between 0-5): ");
        position = input.nextInt();

        if (0 <= position && position <= (partyPokemon.numPokemon() - 1)) {
            if (!partyPokemon.getIndex(position).getName().equals("")
                    && !partyPokemon.getIndex(position).getType().equals("")) {
                if (partyPokemon.getIndex(position).getItem()) {
                    partyPokemon.getPartyPokemon().get(position).takeItem();
                    System.out.println(partyPokemon.getIndex(position).getName() + "'s item was taken.");
                } else {
                    System.out.println("There is no item to take.");
                }
            } else {
                System.out.println("No Pokemon to take an item from...");
            }
        } else {
            System.out.println("No Pokemon at this index..");
        }
    }

    // EFFECTS: displays all the Pokemon in the party
    private void viewPartyPokemon() {
        System.out.println("\nParty Pokemon: ");
        for (Pokemon p : partyPokemon.getPartyPokemon()) {
            if (!p.getName().equals("") && !p.getType().equals("")) {
                System.out.println(p.getName() + " " + p.getType() + " type; hasItem = " + p.getItem());
            } else {
                System.out.println("");
            }
        }
    }

    // EFFECTS: saves the party Pokemon to file
    private void savePartyPokemon() {
        try {
            jsonWriter.open();
            jsonWriter.write(partyPokemon);
            jsonWriter.close();
            System.out.println("Saved party to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the party Pokemon from file
    private void loadPartyPokemon() {
        try {
            partyPokemon = jsonReader.read();
            System.out.println("Loaded party from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Cannot read from file: " + JSON_STORE);
        }
    }
}
