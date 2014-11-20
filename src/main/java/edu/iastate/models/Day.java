package edu.iastate.models;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Day")
public class Day {
    
    @Id
    @GeneratedValue
    @Column(name = "day_id")
    private int day_id;
    
    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER)
    private Set<Period> periods;
    
    @ManyToOne
    @JoinColumn(name = "availability_id")
    private Availability availability;
    
    public enum WeekDay {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    };

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "name")
    private WeekDay name;

    public Day() {
        periods = new LinkedHashSet<Period>();
    }
    
    public Day(WeekDay name) {
        this.name = name;
        periods = new LinkedHashSet<Period>();
    }
    
    /**
     * @return the name
     */
    public WeekDay getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(WeekDay name) {
        this.name = name;
    }

    public Set<Period> getPeriods() {
        return periods;
    }
    
    public void setPeriods(Set<Period> updatedPeriods) {
        this.periods = updatedPeriods;
    }
    
    public void addPeriod(Period period) {
        periods.add(period);
    }
    
    public List<Period> getAvailablePeriods() {
        List<Period> availablePeriods = new ArrayList<Period>();
        for (Period period : periods) {
            if (period.isAvailable())
                availablePeriods.add(period);
        }
        return availablePeriods;
    }

    /**
     * @return the availability
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public Day setAvailability(Availability availability) {
        this.availability = availability;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Day other = (Day) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public boolean hasPeriod(Period period) {
        for (Period ePeriod : periods) {
            if (ePeriod.getSlot().equals(period.getSlot()))
                return true;
        }
        return false;
    }
}
