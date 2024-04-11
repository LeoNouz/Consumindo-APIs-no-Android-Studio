package com.example.pokeapi;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private TextView resultTextView;
    private ImageView pokemonImageView;
    private ImageView shinyImageView; // Nova ImageView para exibir a versão shiny do Pokémon

    private PokemonService pokemonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.search_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        pokemonImageView = findViewById(R.id.pokemon_image_view);
        shinyImageView = findViewById(R.id.shiny_image_view); // Inicializa a ImageView para a versão shiny

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pokemonService = retrofit.create(PokemonService.class);

        findViewById(R.id.search_button).setOnClickListener(v -> searchPokemon());
    }

    private void searchPokemon() {
        String pokemonName = searchEditText.getText().toString().toLowerCase();

        Call<Pokemon> call = pokemonService.getPokemonByName(pokemonName);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Não foi possível encontrar o Pokémon", Toast.LENGTH_SHORT).show();
                    return;
                }

                Pokemon pokemon = response.body();
                String result = "Nome: " + pokemon.getName() + "\n" +
                        "Altura: " + pokemon.getHeight() + "\n" +
                        "Peso: " + pokemon.getWeight() + "\n" +
                        "Habilidades: ";

                List<Ability> abilities = pokemon.getAbilities();
                if (abilities != null) {
                    for (int i = 0; i < abilities.size(); i++) {
                        Ability ability = abilities.get(i);
                        AbilityInfo abilityInfo = ability.getAbilityInfo();
                        if (abilityInfo != null) {
                            result += abilityInfo.getName();
                            if (i < abilities.size() - 1) {
                                result += ", ";
                            }
                        }
                    }
                }

                result += "\nStats:\n";
                List<Stat> stats = pokemon.getStats();
                if (stats != null) {
                    for (Stat stat : stats) {
                        StatInfo statInfo = stat.getStatInfo();
                        if (statInfo != null) {
                            result += statInfo.getName() + ": " + stat.getBaseStat() + "\n";
                        }
                    }
                }

                resultTextView.setText(result);

                // Carrega a imagem normal do Pokémon
                Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemon.getId() + ".png").into(pokemonImageView);

                // Carrega a imagem shiny do Pokémon
                Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + pokemon.getId() + ".png").into(shinyImageView);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
