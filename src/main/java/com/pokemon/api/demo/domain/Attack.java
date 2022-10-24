package com.pokemon.api.demo.domain;

public class Attack {

	private String name;
	private Move attack;
	
	public Attack(String name, Move attack){
		this.attack = attack;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Move getAttack() {
		return attack;
	}
	public void setAttack(Move attack) {
		this.attack = attack;
	}
	
	
}
