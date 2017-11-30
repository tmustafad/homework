package com.hangman.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hangman.entity.Game;
import com.hangman.entity.Player;
import com.hangman.util.GameStatus;
import com.hangman.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
/**
 * Created by Turkmen on 29/11/2017
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
	public static List<String> wordPool = new ArrayList<>();

	static { // This is the word pool. There may be a few better solutions for this but due to the deadline of the homework I used this static pool of words
		wordPool.add("FOOTBALL");
		wordPool.add("TABLE");
		wordPool.add("CHAIR");
		wordPool.add("BOOK");
		wordPool.add("BLOCKCHAIN");
		wordPool.add("BITCOIN");
		wordPool.add("BLACKBOARD");
	}

	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				Configuration configuration = new Configuration()
						.configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration.getProperties());
				ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	// static persist methods for interacting with the controller for post and put requests.I used these two methods here because I havent used spring or other frameworkds for session and trnx management.
	//I do know that this is not a good solution but for catching up the deadline I have chosen the fastest way :)
	// All other get requests is handled through dao layer.

	public static void savePlayer(Player player) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		session.save(player);
		session.getTransaction().commit();

	}

	public static void createGame(Player player) {
		Session session = getSessionFactory().openSession();

		session.beginTransaction();
		Game game = new Game();
		game.setGameStatus(GameStatus.ONGOING);// while creating the game ,status is set to ONGOING.
		game.setGuesses(0);
		game.setGuessesLeft(3);
		game.setIncorrectLetters("");
		Random rand = new Random();
		String randomElement = HibernateUtil.wordPool.get(rand.nextInt(wordPool.size()));
		game.setGuessedWord(randomElement);// get the guessedword from predefined pool eachtime. Again for catching up
											// the deadline I quickly find a solution for this problem
		game.setMaskedWord(Util.generateMask(randomElement, "-", randomElement, true));// for creating game we make all
																						// the word masked.
		game.setPlayer(player);
		session.save(game);
		session.getTransaction().commit();
	}

	public static void mergeGame(Game game, String guess) {

		if (game != null && game.getGameStatus().equals(GameStatus.ONGOING)) {

			game.setMaskedWord(
					Util.generateMask(game.getMaskedWord(), guess.toUpperCase(), game.getGuessedWord(), false));
			game.setGuesses(game.getGuesses() + 1); // after each guess, increment the guess variable by one

			if (!game.getGuessedWord().toUpperCase().contains(guess.toUpperCase())) {
				game.setGuessesLeft(game.getGuessesLeft() - 1);// after each guess ,decrement the guessesleft variable
																// by one

				game.setGameStatus(game.getGuessesLeft() == 0 ? GameStatus.LOST : GameStatus.ONGOING);
				game.getIncorrectLetters().concat(guess);// if guess is not successfull ,add it to incorrect letters

			}
			if (game.getGuessedWord().toUpperCase().equals(game.getMaskedWord().toUpperCase()))// if both masked word and the real word is equal then the player won
				game.setGameStatus(GameStatus.WON);

			Session session = getSessionFactory().openSession();

			session.beginTransaction();
			session.merge(game);
			session.getTransaction().commit();
		}

		
	}
	public static void deleteGame(Game game) {
		game.setGameStatus(GameStatus.LOST);
		Session session = getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(game);
		session.getTransaction().commit();
	}
}
