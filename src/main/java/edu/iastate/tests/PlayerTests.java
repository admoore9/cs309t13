package edu.iastate.tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Player;
import edu.iastate.models.Member.UserType;

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
        Player player = playerDao.getPlayerById(3);
        System.out.println(player.getName());
    }

    @Test
    public void savePlayerTest() {
        Player player = new Player();
        player.setName("TestSavePlayer3");
        player.setPassword("asdf");
        player.setUsername("TSP");
        player.setUserType(UserType.PLAYER);
        playerDao.savePlayer(player);
        System.out.println(playerDao.getPlayerById(7).getName());
    }

}
