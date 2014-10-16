package edu.iastate.tests;

import java.util.List;

import org.junit.Test;

import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Player;

public class PlayerTests {

//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }

    @Test
    public void returnAllPlayerTest() {
        PlayerDao playerDao = new PlayerDao();
        List<Player> players = playerDao.returnAllPlayers();
        System.out.println("Names:");
        for (Player player : players) {
            System.out.println(player.getName());
        }
    }

}
