package com.example.pokeapi;

import com.google.gson.annotations.SerializedName;

public class AbilityInfo {
    @SerializedName("name")
    private String name;

    // Adicione mais campos conforme necessário

    public String getName() {
        return name;
    }

    // Adicione mais getters conforme necessário
}
