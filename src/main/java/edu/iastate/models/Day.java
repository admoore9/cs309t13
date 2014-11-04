package edu.iastate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Day")
class Day {
    
    @Id
    @GeneratedValue
    @Column(name = "day_id")
    private int day_id;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day")
    private List<Period> availablePeriods;
    
    @ManyToOne
    @JoinColumn(name = "availability_id")
    private Availability availability;
    
    private String name;
    
    public Day() {}
    
    public Day(String name) {
        this.name = name;
    }
    public List<Period> getAvailablePeriods() {
        return availablePeriods;
    }
    public void setAvailablePeriods(List<Period> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Add given period to available periods. 
     * If period already exists, does not do anything. 
     * If period overlaps with other period(s), merges period with overlapping period(s).
     * @param newPeriod
     */
    public void addAvailablePeriod(Period newPeriod) {
        if (availablePeriods.contains(newPeriod)) 
            return;
        for (Period availablePeriod : availablePeriods) {
            // If start of existing period begins inside new period, change start of existing period to the start of new period
            if (availablePeriod.getStartHour() >= newPeriod.getStartHour() && availablePeriod.getStartHour() <= newPeriod.getEndHour()) {
                availablePeriod.setStartHour(newPeriod.getStartHour());
                // If end of existing period is earlier than new period, change it to the value of the new period
                if (availablePeriod.getEndHour() < newPeriod.getEndHour())
                    availablePeriod.setEndHour(newPeriod.getEndHour());
                return;
            }
            // If start of new period begins inside existing period
            else if (newPeriod.getStartHour() >= availablePeriod.getStartHour() && newPeriod.getStartHour() <= availablePeriod.getEndHour()) {
                // if end of existing period is earlier than the end of new period, change end of existing period to the end of new period
                if (availablePeriod.getEndHour() < newPeriod.getEndHour())
                    availablePeriod.setEndHour(newPeriod.getEndHour());
                return;
            }
        }
        // No overlap between existing available periods and new period
        availablePeriods.add(newPeriod);
    }
    
    public void removeAvailablePeriod(Period period) {
        availablePeriods.remove(period);
    }
    
    public void modifyAvailablePeriod(Period oldPeriod, Period newPeriod) {
        availablePeriods.remove(oldPeriod);
        availablePeriods.add(newPeriod);
    }
}
