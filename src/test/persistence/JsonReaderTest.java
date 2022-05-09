package persistence;

import model.PartyPokemon;
import model.Pokemon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PartyPokemon pp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyParty() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyParty.json");
        try {
            PartyPokemon pp = reader.read();
            assertEquals(0, pp.numPokemon());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReaderGeneralParty() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralParty.json");
        try {
            PartyPokemon pp = reader.read();
            List<Pokemon> partyPokemon = pp.getPartyPokemon();
            assertEquals(3, pp.numPokemon());
            checkPokemon("Squirtle", "Water", partyPokemon.get(0).getItem(), partyPokemon.get(0));
            checkPokemon("Bulbasaur", "Grass", partyPokemon.get(1).getItem(), partyPokemon.get(1));
            checkPokemon("Charmander", "Fire", partyPokemon.get(2).getItem(), partyPokemon.get(2));
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
