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
class Period {
    
    @Id
    @GeneratedValue
    @Column(name = "period_id")
    private int period_id;
    
    @Column
    private int startHour;
    @Column
    private int startMinute;
    @Column
    private int endHour;
    @Column
    private int endMinute;
    
    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;
    
    /**
     * range for acceptable hours
     */
    private int[] range = {8, 22};
    
    public Period() {}
    
    /**
     * Period should not exceed or be below range
     * if startHour or endHour is below or above range, adjust/calibrate value to be within range
     * @throws exception if start and end are equal or start is later than end
     * @param startHour
     * @param startMinute
     * @param endHour
     * @param endMinute
     */
    public Period(int startHour, int startMinute, int endHour, int endMinute) throws Exception {
        if (startHour < range[0])
            startHour = range[0];
        if (endHour > range[1])
            endHour = range[1];
        
        if (startHour == endHour && startMinute == endMinute)
            throw new Exception("Start and end times are identical");
        if (startHour > endHour || ( startHour == endHour && startMinute > endMinute ) )
            throw new Exception("Start is later than end.");
        
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