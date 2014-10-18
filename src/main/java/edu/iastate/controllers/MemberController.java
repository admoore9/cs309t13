package edu.iastate.controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;

@Controller
@RequestMapping("/members")
public class MemberController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
    	
        MemberDao memberDao = new MemberDao();
        List<Member> members =  memberDao.returnAllMembers();
        model.addAttribute("members", members);

        return "members";
    }
    
//    @RequestMapping(method=RequestMethod.POST)
//    public String handlePost(@RequestParam String action, @RequestParam String id, @RequestParam String name, Model model) {
//    	MemberDao memberDao = new MemberDao();
//    	memberDao.setName(Integer.parseInt(id), name);
//    	
//        List<Member> members =  memberDao.returnMembers();
//        model.addAttribute("members", members);
//        
//        return "members";
//    }
    
}

