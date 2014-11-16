package tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;

public class AdminTests {

    MemberDao MemberDao;

    @Before
    public void setUp() throws Exception {
        MemberDao = new MemberDao();
        Member Member = new Member("Sam", "sam", "123");
        MemberDao.save(Member);
    }

    @Test
    public void changeCurrentView() {
        Member Member = MemberDao.getMemberById(1);
        System.out.println("Name: " + Member.getName() + " View: " + Member.getCurrentView());
        Member.setCurrentView(UserType.PLAYER);
        System.out.println("Name: " + Member.getName() + " View: " + Member.getCurrentView());
        MemberDao.save(Member);
    }

    @Test
    public void getAllMembersTest() {
        List<Member> Members = MemberDao.getAllMembers();
        System.out.println("Names:");
        for (Member Member : Members) {
            System.out.println(Member.getName());
        }
    }

}
