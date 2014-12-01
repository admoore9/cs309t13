package edu.iastate.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iastate.dao.MemberDao;
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
        List<Message> reversedMessages = new ArrayList<>(messages);
        Collections.reverse(reversedMessages);
        model.addAttribute("messages", reversedMessages);
        return "mail";
    }

    @RequestMapping(value = "/setMessageAsViewed", method = RequestMethod.POST)
    public @ResponseBody void setMessageAsViewed(HttpSession session, @RequestParam(value = "messageId") int messageId) {
        if (session.getAttribute("member") == null)
            return;
        MessageDao messageDao = new MessageDao();
        Message message = messageDao.getMessageById(messageId);
        message.setViewed(true);
        messageDao.save(message);
        MemberDao memberDao = new MemberDao();
        Member member = (Member) session.getAttribute("member");
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
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
        session.setAttribute("member", new MemberDao().getMemberById(member.getId()));
    }

    @RequestMapping(value = "/doesRecipientExist", method = RequestMethod.GET)
    public @ResponseBody String doesRecipientExist(HttpSession session,
            @RequestParam(value = "recipient") String username) {
        if (session.getAttribute("member") == null)
            return "redirect:../denied";
        MemberDao memberDao = new MemberDao();
        Member member = memberDao.getMemberByUsername(username);
        String isValid;
        if (member == null) {
            isValid = "{ \"valid\": false }";
        } else {
            isValid = "{ \"valid\": true }";
        }
        return isValid;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public @ResponseBody void send(HttpSession session, @RequestParam(value = "recipient") String recipientUsername,
            @RequestParam(value = "subject") String subject, @RequestParam(value = "body") String body) {
        if (session.getAttribute("member") == null)
            return;
        MemberDao memberDao = new MemberDao();
        Member sender = (Member) session.getAttribute("member");
        Member recipient = memberDao.getMemberByUsername(recipientUsername);
        Message newMessage = new Message(subject, body, sender, recipient);
        newMessage.setSent(true);
        newMessage.setDraft(false);
        new MessageDao().save(newMessage);
        session.setAttribute("member", memberDao.getMemberById(sender.getId()));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody void delete(HttpSession session, @RequestParam(value = "messages") String messages) {
        if (session.getAttribute("member") == null)
            return;
        Member member = (Member) session.getAttribute("member");
        String messagesIds[] = messages.split(",");
        MessageDao messageDao = new MessageDao();
        for (String messageId : messagesIds) {
            Message message = member.getMail().getMessageById(Integer.parseInt(messageId));
            if (message != null) {
                message.setDeleted(true);
                messageDao.save(message);
            }
        }
        MemberDao memberDao = new MemberDao();
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
    }

    @RequestMapping(value = "/mark_unread", method = RequestMethod.POST)
    public @ResponseBody void markUnread(HttpSession session, @RequestParam(value = "messages") String messages) {
        if (session.getAttribute("member") == null)
            return;
        Member member = (Member) session.getAttribute("member");
        String messagesIds[] = messages.split(",");
        MessageDao messageDao = new MessageDao();
        for (String messageId : messagesIds) {
            Message message = member.getMail().getMessageById(Integer.parseInt(messageId));
            if (message != null) {
                message.setViewed(false);
                messageDao.save(message);
            }
        }
        MemberDao memberDao = new MemberDao();
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
    }

    @RequestMapping(value = "/save_draft", method = RequestMethod.POST)
    public @ResponseBody int saveDraft(HttpSession session,
            @RequestParam(value = "recipient") String recipientUsername,
            @RequestParam(value = "subject") String subject, @RequestParam(value = "body") String body,
            @RequestParam(value = "draft_id") Integer draftId) {
        if (session.getAttribute("member") == null)
            return 0;
        Member sender = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        Member recipient = memberDao.getMemberByUsername(recipientUsername);
        MessageDao messageDao = new MessageDao();
        if (draftId == null) {
            Message draft = new Message(subject, body, sender, recipient).setDraft(true);
            draftId = messageDao.save(draft).getMessageId();
        } else {
            Message draft = messageDao.getMessageById(draftId);
            draft.setBody(body);
            draft.setSubject(subject);
            draft.setRecipient(recipient);
            draft.setDraft(true);
            messageDao.save(draft);
        }
        session.setAttribute("member", memberDao.getMemberById(sender.getId()));
        return draftId;
    }
}
