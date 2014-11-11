package tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.DayDao;
import edu.iastate.dao.PeriodDao;
import edu.iastate.dao.PlayerDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Period;
import edu.iastate.models.Period.Slot;
import edu.iastate.models.Player;

public class PeriodTests {
    @Test
    public void constructorTest() {
        
        PlayerDao playerDao = new PlayerDao();
        Player player = new Player("test1", "test1", "123");
        playerDao.save(player);
        
        AvailabilityDao availabilityDao = new AvailabilityDao();
        Availability availability = new Availability();
        availability.setPlayer(player);
        availabilityDao.saveAvailability(availability);
        
        DayDao dayDao = new DayDao();
        List<Day> days = Arrays.asList(new Day("Monday"), new Day("Tuesday"), new Day("Wednesday"), new Day("Thursday"), new Day("Friday"));
        for (Day day : days)
            day.setAvailability(availability);
        dayDao.saveDays(days);
        
        PeriodDao periodDao = new PeriodDao();
        for (Day day : days) {
            Period period = new Period(Slot.NINE);
            period.setDay(day);
            periodDao.savePeriod(period);
        }
        
    }
}
