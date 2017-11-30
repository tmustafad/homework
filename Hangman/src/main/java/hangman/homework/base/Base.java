package hangman.homework.base;

import java.io.IOException;
import java.net.URI;
import java.util.Random;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;

import com.hangman.db.HibernateUtil;
import com.hangman.entity.Game;
import com.hangman.entity.Player;
import com.hangman.util.GameStatus;
import com.hangman.util.Util;

/**
 * Created by Turkmen on 29/11/2017
 */
public class Base {
	// Here is the base class where there is a main method which triggers both
	// Grizzly HTTP Server and in memory db.
	// Base URI is the main uri of our app.
	public static final String BASE_URI = "http://localhost:8080/hangman/";

	public static HttpServer startServer() {
		// create the resource config that scans for our controller
		final ResourceConfig rc = new ResourceConfig().packages("com.hangman.controller");
		rc.register(JacksonFeature.class);//in order to convert json string to our java objects we use jackson
		// create and start a new instance of grizzly http server
		// exposing the application at the URI below
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	public static void initHsqlDBwithSomeData() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		// Add new Player object and Game object for populating test data
		Player plyr = new Player("BasePlayer", 25);

		session.save(plyr);

		session.getTransaction().commit();

		session.beginTransaction();
		Game game = new Game();
		game.setGameStatus(GameStatus.ONGOING);// while creating the game ,status is set to ONGOING.
		game.setGuesses(0);
		game.setGuessesLeft(3);
		game.setIncorrectLetters("");
		Random rand = new Random();
	    String randomElement = HibernateUtil.wordPool.get(rand.nextInt(HibernateUtil.wordPool.size()));
		game.setGuessedWord(randomElement);
		game.setMaskedWord(Util.generateMask(randomElement, "-",randomElement,true));
		game.setPlayer(plyr);
		session.save(game);
		session.getTransaction().commit();
		//org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:hangmanDb" }); this is hsql db viever. I use this to track the correctness of the records
	}
	
	public static void main(String[] args) throws IOException {
		
		initHsqlDBwithSomeData();
		final HttpServer server = startServer();
		System.out.println("Hangman app started ********" + BASE_URI);
		System.in.read();
		server.shutdown();
		HibernateUtil.shutdown();

	}
}
