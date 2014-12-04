package tests;

import org.junit.Test;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Member;

public class AvailabilityTests {
    @Test
    public void constructorTest() {
        MemberDao pA = new MemberDao();
        Member p = new Member("Ames", "ames", "123");
        pA.save(p);
        
        AvailabilityDao aD = new AvailabilityDao();
        Availability availability = new Availability();
        availability.setPlayer(p);
        
        aD.saveAvailability(availability);
    }
}
