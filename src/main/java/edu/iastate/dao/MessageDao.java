package edu.iastate.dao;

import edu.iastate.models.Member;
import edu.iastate.models.Message;

public class MessageDao extends BaseDao<Message> {

    public void notify(Member member,
            String message) {
        Member intramurals = (Member) new MemberDao().getMemberByUsername("Intramurals");
        merge(new Message(message, "", intramurals, member).setSent(true));
    }
}
