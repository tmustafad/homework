package com.hangman.dao;

import java.util.List;

import com.hangman.entity.Player;
import org.hibernate.Criteria;

/**
 * Created by Turkmen on 29/11/2017
 */
public class PlayerDaoImpl extends AbstractDao<Integer, Player> implements PlayerDao{

	@Override
	public Player findById(int id) {
		return getByKey(id);
	}

	@Override
	public void savePlayer(Player player) {
		persist(player);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getAllPlayers() {
		Criteria criteria=createEntityCriteria();
		return (List<Player>)criteria.list();
	}
	
	
	

}
