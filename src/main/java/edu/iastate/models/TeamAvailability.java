package edu.iastate.models;

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
@Table(name = "TeamAvailability")
public class TeamAvailability {
    
    @Id
    @GeneratedValue
    @Column(name = "availability_id")
    private int availabilityId;
    
    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "availability")
    private Set<Day> days;
    
    public TeamAvailability() {}

    /**
     * @return the availability_id
     */
    public int getAvailabilityId() {
        return availabilityId;
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
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }
}
