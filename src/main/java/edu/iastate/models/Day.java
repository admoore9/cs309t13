package edu.iastate.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Day")
public class Day {
    
    @Id
    @GeneratedValue
    @Column(name = "day_id")
    private int day_id;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "day")
    private List<Period> availablePeriods;
    
    @ManyToOne
    @JoinColumn(name = "availability_id")
    private Availability availability;
    
    private String name;
    
    public Day() {
        availablePeriods = new ArrayList<Period>();
    }
    
    public Day(String name) {
        this.name = name;
        availablePeriods = new ArrayList<Period>();
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
     * @return the availability
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    /**
     * Add given period to available periods. 
     * If period already exists, return. 
     * @param newPeriod
     */
    public void addToAvailablePeriods(Period newPeriod) {
        if (availablePeriods.contains(newPeriod)) 
            return;
        availablePeriods.add(newPeriod);
    }
    
    public void removeFromAvailablePeriods(Period period) {
        availablePeriods.remove(period);
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

}
