package com.pokemon.api.demo;

import com.pokemon.api.demo.controller.PokemonController;
import com.pokemon.api.demo.domain.Move;
import com.pokemon.api.demo.domain.Pokemon;
import com.pokemon.api.demo.service.PokemonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PokemonControllerTest {


    private PokemonService pokemonService;
    private PokemonController pokemonController;


    //PokemonController spyController;

    @BeforeEach
    void setUp() {
        pokemonController = new PokemonController();
        pokemonService = mock(PokemonService.class);
        pokemonController.setPokemonService(pokemonService);
        //spyController = spy(pokemonController);


    }


    @Test
    public void battleTest() {
        System.out.println("Battle Test Started");
        Pokemon pokemonOne = new Pokemon();
        pokemonOne.setName("bulbasaur");
        pokemonOne.setHeight(10);
        pokemonOne.setWeight(20);
        Pokemon pokemonTwo = new Pokemon();
        pokemonOne.setName("squirtle");
        pokemonOne.setHeight(15);
        pokemonTwo.setWeight(30);


        when(pokemonService.pokemonLookUp(pokemonOne.getName())).thenReturn(pokemonOne);
        when(pokemonService.pokemonLookUp(pokemonTwo.getName())).thenReturn(pokemonTwo);
        Move move1 = new Move();
        move1.setId(1);
        move1.setName("move1");
        move1.setAccuracy("accuracy1");
        move1.setPp(1);
        move1.setPriority(1);
        move1.setPower(40);
        when(pokemonService.pickMoveRandomly(pokemonOne.getMoves())).thenReturn(move1);

        Move move2 = new Move();
        move2.setId(1);
        move2.setName("move1");
        move2.setAccuracy("accuracy1");
        move2.setPp(1);
        move2.setPriority(1);
        move2.setPower(30);
        when(pokemonService.pickMoveRandomly(pokemonTwo.getMoves())).thenReturn(move2);
        Pokemon winnerPokemon = pokemonController.battle(pokemonOne, pokemonTwo);
        Assertions.assertEquals(winnerPokemon.getName(), "squirtle");


    }

}
