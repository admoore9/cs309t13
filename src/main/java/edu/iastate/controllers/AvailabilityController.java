package edu.iastate.controllers;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Day.WeekDay;
import edu.iastate.models.Member;
import edu.iastate.models.Period;
import edu.iastate.models.Period.Slot;

@Controller
@RequestMapping("/availability")
public class AvailabilityController {

    AvailabilityDao availabilityDao;
    Availability availability;
    Set<Day> days;

    @RequestMapping(method = RequestMethod.GET)
    public String getAvailability(Model model, HttpSession session) {
        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }
        Member member = (Member) session.getAttribute("member");
        availabilityDao = new AvailabilityDao();
        availability = member.getAvailability();
        days = availability.getDays();
        model.addAttribute("days", days);
        model.addAttribute("slots", Slot.values());
        return "availability";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody boolean updateAvailability(HttpSession session,
            @RequestParam(value = "MONDAY", required = false) String monday,
            @RequestParam(value = "TUESDAY", required = false) String tuesday,
            @RequestParam(value = "WEDNESDAY", required = false) String wednesday,
            @RequestParam(value = "THURSDAY", required = false) String thursday,
            @RequestParam(value = "FRIDAY", required = false) String friday,
            @RequestParam(value = "SATURDAY", required = false) String saturday,
            @RequestParam(value = "SUNDAY", required = false) String sunday) {

        if (session.getAttribute("member") == null)
            return false;
        Member member = (Member) session.getAttribute("member");

        Day newMonday = new Day(WeekDay.MONDAY);
        Day newTuesday = new Day(WeekDay.TUESDAY);
        Day newWednesday = new Day(WeekDay.WEDNESDAY);
        Day newThursday = new Day(WeekDay.THURSDAY);
        Day newFriday = new Day(WeekDay.FRIDAY);
        Day newSaturday = new Day(WeekDay.SATURDAY);
        Day newSunday = new Day(WeekDay.SUNDAY);
        if (monday != null)
            addPeriodsToDay(newMonday, monday);
        if (tuesday != null)
            addPeriodsToDay(newTuesday, tuesday);
        if (wednesday != null)
            addPeriodsToDay(newWednesday, wednesday);
        if (thursday != null)
            addPeriodsToDay(newThursday, thursday);
        if (friday != null)
            addPeriodsToDay(newFriday, friday);
        if (saturday != null)
            addPeriodsToDay(newSaturday, saturday);
        if (sunday != null)
            addPeriodsToDay(newSunday, sunday);

        Set<Day> newDays = new LinkedHashSet<Day>();
        newDays.addAll(Arrays.asList(newMonday, newTuesday, newWednesday, newThursday, newFriday, newSaturday,
                newSunday));
        availabilityDao.update(availability, newDays);

        // update session
        session.setAttribute("member", new MemberDao().getMemberById(member.getId()));
        return true;
    }

    private void addPeriodsToDay(Day newMonday, String monday) {
        String periodsNames[] = monday.split(",");
        for (String periodName : periodsNames)
            newMonday.addPeriod(new Period(periodName).setAvailable(true));
    }
}