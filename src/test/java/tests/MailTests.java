package tests;

import org.junit.Before;
import org.junit.Test;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Mail;
import edu.iastate.models.Member;

public class MailTests {

    MemberDao memberDao;
    Member member;

    @Before
    public void setUp() throws Exception {
        memberDao = new MemberDao();

        member = memberDao.getMemberById(1);
    }

    @Test
    public void retrieveMail() {
        Mail mail = member.getMail();
        mail.getUnviewedMessages();
        System.out.println(mail);
        // List<Member> Members = MemberDao.getAllMembers();
        // System.out.println("Names:");
        // for (Member Member : Members) {
        // System.out.println(Member.getName());
        // }
    }

}
