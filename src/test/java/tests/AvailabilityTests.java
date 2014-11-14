package tests;

import org.junit.Test;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Player;

public class AvailabilityTests {
    @Test
    public void constructorTest() {
        PlayerDao pA = new PlayerDao();
        Player p = new Player("Ames", "ames", "123");
        pA.save(p);
        
        AvailabilityDao aD = new AvailabilityDao();
        Availability availability = new Availability();
        availability.setPlayer(p);
        
        aD.saveAvailability(availability);
    }
}
