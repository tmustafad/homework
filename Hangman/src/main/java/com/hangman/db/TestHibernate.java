package com.hangman.db;

import java.util.List;

import org.hibernate.Session;

import com.hangman.dao.PlayerDaoImpl;
import com.hangman.entity.Player;
/**
 * Created by Turkmen on 29/11/2017
 */
public class TestHibernate {

	// just a simple test class. ignore this :)
	
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	      session.beginTransaction();
	      // Add new Employee object
//	      Player plyr = new Player("osman",23);
//	      
//	      session.save(plyr);
//	      session.getTransaction().commit();
	      
	      PlayerDaoImpl playerDaoImpl=new PlayerDaoImpl();
	      List<Player> players=playerDaoImpl.getAllPlayers();
	      
	      System.out.println(players.stream().findFirst().get().getName());
	      
	      
	      HibernateUtil.shutdown();
		
		
	}
	
	
}
