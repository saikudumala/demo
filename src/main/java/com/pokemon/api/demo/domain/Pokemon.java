package com.pokemon.api.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

	private Integer id;
	private String name;
	private Integer height;
	private Integer weight;

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	private List<PokemonStat> stats;
	private List<PokemonMove> moves;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PokemonStat> getStats() {
		return stats;
	}

	public void setStats(List<PokemonStat> stats) {
		this.stats = stats;
	}

	public List<PokemonMove> getMoves() {
		return moves;
	}

	public void setMoves(List<PokemonMove> moves) {
		this.moves = moves;
	}

	@Override
	public String toString() {
		String toString ="Name: "+ this.name+
				" Height: "+ this.height+
				" Weight: "+ this.weight;
		return toString;
	}
}
