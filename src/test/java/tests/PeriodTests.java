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

        List<Day> days = Arrays.asList(new Day("Monday"), new Day("Tuesday"),
                new Day("Wednesday"), new Day("Thursday"), new Day("Friday"),
                new Day("Saturday"), new Day("Sunday"));
        for (Day day : days)
            day.setAvailability(savedAvailability);
        savedDays = dayDao.saveDays(days);
    }

    @Test
    public void constructorTest() {
        for (Day day : savedDays) {
            Period period = new Period(Slot.NINE);
            period.setDay(day);
            periodDao.savePeriod(period);
            System.out.println(period.getSlot());
            System.out.println(Period.Slot.SIX);
            System.out.println(period.getSlot() == Period.Slot.NINE);
        }
    }

    @Test public void deleteTest() {
        Period period = new Period(Slot.TWELVE);
        period.setDay(savedDays.get(0));
        Period savedPeriod = periodDao.savePeriod(period);
        periodDao.delete(savedPeriod);
    }
}
