package edu.iastate.controllers;

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
import edu.iastate.models.Member;
import edu.iastate.utils.StringUtils;

@Controller
@RequestMapping("/availability")
public class AvailabilityController {

    AvailabilityDao availabilityDao = new AvailabilityDao();
    Availability availability;

    @RequestMapping(method = RequestMethod.GET)
    public String getAvailability(Model model) {
        availability = availabilityDao.getAvailabilityById(2);

        Set<Day> days = availability.getDays();
        model.addAttribute("days", days);
        return "availability";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody void availabilitySubmit(
            @RequestParam(value = "Monday") String monday,
            @RequestParam(value = "Tuesday") String tuesday,
            @RequestParam(value = "Wednesday") String wednesday,
            @RequestParam(value = "Thursday") String thursday,
            @RequestParam(value = "Friday") String friday,
            @RequestParam(value = "Saturday") String saturday,
            @RequestParam(value = "Sunday") String sunday) {

        Set<Day> days;
        availabilityDao.
        
        if (monday != null) {
            Day mon = new Day();
            mon.setAvailability(availability);
            String[] slots = monday.split(",");
            for (String slot : slots) {
                Period
            }
        }
        for (Day day : days) {
        }
        System.out.println(monday);
        // MemberDao memberDao = new MemberDao();
        //
        // String genPassword = StringUtils.secureString(password);
        //
        // Member member = memberDao.getMemberByUsernamePassword(username,
        // genPassword);
        //
        // if (member != null) {
        // session.setAttribute("member", member);
        // model.addAttribute("member", member);
        // }
    }

}