package com.pokemon.api.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)  
public class PokemonMove {
	
	private MoveLookup move;

	public MoveLookup getMove() {
		return move;
	}

	public void setMove(MoveLookup move) {
		this.move = move;
	}

}
