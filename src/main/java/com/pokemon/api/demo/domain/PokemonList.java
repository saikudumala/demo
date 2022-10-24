package com.pokemon.api.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class PokemonList {
    private List<Pokemon> results;

    public PokemonList() {
        results = new ArrayList<>();
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}
