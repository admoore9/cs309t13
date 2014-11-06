package tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;
import edu.iastate.models.Member.UserType;

public class MemberTests {

    MemberDao memberDao;

    @Before
    public void setUp() throws Exception {
        memberDao = new MemberDao();
    }

//    @Test
//    public void returnAllMembersTest() {
//        List<Member> members = memberDao.getAllMembers();
//        System.out.println("Names:");
//        for (Member member : members) {
//            System.out.println(member.getName());
//        }
//    }
//
//    @Test
//    public void getMemberByIdTest() {
//        Member member = memberDao.getMemberById(1);
//        System.out.println(member.getName());
//    }
//    
//    @Test
//    public void saveMemberTest() {
//        Member member = new Member();
//        member.setName("Testing add");
//        member.setPassword("asdf");
//        member.setUserType(UserType.OFFICIAL);
//        memberDao.saveMember(member);
//    }
    
    @Test
    public void promotingTest() {
        Member member = new Member("member1", "member1", "123");
        System.out.println(member.getClass().getSimpleName());
//        memberDao.save(member);
//        member.setUserType(UserType.PLAYER);
//        memberDao.save(member);
    }

}
