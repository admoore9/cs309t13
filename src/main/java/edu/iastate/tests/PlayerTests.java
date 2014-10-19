package edu.iastate.tests;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Player;

public class PlayerTests {

    PlayerDao playerDao;
    
    @Before
    public void setUp() throws Exception {
        playerDao = new PlayerDao();
//        playerDao.register("Sam", "sam", "123");
    }

//    @After
//    public void tearDown() throws Exception {
//        playerDao.deletePlayerById("7");
//        playerDao.deleteMemberById("7");
//    }
//
    @Test
    public void getAllPlayersTest() {
        List<Player> players = playerDao.getAllPlayers();
        System.out.println("Names:");
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }
    
    @Test
    public void getPlayerByIdTest() {
        Player player = playerDao.getPlayerById(10);
        System.out.println(player.getName());
    }
    
//    @Test
//    public void deletePlayerByIdTest() {
//        playerDao.deletePlayerById("7");
//    }

}
