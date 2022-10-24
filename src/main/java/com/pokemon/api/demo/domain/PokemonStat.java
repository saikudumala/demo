package com.pokemon.api.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)  
public class PokemonStat {
	
	private Stat stat;
	
	@JsonProperty("base_stat")
	private Integer baseStat;
	
	public Stat getStat() {
		return stat;
	}
	public void setStat(Stat stat) {
		this.stat = stat;
	}
	public Integer getBaseStat() {
		return baseStat;
	}
	public void setBaseStat(Integer baseStat) {
		this.baseStat = baseStat;
	}
	
}
