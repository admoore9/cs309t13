package edu.iastate.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "availability")
    private Set<Day> days;
    
    public Availability() {
        days = new HashSet<Day>();
    }

    /**
     * @return the availability_id
     */
    public int getAvailability_id() {
        return availability_id;
    }

    /**
     * @return the days
     */
    public Set<Day> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(Set<Day> days) {
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
