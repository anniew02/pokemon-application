package ui;

import model.Event;
import model.EventLog;
import model.PartyPokemon;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main panel with all the buttons
public class MainPanel extends JPanel {
    public static final int BUTTON_WIDTH = 100;
    public static final int BUTTON_HEIGHT = 50;
    private static final String JSON_STORE = "./data/partyPokemon.json";
    private JPanel panel;
    private JButton addPokemonButton;
    private JButton clearPartyButton;
    private JButton viewPartyButton;
    private JButton savePartyButton;
    private JButton loadPartyButton;
    private JButton quitPartyButton;
    private PartyPokemon partyPokemon = new PartyPokemon();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs the main panel
    public MainPanel() {
        panel = new JPanel();
    }

    // EFFECTS: sets panel settings and interface
    public void createPanel() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout(1, 1));
        setBackground(Color.WHITE);
        createButtons();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: creates buttons on the main panel
    public void createButtons() {
        addButton();
        viewButton();
        clearButton();
        saveButton();
        loadButton();
        quitButton();
    }

    // EFFECTS: button that adds Pokemon
    public void addButton() {
        addPokemonButton = new JButton("Add");
        addPokemonButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        addPokemonButton.setFocusable(false);
        add(addPokemonButton);
        addPokemonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPokemonFrame(partyPokemon);
                JLabel addLabel = new JLabel();
                add(addLabel);
                addLabel.setText("Pokemon added!");
            }
        });
    }

    // EFFECTS: button that views the party Pokemon
    public void viewButton() {
        viewPartyButton = new JButton("View");
        viewPartyButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        viewPartyButton.setFocusable(false);
        add(viewPartyButton);
        viewPartyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPartyFrame(partyPokemon);
            }
        });
    }

    // EFFECTS: button that removes all Pokemon from the party
    public void clearButton() {
        clearPartyButton = new JButton("Clear");
        clearPartyButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        clearPartyButton.setFocusable(false);
        add(clearPartyButton);
        clearPartyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    // EFFECTS: button that saves the party Pokemon
    public void saveButton() {
        savePartyButton = new JButton("Save");
        savePartyButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        savePartyButton.setFocusable(false);
        add(savePartyButton);
        savePartyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }

    // EFFECTS: button that loads the party Pokemon
    public void loadButton() {
        loadPartyButton = new JButton("Load");
        loadPartyButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        loadPartyButton.setFocusable(false);
        add(loadPartyButton);
        loadPartyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
    }

    // EFFECTS: button that exits application
    public void quitButton() {
        quitPartyButton = new JButton("Quit");
        quitPartyButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        quitPartyButton.setFocusable(false);
        add(quitPartyButton);
        quitPartyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLog();
                System.exit(0);
            }
        });
    }

    //EFFECTS: prints event log to console
    private void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all Pokemon from the party
    private void clear() {
        partyPokemon.clearParty();
        JLabel clearLabel = new JLabel();
        add(clearLabel);
        clearLabel.setText("Cleared party.");
    }

    // MODIFIES: this
    // EFFECTS: writes the data and saves it
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(partyPokemon);
            jsonWriter.close();
            JLabel saveLabel = new JLabel();
            add(saveLabel);
            saveLabel.setText("Saved Party!");
        } catch (FileNotFoundException e) {
            JLabel notFound = new JLabel();
            panel.add(notFound);
            notFound.setText("Party not found :(");
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the saved data and loads it
    private void load() {
        try {
            partyPokemon = jsonReader.read();
            JLabel loadLabel = new JLabel();
            add(loadLabel);
            loadLabel.setText("Loaded Party!");
        } catch (IOException e) {
            JLabel cantRead = new JLabel();
            add(cantRead);
            cantRead.setText("Cannot read from file " + JSON_STORE);
        }
    }
}