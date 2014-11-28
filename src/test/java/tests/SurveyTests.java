package tests;

import org.junit.Test;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Member;

public class SurveyTests {

    @Test
    public void constructorTest() {
        MemberDao playerDao = new MemberDao();
        Member p = new Member("Ames", "ames", "123");
        playerDao.save(p);
        
        AvailabilityDao aD = new AvailabilityDao();
        Availability a = new Availability();
        a.setPlayer(p);
        System.out.println(a.getPlayer().getId());
        aD.saveAvailability(a);
    }

}
