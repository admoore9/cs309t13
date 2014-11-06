package edu.iastate.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.AvailabilityDao;
import edu.iastate.models.Availability;
import edu.iastate.models.Day;

@Controller
@RequestMapping("/availability")
public class AvailabilityController {

    @RequestMapping(method = RequestMethod.GET)
    public String getAvailability(Model model) {
        AvailabilityDao availabilityDao = new AvailabilityDao();
        Availability availability = availabilityDao.getAvailabilityById(2);
        
        Set<Day> days = availability.getDays();
        model.addAttribute("days", days);
        return "availability";
    }
    
}