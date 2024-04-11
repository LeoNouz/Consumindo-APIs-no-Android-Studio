package com.example.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {
    @GET("pokemon/{name}")
    Call<Pokemon> getPokemonByName(@Path("name") String name);
}
