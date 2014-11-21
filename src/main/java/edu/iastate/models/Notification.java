package edu.iastate.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "notification_id")
    private int notification_id;
    
	private String text;
	private String url;
	private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

	public Notification() {
	    time = new Timestamp(new Date().getTime());
	}

	public Notification(String text, String url) {
	    time = new Timestamp(new Date().getTime());
	    this.text = text;
	    this.url = url;
	}

    public Notification(String text) {
        time = new Timestamp(new Date().getTime());
        this.text = text;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public Notification setMember(Member member) {
        this.member = member;
        return this;
    }
}
