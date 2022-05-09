package persistence;

import model.Pokemon;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkPokemon(String name, String type, boolean hasItem, Pokemon pokemon) {
        assertEquals(name, pokemon.getName());
        assertEquals(type, pokemon.getType());
        assertEquals(hasItem, pokemon.getItem());
    }
}
