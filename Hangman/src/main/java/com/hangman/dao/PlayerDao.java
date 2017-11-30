package com.hangman.dao;

import java.util.List;

import com.hangman.entity.Player;


/**
 * Created by Turkmen on 29/11/2017
 */

public interface PlayerDao {
	
	Player findById(int id);
	
	void savePlayer(Player player);

	List<Player> getAllPlayers();
}
