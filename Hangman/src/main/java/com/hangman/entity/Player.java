package com.hangman.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;

/**
 * Created by Turkmen on 29/11/2017
 */
@Entity
@Table(name = "Players")
public class Player {
	
	
	public Player() {
		
	}

	private Integer id;
	private String name;
	private Integer age;

	public Player(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "playerId", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
