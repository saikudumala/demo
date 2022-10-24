package com.pokemon.api.demo.service;

import com.pokemon.api.demo.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class PokemonService {
    public static Map<Integer, Pokemon> pokemonMap = new HashMap<>();
    Map<Integer, Move> pokemonMovesMap = new HashMap<>();

    Map<String, Integer> pokemonNameMapping = new HashMap<>();
    Map<String, Integer> moveNameMapping = new HashMap<>();

    public Pokemon pokemonLookUp(String name) {


        Pokemon pokemon = null;


        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Pokemon> pokemonResponse = restTemplate.getForEntity("https://pokeapi.co/api/v2/pokemon/" + name
                    , Pokemon.class);
            pokemon = pokemonResponse.getBody();
            pokemonMap.put(pokemon.getId(), pokemon);
            pokemonNameMapping.put(pokemon.getName(), pokemon.getId());
        } catch (Exception e) {
            return pokemon;
        }


        return pokemon;
    }

    public Move movesLookUp(String nameOrId) {

        Move move = null;

        try {
            if (StringUtils.isNumeric(nameOrId)) {
                move = pokemonMovesMap.get(Integer.parseInt(nameOrId));
            } else {
                Integer id = moveNameMapping.get(nameOrId);
                move = pokemonMovesMap.get(id);
            }
        } catch (NumberFormatException e) {
            return move;
        }
        if (move == null) {
            RestTemplate restTemplate = new RestTemplate();
            try {
                ResponseEntity<Move> moveResponse = restTemplate.getForEntity("https://pokeapi.co/api/v2/move/" + nameOrId
                        , Move.class);
                move = moveResponse.getBody();

                pokemonMovesMap.put(move.getId(), move);
                moveNameMapping.put(move.getName(), move.getId());
            } catch (Exception E) {
                return move;
            }
        }

        return move;
    }


    public Move pickMoveRandomly(List<PokemonMove> moves) {

        Random random = new Random();
        int moveNumber = random.nextInt(moves.size());
        PokemonMove chosenMove = moves.get(moveNumber);
        MoveLookup moveLookup = chosenMove.getMove();
        return movesLookUp(moveLookup.getName());

    }

    public PokemonList loadPokemonNames() {
        RestTemplate restTemplate = new RestTemplate();
        PokemonList stringResponse = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?limit=50&offset=0", PokemonList.class);
        return stringResponse;


    }
}
