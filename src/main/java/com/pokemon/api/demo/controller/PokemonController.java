package com.pokemon.api.demo.controller;

import com.pokemon.api.demo.domain.Move;
import com.pokemon.api.demo.domain.Pokemon;
import com.pokemon.api.demo.domain.PokemonMove;
import com.pokemon.api.demo.service.PokemonService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


@RestController
@Getter
@Setter
public class PokemonController {

    private static final Double DEFAULT_HP = 20.0;

    @Autowired
    private PokemonService pokemonService;


    @RequestMapping("/")
    public String simulatePokemonBattle() {
        List<Pokemon> pokemonList = pokemonService.loadPokemonNames().getResults();
        System.out.println("Available Pokemon:");
        for (Pokemon pokemon : pokemonList) {
            System.out.print(pokemon.getName());
            System.out.print("\t");
        }
        System.out.println("\t");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Pokemon 1 name : ");
        String pokemon1 = scanner.next();
        System.out.println("Choose Pokemon 2 name :");
        String pokemon2 = scanner.next();

        Pokemon firstPokemon = pokemonList.stream().filter(p -> p.getName().equals(pokemon1)).findFirst().orElse(null);
        Pokemon secondPokemon = pokemonList.stream().filter(p -> p.getName().equals(pokemon2)).findFirst().orElse(null);
        Pokemon winnerPokemon = battle(firstPokemon, secondPokemon);
        return winnerPokemon.getName();
    }


    @RequestMapping("/attack/{nameOrId}")
    public ResponseEntity<?> attack(@PathVariable String nameOrId) {
        Move move = pokemonService.movesLookUp(nameOrId);
        return ResponseEntity.ok(move);
    }


    public Pokemon battle(Pokemon pokemon1, Pokemon pokemon2) {
        Pokemon pokemon1WithMoves = pokemonService.pokemonLookUp(pokemon1.getName());
        Pokemon pokemon2WithMoves = pokemonService.pokemonLookUp(pokemon2.getName());
        System.out.println("Pokemon 1 details: ");
        System.out.println(pokemon1WithMoves.toString());
        System.out.println(pokemon1WithMoves.toString());
        System.out.println("Pokemon 2 details: ");
        System.out.println(pokemon2WithMoves.toString());
        System.out.println(pokemon2WithMoves.toString());

        if (pokemon1 == null || pokemon2 == null) {
            throw new NoSuchElementException("Cannot start a battle as one of the pokemon does not exist");
        }
        System.out.println("Battle Started between " + pokemon1.getName() + " and " + pokemon2.getName());
        Double pokemon1hp = DEFAULT_HP;
        Double pokemon2hp = DEFAULT_HP;
        List<PokemonMove> pokemon1Moves = pokemon1WithMoves.getMoves();
        List<PokemonMove> pokemon2Moves = pokemon2WithMoves.getMoves();
        Move pickedMove = null;
        Boolean isPokemonOneTurn = true;

        int power = 0;
        while (pokemon1hp > 0 && pokemon2hp > 0) {
            if (isPokemonOneTurn) {
                pickedMove = pokemonService.pickMoveRandomly(pokemon1Moves);

                power = pickedMove.getPower() == null ? 0 : pickedMove.getPower();
                System.out.println(pokemon1.getName() + " casted attack " + pickedMove.getName() + " with power " + power);
                pokemon2hp = pokemon2hp - (power * .1);
                isPokemonOneTurn = false;
            } else {
                pickedMove = pokemonService.pickMoveRandomly(pokemon2Moves);
                power = pickedMove.getPower() == null ? 0 : pickedMove.getPower();
                pokemon1hp = pokemon1hp - (power * .1);
                System.out.println(pokemon2.getName() + " casted attack " + pickedMove.getName() + " with power " + power);
                isPokemonOneTurn = true;
            }

        }
        String responseString;
        if (pokemon1hp > 0) {
            responseString = "Winner is " + pokemon1.getName();
            System.out.println(responseString);
            return pokemon1;

        } else {
            responseString = "Winner is " + pokemon2.getName();
            System.out.println(responseString);
            return pokemon2;


        }


    }

}
