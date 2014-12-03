package edu.iastate.dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Day.WeekDay;
import edu.iastate.models.Period;
import edu.iastate.models.Period.Slot;
import edu.iastate.utils.EntityManagerFactorySingleton;

public class DayDao {

    private final EntityManagerFactory entityManagerFactory;

    public DayDao() {
        this.entityManagerFactory = EntityManagerFactorySingleton.getFactory();
    }

    public DayDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Saves day to database
     *
     * @param day The day to save to the database
     */
    public Day saveDay(Day day) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Day savedDay = entityManager.merge(day);

        transaction.commit();
        entityManager.close();

        if (day.getPeriods().size() == 0) {
            LinkedHashSet<Period> periods = new LinkedHashSet<Period>();
            PeriodDao periodDao = new PeriodDao();
            for (Slot slot : Slot.values())
                periods.add(periodDao.merge(new Period(slot).setDay(savedDay)));
            savedDay.setPeriods(periods);
        }

        return savedDay;
    }

    public List<Day> saveDays(List<Day> days) {
        List<Day> savedDays = new ArrayList<Day>();

        for (Day day : days)
            savedDays.add(saveDay(day));

        return savedDays;
    }

    public Day getByNameAndAvailabilityId(WeekDay weekDay,
            Availability availability) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Day> query = entityManager.createQuery("from Day d where d.name = :name and d.availability_id = :availability_id", Day.class);
        query.setParameter("name", weekDay.ordinal());
        query.setParameter("availability_id", availability.getAvailability_id());
        Day day = query.getSingleResult();

        transaction.commit();
        entityManager.close();
        return day;
    }
}
