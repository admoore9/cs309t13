package edu.iastate.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.MessageDao;
import edu.iastate.models.Member;
import edu.iastate.models.Message;

@Controller
@RequestMapping("/mail")
public class MailController {

    @RequestMapping(method = RequestMethod.GET)
    public String getMail(Model model, HttpSession session,
            @RequestParam(value = "inbox", required = false) String inbox,
            @RequestParam(value = "sentmail", required = false) String sentmail,
            @RequestParam(value = "drafts", required = false) String drafts,
            @RequestParam(value = "deleted", required = false) String deleted) {

        if (session.getAttribute("member") == null)
            return "redirect:denied";
        
        Member member = (Member) session.getAttribute("member");
        Set<Message> messages;
        if (inbox != null)
            messages = member.getMail().getMessages();
        else if (sentmail != null)
            messages = member.getMail().getSentMail();
        else if (drafts != null)
            messages = member.getMail().getDrafts();
        else if (deleted != null)
            messages = member.getMail().getDeleted();
        else
            messages = member.getMail().getMessages();
        model.addAttribute("notifications", messages);
        return "notifications";
    }

    @RequestMapping(value = "/setMessagesAsViewed", method = RequestMethod.POST)
    public @ResponseBody void setNotificationsAsViewed(HttpSession session) {

        if (session.getAttribute("member") == null)
            return;
        
        // set up database access objects
        MessageDao messageDao = new MessageDao();
        
        Member member = (Member) session.getAttribute("member");
        Set<Message> unviewedMessages = member.getMail().getUnviewedMessages();
        for (Message unviewedMessage : unviewedMessages) {
            unviewedMessage.setViewed(true);
            messageDao.save(unviewedMessage);
        }
    }
}
