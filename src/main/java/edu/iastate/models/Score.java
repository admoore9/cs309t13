package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Score class. Stores the score for a certain team in a certain game.
 * 
 * @author brianshannan
 */
@Entity
@IdClass(ScoreId.class)
@Table(name = "Score")
public class Score {

    @Column(name = "score")
    private int score = 0;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Score() {}

    public Score(int score, Game game, Team team) {
        this.score = score;
        this.game = game;
        this.team = team;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((game == null) ? 0 : game.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Score other = (Score) obj;
        if(game == null) {
            if(other.game != null)
                return false;
        } else if(!game.equals(other.game))
            return false;
        if(team == null) {
            if(other.team != null)
                return false;
        } else if(!team.equals(other.team))
            return false;
        return true;
    }
}
