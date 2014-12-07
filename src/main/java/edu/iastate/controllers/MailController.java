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
import edu.iastate.dao.TournamentDao;
import edu.iastate.models.Member;
import edu.iastate.models.Message;
import edu.iastate.models.Team;
import edu.iastate.models.Tournament;

@Controller
@RequestMapping("/mail")
public class MailController {

    /**
     * Returns a jsp page for managing your messages.
     * 
     * @param model The model for the jsp page.
     * @param session The http session for the current user.
     * @param inbox Whether to view the inbox.
     * @param sentmail Whether to view your sent mail.
     * @param drafts Whether to view your drafts
     * @return The jsp page for managing your messages.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getMail(
            Model model,
            HttpSession session,
            @RequestParam(value = "inbox", required = false) String inbox,
            @RequestParam(value = "sentmail", required = false) String sentmail,
            @RequestParam(value = "drafts", required = false) String drafts) {

        Member member = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        member = memberDao.getMemberById(member.getId());
        if (member == null) {
            return "redirect:/denied";
        }

        Set<Message> messages;
        if (sentmail != null)
            messages = member.getMail().getSentMail();
        else if (drafts != null)
            messages = member.getMail().getDrafts();
        else
            messages = member.getMail().getMessages();
        List<Message> reversedMessages = new ArrayList<>(messages);
        Collections.reverse(reversedMessages);
        model.addAttribute("messages", reversedMessages);
        session.setAttribute("message", null);

        // For sidebar
        Set<Team> teams = member.getTeams();
        model.addAttribute("teams", teams);

        // For sidebar
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournaments = tournamentDao.getLastXTournaments(5);
        model.addAttribute("tournaments", tournaments);

        session.setAttribute("member", memberDao.getMemberById(member.getId()));
        return "mail";
    }

    /**
     * Sets a message as view by the recipient.
     * 
     * @param session The http session of the current user.
     * @param messageId The id of the message to set as viewed.
     */
    @RequestMapping(value = "/setMessageAsViewed", method = RequestMethod.POST)
    public @ResponseBody void setMessageAsViewed(
            HttpSession session,
            @RequestParam(value = "messageId") int messageId) {
        if (session.getAttribute("member") == null)
            return;
        MessageDao messageDao = new MessageDao();
        Message message = messageDao.getById(messageId);
        message.setViewed(true);
        messageDao.merge(message);
        MemberDao memberDao = new MemberDao();
        Member member = (Member) session.getAttribute("member");
        session.setAttribute("message", null);
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
    }

    /**
     * Sets all the unread messages for a user as read.
     * 
     * @param session The http session for the current user.
     */
    @RequestMapping(value = "/setMessagesAsViewed", method = RequestMethod.POST)
    public @ResponseBody void setNotificationsAsViewed(
            HttpSession session) {

        if (session.getAttribute("member") == null)
            return;

        // set up database access objects
        MessageDao messageDao = new MessageDao();

        Member member = (Member) session.getAttribute("member");
        Set<Message> unviewedMessages = member.getMail().getUnviewedMessages();
        for (Message unviewedMessage : unviewedMessages) {
            unviewedMessage.setViewed(true);
            messageDao.merge(unviewedMessage);
        }
        session.setAttribute("message", null);
        session.setAttribute("member", new MemberDao().getMemberById(member.getId()));
    }

