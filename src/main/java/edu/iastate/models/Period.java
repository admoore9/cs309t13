package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Period")
public class Period {
    
    @Id
    @GeneratedValue
    @Column(name = "period_id")
    private int period_id;
    
    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;
    
    public enum Slot {
        SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE
    };

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "slot")
    private Slot slot;
    
    public Period() {}
    
    /**
     * @param slot
     */
    public Period(Slot slot) {
        this.slot = slot;
    }

    /**
     * @return the day
     */
    public Day getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Period other = (Period) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (slot != other.slot)
            return false;
        return true;
    }

}