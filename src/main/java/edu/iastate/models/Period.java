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
        SIXPM(6, "PM"), SEVENPM(7, "PM"), EIGHTPM(8, "PM"), NINEPM(9, "PM"), TENPM(10, "PM"), ELEVENPM(11, "PM"), TWELVEPM(12, "PM");
        int hour;
        String period;
        Slot(int hour, String period) {
            this.hour = hour;
            this.period = period;
        }
        public int hour()   { return hour; }
        public String period() { return period; }
    };

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "slot")
    private Slot slot;

    @Column(name = "available")
    private boolean available;

    public Period() {}
    
    /**
     * @param slot
     */
    public Period(Slot slot) {
        this.slot = slot;
    }

    public Period(String slotName) {
        for (Slot slot : Slot.values()) {
            if (slot.name().equals(slotName))
                this.slot = slot;
        }
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
    public Period setDay(Day day) {
        this.day = day;
        return this;
    }

    /**
     * @return the slot
     */
    public Slot getSlot() {
        return slot;
    }

    /**
     * @param slot the slot to set
     */
    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    /**
     * @return the period_id
     */
    public int getPeriod_id() {
        return period_id;
    }

    /**
     * @param period_id the period_id to set
     */
    public void setPeriod_id(int period_id) {
        this.period_id = period_id;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     * @return
     */
    public Period setAvailable(boolean available) {
        this.available = available;
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