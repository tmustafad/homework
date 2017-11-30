package com.hangman.dao;

import java.util.List;

import com.hangman.entity.Game;

/**
 * Created by Turkmen on 29/11/2017
 */
public interface GameDao {

	Game findById(int id);

	void saveGame(Game game);
	void merge(Game game);

	List<Game> getAllGames();

}
