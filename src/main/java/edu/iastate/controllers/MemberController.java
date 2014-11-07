package edu.iastate.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Member;

@Controller
@RequestMapping("/members")
public class MemberController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model, HttpSession session) {

        if (session.getAttribute("member") == null) {
            return "redirect:denied";
        }
    	
        MemberDao memberDao = new MemberDao();
        List<Member> members =  memberDao.getAllMembers();
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

