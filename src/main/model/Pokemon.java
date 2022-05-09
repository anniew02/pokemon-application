package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a Pokemon
public class Pokemon implements Writable {
    private String name;
    private String type;
    private boolean hasItem;

    // EFFECTS: constructs a Pokemon with a name and type, and no item to start
    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
        this.hasItem = false;
    }

    // REQUIRES: !hasItem
    // MODIFIES: this
    // EFFECTS: gives an item to a Pokemon
    public void giveItem() {
        this.hasItem = true;
    }

    // REQUIRES: hasItem
    // MODIFIES: this
    // EFFECTS: takes an item from a Pokemon
    public void takeItem() {
        this.hasItem = false;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getItem() {
        return this.hasItem;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("item", this.hasItem);
        return json;
    }
}
