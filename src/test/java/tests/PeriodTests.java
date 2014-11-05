package tests;

import org.junit.Test;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.DayDao;
import edu.iastate.dao.PeriodDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Period;
import edu.iastate.models.Player;

public class PeriodTests {
    @Test
    public void constructorTest() {
        
        PlayerDao playerDao = new PlayerDao();
        Player player = new Player("Muhammad", "muhammad", "123");
        playerDao.savePlayer(player);
        
        AvailabilityDao availabilityDao = new AvailabilityDao();
        Availability availability = new Availability();
        availability.setPlayer(player);
        availabilityDao.saveAvailability(availability);
        
        DayDao dayDao = new DayDao();
        Day day = new Day("Monday");
        day.setAvailability(availability);
        dayDao.saveDay(day);
        
        PeriodDao periodDao = new PeriodDao();
        Period period = new Period(9, 9, 10, 10);
        period.setDay(day);
        periodDao.savePeriod(period);
        
    }
}
