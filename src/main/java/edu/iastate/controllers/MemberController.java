package edu.iastate.controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iastate.dao.MemberDao;
import edu.iastate.models.Game;
import edu.iastate.models.Member;

@Controller
@RequestMapping("/members")
public class MemberController {

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
    
    	
        MemberDao memberDao = new MemberDao();
        List<Member> members =  memberDao.returnMembers();
        model.addAttribute("members", members);

        return "members";
    }
}
