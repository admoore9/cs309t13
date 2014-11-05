package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Availability")
public class Availability {

    @Id
    @GeneratedValue
    @Column(name = "availability_id")
    private int availability_id;
    
    @OneToOne
    @JoinColumn(name = "member_id")
    private Player player;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "availability")
    private List<Day> days;
    
    public Availability() {}

    /**
     * @return the availability_id
     */
    public int getAvailability_id() {
        return availability_id;
    }

    /**
     * @return the days
     */
    public List<Day> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(List<Day> days) {
        this.days = days;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
