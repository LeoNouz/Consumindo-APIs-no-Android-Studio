package com.example.pokeapi;

import com.google.gson.annotations.SerializedName;

public class StatInfo {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
