package ui;

import model.PartyPokemon;
import model.Pokemon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents the frame to view the party Pokemon
public class ViewPartyFrame extends JFrame {
    private PartyPokemon partyPokemon;
    private JTable table;
    private List<Pokemon> pokemonList;
    private final String[] columnNames = {"Name", "Type"};

    // MODIFIES: this
    // EFFECTS: constructs a JTable that displays the playlist
    public ViewPartyFrame(PartyPokemon partyPokemon) {
        this.partyPokemon = partyPokemon;
        this.pokemonList = this.partyPokemon.getPartyPokemon();
        table = new JTable();

        createTable();
    }

    // EFFECTS: creates the table that displays the party
    public void createTable() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(columnNames);

        for (Pokemon p : this.pokemonList) {
            dtm.addRow(new Object[]{p.getName(), p.getType()});
        }

        table.setModel(dtm);
        setTitle("Party Pokemon");
        this.add(table);
        JScrollPane sp = new JScrollPane(table);
        this.add(sp);
        this.pack();
        this.setVisible(true);
    }
}
