package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    private Pokemon testPokemon;

    @BeforeEach
    void runBefore() {
        testPokemon = new Pokemon("Squirtle", "Water");
    }

    @Test
    void testConstructor() {
        assertEquals("Squirtle", testPokemon.getName());
        assertEquals("Water", testPokemon.getType());
        assertFalse(testPokemon.getItem());

        testPokemon.giveItem();
        assertTrue(testPokemon.getItem());

        testPokemon.takeItem();
        assertFalse(testPokemon.getItem());
    }

    @Test
    void testGiveItem() {
        testPokemon.giveItem();
        assertTrue(testPokemon.getItem());
    }

    @Test
    void testTakeItem() {
        testPokemon.giveItem();
        testPokemon.takeItem();
        assertFalse(testPokemon.getItem());
    }
}
