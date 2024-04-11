package com.example.pokeapi;

import com.google.gson.annotations.SerializedName;

public class Ability {
    @SerializedName("ability")
    private AbilityInfo abilityInfo;

    // Adicione mais campos conforme necessário

    public AbilityInfo getAbilityInfo() {
        return abilityInfo;
    }

    // Adicione mais getters conforme necessário
}