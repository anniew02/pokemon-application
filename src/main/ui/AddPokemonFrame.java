package ui;

import model.PartyPokemon;
import model.Pokemon;

import javax.swing.*;
import java.awt.*;

// represents the frame to add Pokemon in the GUI
public class AddPokemonFrame extends JFrame {
    public static final String POKEMON_NAME = "";
    public static final String POKEMON_TYPE = "";
    private PartyPokemon partyPokemon;
    private JFrame frame;
    private JPanel pokemonPanel;
    private JTextField nameField;
    private JTextField typeField;

    // EFFECTS: creates the add Pokemon frame and panel
    public AddPokemonFrame(PartyPokemon partyPokemon) {
        this.partyPokemon = partyPokemon;
        pokemonPanel = new JPanel(new GridLayout(0, 1));
        initializeFields();
        addPokemonToPanel();
        checkInputs();
    }

    // MODIFIES: this
    // EFFECTS: initializes JTextField's to input the Pokemon's name and type
    private void initializeFields() {
        nameField = new JTextField(POKEMON_NAME);
        typeField = new JTextField(POKEMON_TYPE);
    }

    // MODIFIES: this
    // EFFECTS: adds inputted Pokemon to the main panel
    public void addPokemonToPanel() {
        pokemonPanel.add(new JLabel("Name: "));
        pokemonPanel.add(nameField);
        pokemonPanel.add(new JLabel("Type: "));
        pokemonPanel.add(typeField);
//        pokemonPanel.add(new JLabel("Item: false"));
    }

    // EFFECTS: checks that the user inputted a valid name and type
    private void checkInputs() {
        int result = JOptionPane.showConfirmDialog(frame, pokemonPanel,
                "Add Pokemon", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String type = typeField.getText();
            //boolean hasItem = Boolean.toString(getItem());
            if (name == POKEMON_NAME || type == POKEMON_TYPE) {
                partyPokemon.addPokemon(new Pokemon("", ""));
            }

            Pokemon pokemon = new Pokemon(name, type);
            partyPokemon.addPokemon(pokemon);
        }
    }
}
