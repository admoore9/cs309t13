package edu.iastate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    
    @Column(name = "start_hour")
    private int startHour;
    @Column(name = "start_minute")
    private int startMinute;
    @Column(name = "end_hour")
    private int endHour;
    @Column(name = "end_minute")
    private int endMinute;
    
    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;
    
    public Period() {}
    
    /**
     * @param startHour
     * @param startMinute
     * @param endHour
     * @param endMinute
     */
    public Period(int startHour, int startMinute, int endHour, int endMinute) {
        
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }
    /**
     * @return the startHour
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * @param startHour the startHour to set
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * @return the startMinute
     */
    public int getStartMinute() {
        return startMinute;
    }

    /**
     * @param startMinute the startMinute to set
     */
    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    /**
     * @return the endHour
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    /**
     * @return the endMinute
     */
    public int getEndMinute() {
        return endMinute;
    }

    /**
     * @param endMinute the endMinute to set
     */
    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
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
        if (endHour != other.endHour)
            return false;
        if (endMinute != other.endMinute)
            return false;
        if (startHour != other.startHour)
            return false;
        if (startMinute != other.startMinute)
            return false;
        return true;
    }
    
}