    /**
     * Checks if the recipient of the message is a valid user.
     * 
     * @param session The http session for the current user.
     * @param username The intended recipients username.
     * @return Whether the recipient is a valid user.
     */
    @RequestMapping(value = "/doesRecipientExist", method = RequestMethod.GET)
    public @ResponseBody String doesRecipientExist(
            HttpSession session,
            @RequestParam(value = "recipient") String username) {
        if (session.getAttribute("member") == null)
            return "redirect:/denied";
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

    /**
     * Sends a message.
     * 
     * @param session The http session of the current user.
     * @param recipientUsername The username of the recipient
     * @param subject The subject line of the message
     * @param body The main body of the message.
     * @param draftId The draft id, if it exists.
     * @return The mail jsp page.
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(
            HttpSession session,
            @RequestParam(value = "recipient") String recipientUsername,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "body") String body,
            @RequestParam(value = "draft_id") Integer draftId) {
        if (session.getAttribute("member") == null)
            return "redirect:/denied";
        MemberDao memberDao = new MemberDao();
        MessageDao messageDao = new MessageDao();
        Member sender = (Member) session.getAttribute("member");
        Member recipient = memberDao.getMemberByUsername(recipientUsername);
        if (draftId != null) {
            Message draft = messageDao.getById(draftId);
            draft.setRecipient(recipient).setSubject(subject).setBody(body).setDraft(false).setSent(true);
            messageDao.merge(draft);
        } else {
            Message newMessage = new Message(subject, body, sender, recipient);
            newMessage.setSent(true);
            newMessage.setDraft(false);
            messageDao.merge(newMessage);
        }
        session.setAttribute("message", "Message sent!");
        session.setAttribute("member", memberDao.getMemberById(sender.getId()));
        return "mail";
    }

    /**
     * Deletes messages.
     * 
     * @param session The http session for the current user.
     * @param messages The message id, as a comma separated list.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody void delete(
            HttpSession session,
            @RequestParam(value = "messages") String messages) {
        if (session.getAttribute("member") == null)
            return;
        Member member = (Member) session.getAttribute("member");
        String messagesIds[] = messages.split(",");
        MessageDao messageDao = new MessageDao();
        for (String messageId : messagesIds) {
            Message message = member.getMail().getMessageById(Integer.parseInt(messageId));
            if (message != null) {
                message.setDeleted(true);
                messageDao.merge(message);
            }
        }
        MemberDao memberDao = new MemberDao();
        session.setAttribute("message", null);
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
    }

    /**
     * Marks messages as unread.
     * 
     * @param session The http session for the current user.
     * @param messages The messages to mark as unread, as a comma separated
     *            list.
     */
    @RequestMapping(value = "/mark_unread", method = RequestMethod.POST)
    public @ResponseBody void markUnread(
            HttpSession session,
            @RequestParam(value = "messages") String messages) {
        if (session.getAttribute("member") == null)
            return;
        Member member = (Member) session.getAttribute("member");
        String messagesIds[] = messages.split(",");
        MessageDao messageDao = new MessageDao();
        for (String messageId : messagesIds) {
            Message message = member.getMail().getMessageById(Integer.parseInt(messageId));
            if (message != null) {
                message.setViewed(false);
                messageDao.merge(message);
            }
        }
        MemberDao memberDao = new MemberDao();
        session.setAttribute("message", null);
        session.setAttribute("member", memberDao.getMemberById(member.getId()));
    }

    /**
     * Saves a draft.
     * 
     * @param session The http session for the current user.
     * @param recipientUsername The recipients username.
     * @param subject The subject line of the message.
     * @param body The main body of the message.
     * @param draftId The id of the draft.
     * @return The id of the draft.
     */
    @RequestMapping(value = "/save_draft", method = RequestMethod.POST)
    public @ResponseBody int saveDraft(
            HttpSession session,
            @RequestParam(value = "recipient") String recipientUsername,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "body") String body,
            @RequestParam(value = "draft_id") Integer draftId) {
        if (session.getAttribute("member") == null)
            return 0;
        Member sender = (Member) session.getAttribute("member");
        MemberDao memberDao = new MemberDao();
        Member recipient = memberDao.getMemberByUsername(recipientUsername);
        MessageDao messageDao = new MessageDao();
        if (draftId == null) {
            Message draft = new Message(subject, body, sender, recipient).setDraft(true);
            draftId = messageDao.merge(draft).getMessageId();
        } else {
            Message draft = messageDao.getById(draftId);
            draft.setBody(body);
            draft.setSubject(subject);
            draft.setRecipient(recipient);
            draft.setDraft(true);
            messageDao.merge(draft);
        }
        session.setAttribute("message", "Draft saved!");
        session.setAttribute("member", memberDao.getMemberById(sender.getId()));
        return draftId;
    }
}
