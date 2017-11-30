package com.hangman.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hangman.dao.GameDaoImpl;
import com.hangman.dao.PlayerDaoImpl;
import com.hangman.db.HibernateUtil;
import com.hangman.entity.Game;
import com.hangman.entity.Player;
import com.hangman.util.GameStatus;
import com.hangman.util.Util;
/**
 * Created by Turkmen on 29/11/2017
 */
@Path("baseController")
public class BaseController {

	// I have used these final variables here because I have no time to implement
	// service layer because of the homework deadline. I know this is not a good
	// usage!
	private static final PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
	private static final GameDaoImpl gameDaoImpl = new GameDaoImpl();

	@GET
	@Path("/player")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayer() {

		List<Player> players = playerDaoImpl.getAllPlayers();

		return players != null ? players : null;
	}

	@GET
	@Path("/player/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Player getPlayer(@PathParam("id") int id) {

		// List<Player> players=playerDaoImpl.getAllPlayers();
		Player player = playerDaoImpl.findById(id);
		return player != null ? player : new Player();
	}

	@POST
	@Path("createPlayer/{name}/{age}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPlayer(@PathParam("name") String name, @PathParam("age") int age) {
		Player player = new Player(name, age);
		HibernateUtil.savePlayer(player);
		return Response.status(200).type("application/json").entity("Player created Successfully").build();
	}

	@POST
	@Path("createGame/{name}/{age}/{id}") // the parameter player object should exist in the db.
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGame(@PathParam("name") String name, @PathParam("age") int age, @PathParam("id") int id) {
		Player player = playerDaoImpl.findById(id);
		HibernateUtil.createGame(player);
		return Response.status(200).type("application/json").entity("Game was started successfully.").build();
	}

	@GET
	@Path("/game")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> getGame() {

		List<Game> games = gameDaoImpl.getAllGames();

		return games != null ? games : null;
	}

	@GET
	@Path("/game/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game getGame(@PathParam("id") int id) {

		Game game = gameDaoImpl.findById(id);
		return game != null ? game : null;
	}

	@PUT
	@Path("/{id}/{guess}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game makeGuess(@PathParam("id") int id, @PathParam("guess") String guess) {

		Game game = gameDaoImpl.findById(id);
		HibernateUtil.mergeGame(game, guess);
		return game != null ? game : null;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game deleteGame(@PathParam("id") int id) {

		Game game = gameDaoImpl.findById(id);
		HibernateUtil.deleteGame(game);
		return game != null ? game : null;
	}
}
