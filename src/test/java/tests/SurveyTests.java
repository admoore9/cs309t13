package tests;

import org.junit.Test;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Player;

public class SurveyTests {

    @Test
    public void constructorTest() {
        PlayerDao playerDao = new PlayerDao();
        Player p = new Player("Ames", "ames", "123");
        playerDao.save(p);
        
        AvailabilityDao aD = new AvailabilityDao();
        Availability a = new Availability();
        a.setPlayer(p);
        System.out.println(a.getPlayer().getId());
        aD.saveAvailability(a);
    }

}
