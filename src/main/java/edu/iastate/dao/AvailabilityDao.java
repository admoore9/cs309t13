package edu.iastate.dao;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Day.WeekDay;
import edu.iastate.models.Member;
import edu.iastate.models.Period;
import edu.iastate.models.Team;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class AvailabilityDao {

    private final EntityManagerFactory entityManagerFactory;
    
    public AvailabilityDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }
    
    public AvailabilityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    
    /**
     * Saves the given availability to the database
     * 
     * @param member The member to save to the database
     */
    public Availability saveAvailability(Availability availability) {
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Availability savedAvailability = entityManager.merge(availability);
        
        transaction.commit();
        entityManager.close();

        if (savedAvailability.getDays().size() == 0) {
            LinkedHashSet<Day> days = new LinkedHashSet<Day>();
            DayDao dayDao = new DayDao();
            for (WeekDay weekday : WeekDay.values())
                days.add(dayDao.saveDay(new Day(weekday).setAvailability(savedAvailability)));
            savedAvailability.setDays(days);
        }
        return savedAvailability;
    }

    public Availability getAvailabilityById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Availability> query = entityManager.createQuery("from Availability a where a.availability_id = :id", Availability.class);
        query.setParameter("id", id);
        Availability availability = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return availability;
    }

    public void update(Availability availability, Set<Day> newDays) {
        PeriodDao periodDao = new PeriodDao();
        for (Day newDay : newDays) {
            Day day = availability.getDayByName(newDay.getName());
            for (Period period : day.getPeriods()) {
                if (newDay.hasPeriod(period)) {
                    period.setAvailable(true).setDay(day);
                    periodDao.update(period);
                }
                else {
                    period.setAvailable(false).setDay(day);
                    periodDao.update(period);
                }
            }
        }
    }

    public Availability getTeamAvailability(Team team) {
        Availability teamAvailability = initializeAvailability(new Availability());
        Set<Member> players = team.getPlayers();
        for (Member player : players) {
            Availability playerAvailability = player.getAvailability();
            for (Day day : playerAvailability.getDays())
                for (Period period : day.getPeriods())
                    if (!period.isAvailable())
                        teamAvailability.setPeriodAvailability(period, false);
        }
        return teamAvailability;
    }

    private Availability initializeAvailability(Availability availability) {
        LinkedHashSet<Day> days = new LinkedHashSet<Day>();
        for (Day.WeekDay weekDay : Day.WeekDay.values()) {
            Day day = new Day(weekDay);
            for (Period.Slot slot : Period.Slot.values())
                day.addPeriod(new Period(slot).setAvailable(true));
            days.add(day);
        }
        availability.setDays(days);
        return availability;
    }
}