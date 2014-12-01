package edu.iastate.models;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "Mail")
public class Mail {

    @Id
    @GeneratedValue
    @Column(name = "mail_id")
    private int mailId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER)
    @OrderBy(clause = "message_id")
    private Set<Message> messages;

    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    @OrderBy(clause = "message_id")
    private Set<Message> sentmail;

    public Mail() {
        messages = new LinkedHashSet<Message>();
        sentmail = new LinkedHashSet<Message>();
    }

    /**
     * @return the messages
     */
    public Set<Message> getMessages() {
        Set<Message> inbox = new LinkedHashSet<Message>();
        for (Message message : messages) {
            if (!message.isDraft() && !message.isDeleted() && message.isSent())
                inbox.add(message);
        }
        return inbox;
    }

    /**
     * @return unviewed messages
     */
    public Set<Message> getUnviewedMessages() {
        Set<Message> unviewedNotification = new LinkedHashSet<Message>();
        for (Message message : messages) {
            if (!message.isViewed() && !message.isDraft() && !message.isDeleted() && message.isSent())
                unviewedNotification.add(message);
        }
        return unviewedNotification;
    }

    public Set<Message> getSentMail() {
        Set<Message> sentMail = new LinkedHashSet<Message>();
        for (Message message : messages) {
            if (message.isSent())
                sentMail.add(message);
        }
        return sentMail;
    }

    public Set<Message> getDrafts() {
        Set<Message> drafts = new LinkedHashSet<Message>();
        for (Message message : messages) {
            if (message.isDraft() && !message.isDeleted() && !message.isSent())
                drafts.add(message);
        }
        return drafts;
    }

    public Set<Message> getDeleted() {
        // TODO Auto-generated method stub
        return null;
    }

    public Mail setMember(Member member) {
        this.member = member;
        return this;
    }

    public Message getMessageById(int id) {
        for (Message message : messages) {
            if (message.getMessageId() == id)
                return message;
        }
        return null;
    }
}
