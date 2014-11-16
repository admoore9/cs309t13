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
            @RequestParam(value = "Monday6pm", required = false) String monday6pm,
            @RequestParam(value = "Monday7pm", required = false) String monday7pm,
            @RequestParam(value = "Monday8pm", required = false) String monday8pm,
            @RequestParam(value = "Monday9pm", required = false) String monday9pm,
            @RequestParam(value = "Monday10pm", required = false) String monday10pm,
            @RequestParam(value = "Monday11pm", required = false) String monday11pm,
            @RequestParam(value = "Monday12pm", required = false) String monday12pm,
            @RequestParam(value = "Tuesday6pm", required = false) String tuesday6pm,
            @RequestParam(value = "Tuesday7pm", required = false) String tuesday7pm,
            @RequestParam(value = "Tuesday8pm", required = false) String tuesday8pm,
            @RequestParam(value = "Tuesday9pm", required = false) String tuesday9pm,
            @RequestParam(value = "Tuesday10pm", required = false) String tuesday10pm,
            @RequestParam(value = "Tuesday11pm", required = false) String tuesday11pm,
            @RequestParam(value = "Tuesday12pm", required = false) String tuesday12pm,
            @RequestParam(value = "Wednesday6pm", required = false) String wednesday6pm,
            @RequestParam(value = "Wednesday7pm", required = false) String wednesday7pm,
            @RequestParam(value = "Wednesday8pm", required = false) String wednesday8pm,
            @RequestParam(value = "Wednesday9pm", required = false) String wednesday9pm,
            @RequestParam(value = "Wednesday10pm", required = false) String wednesday10pm,
            @RequestParam(value = "Wednesday11pm", required = false) String wednesday11pm,
            @RequestParam(value = "Wednesday12pm", required = false) String wednesday12pm,
            @RequestParam(value = "Thursday6pm", required = false) String thursday6pm,
            @RequestParam(value = "Thursday7pm", required = false) String thursday7pm,
            @RequestParam(value = "Thursday8pm", required = false) String thursday8pm,
            @RequestParam(value = "Thursday9pm", required = false) String thursday9pm,
            @RequestParam(value = "Thursday10pm", required = false) String thursday10pm,
            @RequestParam(value = "Thursday11pm", required = false) String thursday11pm,
            @RequestParam(value = "Thursday12pm", required = false) String thursday12pm,
            @RequestParam(value = "Friday6pm", required = false) String friday6pm,
            @RequestParam(value = "Friday7pm", required = false) String friday7pm,
            @RequestParam(value = "Friday8pm", required = false) String friday8pm,
            @RequestParam(value = "Friday9pm", required = false) String friday9pm,
            @RequestParam(value = "Friday10pm", required = false) String friday10pm,
            @RequestParam(value = "Friday11pm", required = false) String friday11pm,
            @RequestParam(value = "Friday12pm", required = false) String friday12pm,
            @RequestParam(value = "Saturday6pm", required = false) String saturday6pm,
            @RequestParam(value = "Saturday7pm", required = false) String saturday7pm,
            @RequestParam(value = "Saturday8pm", required = false) String saturday8pm,
            @RequestParam(value = "Saturday9pm", required = false) String saturday9pm,
            @RequestParam(value = "Saturday10pm", required = false) String saturday10pm,
            @RequestParam(value = "Saturday11pm", required = false) String saturday11pm,
            @RequestParam(value = "Saturday12pm", required = false) String saturday12pm,
            @RequestParam(value = "Sunday6pm", required = false) String sunday6pm,
            @RequestParam(value = "Sunday7pm", required = false) String sunday7pm,
            @RequestParam(value = "Sunday8pm", required = false) String sunday8pm,
            @RequestParam(value = "Sunday9pm", required = false) String sunday9pm,
            @RequestParam(value = "Sunday10pm", required = false) String sunday10pm,
            @RequestParam(value = "Sunday11pm", required = false) String sunday11pm,
            @RequestParam(value = "Sunday12pm", required = false) String sunday12pm) {

        Set<Day> days;
//        availabilityDao.
        
//        if (monday != null) {
//            Day mon = new Day();
//            mon.setAvailability(availability);
//            String[] slots = monday.split(",");
//            for (String slot : slots) {
//                Period
//            }
//        }
//        for (Day day : days) {
//        }
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