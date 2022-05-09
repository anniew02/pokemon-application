package persistence;

import model.PartyPokemon;
import model.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents JsonReader that loads the file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads party Pokemon from file and returns it
    //          throws IOException if there is an error reading data from file
    public PartyPokemon read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePartyPokemon(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses party pokemon from JSON object and returns it
    private PartyPokemon parsePartyPokemon(JSONObject jsonObject) {
        PartyPokemon partyPokemon = new PartyPokemon();
        addPokemon(partyPokemon, jsonObject);
        return partyPokemon;
    }

    // MODIFIES: partyPokemon
    // EFFECTS: parses Pokemon from JSON objects and adds them to party Pokemon
    private void addPokemon(PartyPokemon partyPokemon, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Party Pokemon");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            addMon(partyPokemon, nextPokemon);
        }
    }

    // MODIFIES: partyPokemon
    // EFFECTS: parses Pokemon from JSON object and adds it to party Pokemon
    private void addMon(PartyPokemon partyPokemon, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        boolean hasItem = jsonObject.getBoolean("item");
        Pokemon pokemon = new Pokemon(name, type);
        if (hasItem) {
            pokemon.giveItem();
        }
        partyPokemon.addPokemon(pokemon);
    }
}
