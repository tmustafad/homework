package com.hangman.util;


/**
 * Created by Turkmen on 27/11/2017
 */
// The enum where I hold the static game statuses

public enum GameStatus {

	ONGOING("ongoing"), WON("won"), LOST("lost");

	private final String value;

	private GameStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
