package tests;

import java.util.List;

import org.junit.Test;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;

public class OfficialTests {

    @Test
    public void getAllOfficialTest() {
        MemberDao memberDao = new MemberDao();
        List<Member> officials = memberDao.getAllByUserType(UserType.OFFICIAL);
        System.out.println("Names:");
        for (Member official : officials) {
            System.out.println(official.getName());
        }
    }
}
