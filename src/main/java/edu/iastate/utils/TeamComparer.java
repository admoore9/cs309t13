package edu.iastate.utils;

import java.util.Comparator;

import edu.iastate.models.Team;

/**
 * Comparator for teams based on skill level
 * 
 * @author Shubang
 *
 */
public class TeamComparer implements Comparator<Team> {

    @Override
    public int compare(Team team1, Team team2) {
        if(team1 == null && team2==null) {
            return 0;
        }
        if(team1==null) {
            return -1;
        }
        if(team2==null) {
            return 1; 
        }
        int skillCompare = compare(team1.getTeamSkillLevel(), team2.getTeamSkillLevel());
        return skillCompare;
    }
    
    private static int compare(int a, int b) {
        return (a-b);
    }
    
}
