package edu.iastate.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private int messageId;

    private String subject;
    private String body;
    private Date datetime;
    private boolean viewed;
    private boolean sent;
    private boolean deleted;
    private boolean draft;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Member recipient;

    public Message() {
        datetime = new Date();
    }

    public Message(String subject, String body, Member sender, Member recipient) {
        datetime = new Date();
        this.setSubject(subject);
        this.body = body;
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * @return the message_id
     */
    public int getMessageId() {
        return messageId;
    }

    public String getSubject() {
        return subject;
    }

    public Message setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public Message setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    public Message setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDraft() {
        return draft;
    }

    public Message setDraft(boolean draft) {
        this.draft = draft;
        return this;
    }

    /**
     * @return the time
     */
    public String getTime() {
        // today's date
        Calendar now = Calendar.getInstance();

        Calendar messageDatetime = Calendar.getInstance();
        messageDatetime.setTime(datetime);

        if (messageDatetime.get(Calendar.YEAR) < now.get(Calendar.YEAR)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return simpleDateFormat.format(datetime);
        } else if (messageDatetime.get(Calendar.DAY_OF_MONTH) < now.get(Calendar.DAY_OF_MONTH)
                || messageDatetime.get(Calendar.MONTH) < now.get(Calendar.MONTH)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd");
            return simpleDateFormat.format(datetime);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(datetime);
    }

    /**
     * @return the senderId
     */
    public Member getSender() {
        return sender;
    }

    /**
     * @return the member
     */
    public Member getRecipient() {
        return recipient;
    }

    /**
     * @param member
     *            the member to set
     */
    public Message setRecipient(Member recipient) {
        this.recipient = recipient;
        return this;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    /**
     * @return the sent
     */
    public boolean isSent() {
        return sent;
    }

    /**
     * @param sent
     *            the sent to set
     */
    public Message setSent(boolean sent) {
        this.sent = sent;
        return this;
    }
}
