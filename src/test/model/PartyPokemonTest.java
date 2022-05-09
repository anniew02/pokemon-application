package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PartyPokemonTest {
    private Pokemon p1 = new Pokemon("Blastoise", "Water");
    private Pokemon p2 = new Pokemon("Dragonite", "Dragon-Flying");
    private Pokemon p3 = new Pokemon("Mewtwo", "Psychic");
    private Pokemon p4 = new Pokemon("Lapras", "Water-Ice");
    private Pokemon p5 = new Pokemon("Flareon", "Fire");
    private Pokemon p6 = new Pokemon("Raichu", "Electric");
    private Pokemon p7 = new Pokemon("Vileplume", "Grass-Poison");
    private PartyPokemon testPartyPokemon;

    @BeforeEach
    void runBefore() {
        testPartyPokemon = new PartyPokemon();
    }

    @Test
    void testConstructor() {
        assertEquals(new ArrayList<Pokemon>(), testPartyPokemon.getPartyPokemon());
    }

    @Test
    void getPartyPokemonTest() {
        ArrayList<Pokemon> partyPokemon = new ArrayList<>();
        partyPokemon.add(p1);
        testPartyPokemon.addPokemon(p1);
        partyPokemon.add(p2);
        testPartyPokemon.addPokemon(p2);
        partyPokemon.add(p3);
        testPartyPokemon.addPokemon(p3);
        assertEquals(partyPokemon, testPartyPokemon.getPartyPokemon());
        assertEquals(3, partyPokemon.size());
    }

    @Test
    void testAddOnePokemon() {
        assertTrue(testPartyPokemon.addPokemon(p1));
        assertEquals(1, testPartyPokemon.numPokemon());
    }

    @Test
    void testAddManyPokemon() {;
        testPartyPokemon.addPokemon(p4);
        testPartyPokemon.addPokemon(p5);
        testPartyPokemon.addPokemon(p6);
        assertEquals(3, testPartyPokemon.numPokemon());
    }

    @Test
    void testAddTooManyPokemon() {;
        testPartyPokemon.addPokemon(p1);
        testPartyPokemon.addPokemon(p2);
        testPartyPokemon.addPokemon(p3);
        testPartyPokemon.addPokemon(p4);
        testPartyPokemon.addPokemon(p5);
        testPartyPokemon.addPokemon(p6);
        testPartyPokemon.addPokemon(p6);
        testPartyPokemon.addPokemon(p7);
        assertFalse(testPartyPokemon.addPokemon(p7));
        assertEquals(6, testPartyPokemon.numPokemon());
    }

    @Test
    void testRemovePokemon() {
        testPartyPokemon.addPokemon(p1);
        assertTrue(testPartyPokemon.removePokemon(p1));
        assertEquals(0, testPartyPokemon.numPokemon());
    }

    @Test
    void testRemovePokemonNotInParty() {
        testPartyPokemon.addPokemon(p1);
        testPartyPokemon.addPokemon(p2);
        assertFalse(testPartyPokemon.removePokemon(p3));
        assertEquals(2, testPartyPokemon.numPokemon());
    }

    @Test
    void testIsFull() {
        testPartyPokemon.addPokemon(p1);
        testPartyPokemon.addPokemon(p2);
        testPartyPokemon.addPokemon(p3);
        testPartyPokemon.addPokemon(p4);
        testPartyPokemon.addPokemon(p5);
        testPartyPokemon.addPokemon(p6);
        testPartyPokemon.addPokemon(p6);
        assertTrue(testPartyPokemon.isFull());
        assertEquals(6, testPartyPokemon.numPokemon());
    }

    @Test
    void testIsNotFull() {
        testPartyPokemon.addPokemon(p1);
        testPartyPokemon.addPokemon(p2);
        testPartyPokemon.addPokemon(p3);
        assertFalse(testPartyPokemon.isFull());
        assertEquals(3, testPartyPokemon.numPokemon());
    }

    @Test
    void testSize() {
        testPartyPokemon.addPokemon(p1);
        testPartyPokemon.addPokemon(p2);
        testPartyPokemon.addPokemon(p3);
        testPartyPokemon.addPokemon(p4);
        testPartyPokemon.addPokemon(p5);
        testPartyPokemon.addPokemon(p6);
        assertEquals(6, testPartyPokemon.numPokemon());
    }

    @Test
    void testGetIndex() {
        testPartyPokemon.addPokemon(p1);
        testPartyPokemon.addPokemon(p2);
        testPartyPokemon.addPokemon(p3);
        testPartyPokemon.addPokemon(p4);
        testPartyPokemon.addPokemon(p5);
        testPartyPokemon.addPokemon(p6);
        assertEquals(p1, testPartyPokemon.getIndex(0));
        assertEquals(p2, testPartyPokemon.getIndex(1));
        assertEquals(p3, testPartyPokemon.getIndex(2));
        assertEquals(p4, testPartyPokemon.getIndex(3));
        assertEquals(p5, testPartyPokemon.getIndex(4));
        assertEquals(p6, testPartyPokemon.getIndex(5));
    }

    @Test
    void testClearParty() {
        testPartyPokemon.addPokemon(p1);
        testPartyPokemon.addPokemon(p2);
        testPartyPokemon.addPokemon(p3);
        testPartyPokemon.addPokemon(p4);
        testPartyPokemon.addPokemon(p5);
        testPartyPokemon.addPokemon(p6);
        testPartyPokemon.clearParty();
        assertEquals(0, testPartyPokemon.numPokemon());
    }
}
