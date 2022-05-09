package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of up to 6 Pokemon
public class PartyPokemon implements Writable {
    private List<Pokemon> partyPokemon;
    private EventLog eventLog;

    // EFFECTS: constructs an empty list of Pokemon
    public PartyPokemon() {
        partyPokemon = new ArrayList<>();
        eventLog = EventLog.getInstance();
    }

    // EFFECTS: returns true if there are 6 Pokemon in the party, otherwise false
    public boolean isFull() {
        return partyPokemon.size() == 6;
    }

    // REQUIRES: !isFull()
    // MODIFIES: this
    // EFFECTS: adds Pokemon to party Pokemon
    public boolean addPokemon(Pokemon pokemon) {
        if (!isFull()) {
            partyPokemon.add(pokemon);
            EventLog.getInstance().logEvent(new Event(pokemon.getName() + " was added to the party"));
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: !partyPokemon.isEmpty()
    // MODIFIES: this
    // EFFECTS: removes Pokemon from party Pokemon
    public boolean removePokemon(Pokemon pokemon) {
        EventLog.getInstance().logEvent(new Event(pokemon.getName() + " was removed from the party"));
        return partyPokemon.remove(pokemon);
    }

    // EFFECTS: returns the number of Pokemon in the party
    public int numPokemon() {
        return partyPokemon.size();
    }

    // REQUIRES: index [0, 5]
    // EFFECTS: returns the Pokemon at the given index
    public Pokemon getIndex(int index) {
        return partyPokemon.get(index);
    }

    // MODIFIES: this
    // EFFECTS: removes all Pokemon from the party
    public void clearParty() {
        partyPokemon.clear();
        EventLog.getInstance().logEvent(new Event("Party cleared"));
    }

    public List<Pokemon> getPartyPokemon() {
        EventLog.getInstance().logEvent(new Event("Viewed party"));
        return partyPokemon;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Party Pokemon", pokemonToJson());
        return json;
    }

    private JSONArray pokemonToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pokemon pkmn : partyPokemon) {
            jsonArray.put(pkmn.toJson());
        }

        return jsonArray;
    }
}
