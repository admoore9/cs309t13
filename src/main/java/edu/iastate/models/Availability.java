package edu.iastate.models;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.OrderBy;

import edu.iastate.models.Day.WeekDay;

@Entity
@Table(name = "Availability")
public class Availability {

    @Id
    @GeneratedValue
    @Column(name = "availability_id")
    private int availability_id;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member player;

    @JsonManagedReference
    @OneToMany(mappedBy = "availability", fetch = FetchType.EAGER)
    @OrderBy(clause = "day_id")
    private Set<Day> days;
    
    public Availability() {
        days = new LinkedHashSet<Day>();
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
    public void setDays(LinkedHashSet<Day> days) {
        this.days = days;
    }

    /**
     * @return the player
     */
    public Member getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public Availability setPlayer(Member player) {
        this.player = player;
        return this;
    }

    public Day getDayByName(WeekDay name) {
        for (Day day : days) {
            if (day.getName().equals(name))
                return day;
        }
        return null;
    }
    
}
