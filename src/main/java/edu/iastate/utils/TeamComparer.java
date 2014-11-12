/**
 * 
 */
package edu.iastate.utils;

import java.util.Comparator;

import edu.iastate.models.Team;

/**
 * @author Shubang
 *
 */
public class TeamComparer implements Comparator<Team> {

    @Override
    public int compare(Team team1, Team team2) {
        if(team1==null || team2 == null) {
            return -1;
        }
        int skillCompare = compare(team1.getTeamSkillLevel(), team2.getTeamSkillLevel());
        return skillCompare !=0 ? skillCompare : 0;
    }
    
    private static int compare(int a, int b) {
        return a < b ? -1
               : a > b ? 1
               : 0;
    }
    
}
