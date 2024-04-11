package com.example.pokeapi;

import com.google.gson.annotations.SerializedName;

public class Stat {
    @SerializedName("base_stat")
    private int baseStat;

    @SerializedName("stat")
    private StatInfo statInfo;

    public int getBaseStat() {
        return baseStat;
    }

    public StatInfo getStatInfo() {
        return statInfo;
    }
}
