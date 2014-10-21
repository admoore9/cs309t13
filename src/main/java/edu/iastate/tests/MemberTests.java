package edu.iastate.tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;

public class MemberTests {

    MemberDao memberDao;
    
    @Before
    public void setUp() throws Exception {
        memberDao = new MemberDao();
    }

    @Test
    public void returnAllMembersTest() {
        List<Member> members = memberDao.getAllMembers();
        System.out.println("Names:");
        for (Member member : members) {
            System.out.println(member.getName());
        }
    }
    
    @Test
    public void getMemberByIdTest() {
        Member member = memberDao.getMemberById(7);
        System.out.println(member.getName());
    }

}
