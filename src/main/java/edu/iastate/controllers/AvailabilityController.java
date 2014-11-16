package edu.iastate.controllers;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Period;
import edu.iastate.models.Period.Slot;

@Controller
@RequestMapping("/availability")
public class AvailabilityController {

    AvailabilityDao availabilityDao;
    Availability availability;
    int memberId;

    @RequestMapping(method = RequestMethod.GET)
    public String getAvailability(Model model,
            @RequestParam(value = "id") String id) {
        availabilityDao = new AvailabilityDao();
        memberId = Integer.parseInt(id);
        availability = availabilityDao.getAvailabilityById(memberId);

        Set<Day> days = availability.getDays();
        model.addAttribute("days", days);
        return "availability";
    }

    private void addPeriodsToDay(Day day, String dayPeriods) {
        String periodsNames[] = dayPeriods.split(",");
        for (String periodName : periodsNames) {
            for (Slot slot : Period.Slot.values()) {
                if (slot.name().equals(periodName))
                    day.addToAvailablePeriods(new Period(slot).setDay(day));
            }
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody void availabilitySubmit(
            @RequestParam(value = "Monday", required = false) String mondayPeriods,
            @RequestParam(value = "Tuesday", required = false) String tuesdayPeriods,
            @RequestParam(value = "Wednesday", required = false) String wednesdayPeriods,
            @RequestParam(value = "Thursday", required = false) String thursdayPeriods,
            @RequestParam(value = "Friday", required = false) String fridayPeriods,
            @RequestParam(value = "Saturday", required = false) String saturdayPeriods,
            @RequestParam(value = "Sunday", required = false) String sundayPeriods) {

        Set<Day> newDays = new LinkedHashSet<Day>();
        if (mondayPeriods != null) {
            Day monday = new Day("Monday").setAvailability(availability);
            addPeriodsToDay(monday, mondayPeriods);
            newDays.add(monday);
        }
        if (tuesdayPeriods != null) {
            Day tuesday = new Day("Tuesday").setAvailability(availability);
            addPeriodsToDay(tuesday, tuesdayPeriods);
            newDays.add(tuesday);
        }
        if (wednesdayPeriods != null) {
            Day wednesday = new Day("Wednesday").setAvailability(availability);
            addPeriodsToDay(wednesday, wednesdayPeriods);
            newDays.add(wednesday);
        }
        if (thursdayPeriods != null) {
            Day thursday = new Day("Thursday").setAvailability(availability);
            addPeriodsToDay(thursday, thursdayPeriods);
            newDays.add(thursday);
        }
        if (fridayPeriods != null) {
            Day friday = new Day("Friday").setAvailability(availability);
            addPeriodsToDay(friday, fridayPeriods);
            newDays.add(friday);
        }
        if (saturdayPeriods != null) {
            Day saturday = new Day("Saturday").setAvailability(availability);
            addPeriodsToDay(saturday, saturdayPeriods);
            newDays.add(saturday);
        }
        if (sundayPeriods != null) {
            Day sunday = new Day("Sunday").setAvailability(availability);
            addPeriodsToDay(sunday, sundayPeriods);
            newDays.add(sunday);
        }
        
        availabilityDao.updateAvailability(newDays, memberId);

    }

}