package edu.iastate.controllers;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.dao.MemberDao;
import edu.iastate.dao.TeamDao;
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Day;
import edu.iastate.models.Tournament;
import edu.iastate.models.Day.WeekDay;
import edu.iastate.models.Member;
import edu.iastate.models.Period;
import edu.iastate.models.Member.UserType;
import edu.iastate.models.Period.Slot;
import edu.iastate.models.Team;

@Controller
@RequestMapping("/availability")
public class AvailabilityController {

    AvailabilityDao availabilityDao;
    Availability availability;
    Set<Day> days;

    /**
     * Returns the view for the availability page
     * 
     * @param model The model for the view
     * @param session The http session
     * @return The jsp page for the view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAvailability(
            Model model,
            HttpSession session,
            @RequestParam(value = "createTeam", required = true) boolean createTeam,
            @RequestParam(value = "teamId", required = false) int teamId) {

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        if (member == null) {
            return "redirect:/denied";
        }
        member = memberDao.getMemberById(member.getId());

        availabilityDao = new AvailabilityDao();
        availability = member.getAvailability();
        days = availability.getDays();
        model.addAttribute("days", days);
        model.addAttribute("slots", Slot.values());

        model.addAttribute("createTeam", createTeam);
        model.addAttribute("teamId", teamId);

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        session.setAttribute("member", memberDao.getMemberById(member.getId()));

        return "availability";
    }

    /**
     * Returns the view for the team availability by id
     * 
     * @param model The model for the view
     * @param session The http session
     * @param id The id of the team
     * @return The jsp page for the view
     */
    @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
    public String getTeamAvailability(Model model,
            HttpSession session,
            @PathVariable int id) {
        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        if (member == null) {
            return "redirect:/denied";
        }
        member = memberDao.getMemberById(member.getId());
        if (member.getContext() != UserType.ADMIN) {
            return "redirect:/denied";
        }

        availabilityDao = new AvailabilityDao();
        Team team = new TeamDao().getTeamById(id, false, true, false);
        availability = team.getTeamAvailability(team);
        days = availability.getDays();
        model.addAttribute("days", days);
        model.addAttribute("slots", Slot.values());
        model.addAttribute("isTeam", true);

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        session.setAttribute("member", member);
        return "availability";
    }

    /**
     * Updates the availability
     * 
     * @param model The model for the view
     * @param session The http session
     * @param monday The Monday of the week
     * @param tuesday The Tuesday of the week
     * @param wednesday The Wednesday of the week
     * @param thursday The Thursday of the week
     * @param friday The Friday of the week
     * @param saturday The Saturday of the week
     * @param sunday The Sunday of the week
     * @return redirects to team page if update was successful
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateAvailability(Model model, HttpSession session,
            // @PathVariable int teamId,
            @RequestParam(value = "MONDAY", required = false) String monday,
            @RequestParam(value = "TUESDAY", required = false) String tuesday,
            @RequestParam(value = "WEDNESDAY", required = false) String wednesday,
            @RequestParam(value = "THURSDAY", required = false) String thursday,
            @RequestParam(value = "FRIDAY", required = false) String friday,
            @RequestParam(value = "SATURDAY", required = false) String saturday,
            @RequestParam(value = "SUNDAY", required = false) String sunday,
            @RequestParam(value = "createTeam", required = true) boolean createTeam,
            @RequestParam(value = "teamId", required = false) int teamId) {

        if (session.getAttribute("member") == null)
            return "redirect:/denied";
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
        availabilityDao.saveAvailability(availability);

        // update session
        Member member = (Member) session.getAttribute("member");
        model.addAttribute("message", "Availability updated!");
        availability = member.getAvailability();
        days = availability.getDays();
        model.addAttribute("days", days);
        model.addAttribute("slots", Slot.values());

        if (createTeam) {
            return "redirect:/team/" + teamId + "/view";
        } else {
            return "redirect:/profile";
        }
    }

    /**
     * Splits the comma separated list given by the client
     * 
     * @param newDay The day object
     * @param stringDay The day String
     */
    private void addPeriodsToDay(Day newDay, String stringDay) {
        String periodsNames[] = stringDay.split(",");
        for (String periodName : periodsNames)
            newDay.addPeriod(new Period(periodName).setAvailable(true));
    }
}
