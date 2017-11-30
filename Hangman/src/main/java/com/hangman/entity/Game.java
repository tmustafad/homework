package com.hangman.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.hangman.util.GameStatus;

/**
 * Created by Turkmen on 29/11/2017
 */


@Entity
@Table(name = "Game")
public class Game {

	public Game() {

	}

	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	
	@OneToOne
	@JoinColumn(name="playerId",nullable=false)
	private Player player;

	@Column(name = "guesses")
	private int guesses;

	@Column(name = "guessesLeft")
	private int guessesLeft;

	@Column(name = "guessedWord", length = 50)
	private String guessedWord;
	
	@Column(name = "maskedWord", length = 50)
	private String maskedWord;

	@Column(name = "incorrectLetters", length = 50)
	private String incorrectLetters;

	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private GameStatus gameStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getGuesses() {
		return guesses;
	}

	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

	public int getGuessesLeft() {
		return guessesLeft;
	}

	public void setGuessesLeft(int guessesLeft) {
		this.guessesLeft = guessesLeft;
	}

	public String getGuessedWord() {
		return guessedWord;
	}

	public void setGuessedWord(String guessedWord) {
		this.guessedWord = guessedWord;
	}

	public String getIncorrectLetters() {
		return incorrectLetters;
	}

	public void setIncorrectLetters(String incorrectLetters) {
		this.incorrectLetters = incorrectLetters;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getMaskedWord() {
		return maskedWord;
	}
	
	public void setMaskedWord(String maskedWord) {
		this.maskedWord = maskedWord;
	}
	
}
