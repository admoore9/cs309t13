package edu.iastate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Game;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;
import edu.iastate.utils.EntityManagerFactorySingleton;

/**
 * Data Access Object for the Team class, this should be used for
 * interacting with the Tournament table in the database.
 * 
 * @author shubangsridhar
 *
 */
public class TeamDao {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Standard constructor
     */
    public TeamDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    /**
     * Can use a custom EntityManagerFactory for unit testing
     * 
     * @param entityManagerFactory The factory to use to get sessions
     */
    public TeamDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Gets a list of all the teams in the database
     * 
     * @return List of all the teams
     */
    public List<Team> getAllTeams() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Team> query = entityManager.createQuery("from Team", Team.class);
        List<Team> teams = query.getResultList();
        transaction.commit();
        entityManager.close();
        return teams;
    }


    /**
     * Get a team's information using team ID
     * 
     * @param id the team id you wish to fetch
     * @param getGames Whether the games should be fetched
     * @param getTeams Whether the players should be fetched
     * @return
     */
    public Team getTeamById(int id, boolean getGames, boolean getPlayers, boolean getInvitedPlayers) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Team> query = entityManager.createQuery("select t from Team t where t.id = :id",
                Team.class);
        query.setParameter("id", id);
        Team team = query.getSingleResult();
        loadForeignKeys(team, getGames, getPlayers, getInvitedPlayers);

        transaction.commit();
        entityManager.close();
        return team;
    }

    /**
     * Saves the given team to the database
     * 
     * @param team The team to save to the database
     */
    public void saveTeam(Team team) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(team);

        transaction.commit();
        entityManager.close();
    }

    /**
     * Loads the foreign keys for a team based on the booleans
     * 
     * @param team the team to load the foreign keys for
     * @param getGames Whether to get the games for the tournament
     * @param getPlayers Whether to get the players for the tournament
     */
    private void loadForeignKeys(Team team, boolean getGames, boolean getPlayers, boolean getInvitedPlayers) {
        if(getGames) {
            loadGames(team);
        }
        if(getPlayers) {
            loadPlayers(team);
        }
        if(getInvitedPlayers) {
            loadInvitedPlayers(team);
        }
    }

    /**
     * Loads the games for a team
     * 
     * @param team the team to load games for
     */
    private void loadGames(Team team) {
        team.getGames().size();
    }

    /**
     * Loads the players on a team
     * 
     * @param team the team to load players for
     */
    private void loadPlayers(Team team) {
        team.getPlayers().size();
    }
    
    /**
     * Loads the invited players on a team
     * 
     * @param team the team to load invited players for
     */
    private void loadInvitedPlayers(Team team) {
        team.getInvitedPlayers().size();
    }
}
