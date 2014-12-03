package tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.DayDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.dao.PeriodDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Member;
import edu.iastate.models.Period;
import edu.iastate.models.Period.Slot;

public class PeriodTests {

    PeriodDao periodDao;
    AvailabilityDao availabilityDao;
    MemberDao playerDao;
    Member player;
    Availability availability;
    List<Day> savedDays;
    DayDao dayDao;

    @Before
    public void setup() {
        playerDao = new MemberDao();
        availabilityDao = new AvailabilityDao();
        periodDao = new PeriodDao();
        player = playerDao.save(new Member("test1", "test1", "123"));
        availability = new Availability();
        dayDao = new DayDao();

        availability.setPlayer(player);
        Availability savedAvailability = availabilityDao
                .saveAvailability(availability);

    }

    @Test
    public void changePeriodAvailabilityTest() {
        for (Day day : savedDays) {
            System.out.println(day.getPeriods().size());
            periodDao.savePeriod(day.getPeriods().iterator().next().setAvailable(true));
        }
    }
}
