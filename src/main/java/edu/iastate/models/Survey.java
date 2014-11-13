package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author Andrew
 *
 */

@Entity
@Table(name = "Survey")
public class Survey {

    @Id
    @GeneratedValue
    @Column(name = "survey_id")
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Player player;

    // TODO - make this accessible in tournament
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column(name = "survey_score")
    private int surveyScore = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getSurveyScore() {
        return surveyScore;
    }

    public void setSurveyScore(int surveyScore) {
        this.surveyScore = surveyScore;
    }
}
