package edu.iastate.models;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Availability")
public class Availability {

    @Id
    @GeneratedValue
    @Column(name = "availability_id")
    private int availability_id;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "availability")
    private ArrayList<Day> days;
    
    public Availability() {
        // initialize days list
        days = new ArrayList<Day>(Arrays.asList(new Day("Monday"), new Day("Tuesday"), new Day("Wednesday"), new Day("Thursday"), new Day("Friday"), new Day("Saturday"), new Day("Sunday")));
        
    }
    
}
