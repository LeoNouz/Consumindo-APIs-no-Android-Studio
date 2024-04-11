package com.example.pokeapi;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Pokemon {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("abilities")
    private List<Ability> abilities;

    @SerializedName("stats")
    private List<Stat> stats;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public List<Stat> getStats() {
        return stats;
    }
}
