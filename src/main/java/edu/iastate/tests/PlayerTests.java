package edu.iastate.tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Player;

public class PlayerTests {

    PlayerDao playerDao;
    
    @Before
    public void setUp() throws Exception {
        playerDao = new PlayerDao();
    }

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
        Player player = playerDao.getPlayerById(17);
        System.out.println(player.getName());
    }
    
}
