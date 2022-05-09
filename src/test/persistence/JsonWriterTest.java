package persistence;

import model.PartyPokemon;
import model.Pokemon;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            PartyPokemon pp = new PartyPokemon();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyParty() {
        try {
            PartyPokemon pp = new PartyPokemon();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyParty.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyParty.json");
            pp = reader.read();
            assertEquals(0, pp.numPokemon());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralParty() {
        try {
            PartyPokemon pp = new PartyPokemon();
            pp.addPokemon(new Pokemon("Squirtle", "Water"));
            pp.addPokemon(new Pokemon("Bulbasaur", "Grass"));
            pp.addPokemon(new Pokemon("Charmander", "Fire"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralParty.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralParty.json");
            pp = reader.read();
            List<Pokemon> partyPokemon = pp.getPartyPokemon();
            assertEquals(3, partyPokemon.size());
            checkPokemon("Squirtle", "Water", partyPokemon.get(0).getItem(), partyPokemon.get(0));
            checkPokemon("Bulbasaur", "Grass", partyPokemon.get(1).getItem(), partyPokemon.get(1));
            checkPokemon("Charmander", "Fire", partyPokemon.get(2).getItem(), partyPokemon.get(2));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
