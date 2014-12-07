package edu.iastate.dao;

import edu.iastate.models.Game;
import edu.iastate.models.Member;
import edu.iastate.models.Message;
import edu.iastate.models.Team;

public class MessageDao extends BaseDao<Message> {

    public boolean notify(Member member,
            String message) {
        if (message.length() > 100)
            return false;
        Member intramurals = (Member) new MemberDao().getMemberByUsername("Intramurals");
        merge(new Message(message, "", intramurals, member).setSent(true));
        return true;
    }

    public void notifyGameTeams(Game game,
            String message) {
        for (Team team : game.getTeams()) {
            for (Member player : team.getPlayers()) {
                notify(player, message);
            }
        }
    }

    public void notifyTeamPlayers(Team team,
            String message) {
        for (Member player : team.getPlayers()) {
            notify(player, message);
        }
    }
}
