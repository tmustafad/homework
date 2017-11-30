package com.hangman.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.hangman.entity.Game;

/**
 * Created by Turkmen on 29/11/2017
 */
public class GameDaoImpl extends AbstractDao<Integer, Game> implements GameDao {
	
	@Override
	public Game findById(int id) {

		return getByKey(id);
	}

	@Override
	public void saveGame(Game game) {
		persist(game);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGames() {

		Criteria criteria = createEntityCriteria();
		return (List<Game>) criteria.list();
	}

	@Override
	public void merge(Game entity) {

		 merge(entity);

	}

}
