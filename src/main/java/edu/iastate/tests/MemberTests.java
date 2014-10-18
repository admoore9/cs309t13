package edu.iastate.tests;

import java.util.List;

import org.junit.Test;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;

public class MemberTests {

//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }

    @Test
    public void returnAllMembersTest() {
        MemberDao memberDao = new MemberDao();
        List<Member> members = memberDao.returnAllMembers();
        System.out.println("Names:");
        for (Member member : members) {
            System.out.println(member.getName());
        }
    }

}
