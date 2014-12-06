package edu.iastate.dao;

import edu.iastate.models.Member;
import edu.iastate.models.Message;

public class MessageDao extends BaseDao<Message> {

    public boolean notify(Member member,
            String message) {
        if (message.length() > 100)
            return false;
        Member intramurals = (Member) new MemberDao().getMemberByUsername("Intramurals");
        merge(new Message(message, "", intramurals, member).setSent(true));
        return true;
    }
}
