package com.example.demo.model;

import javax.persistence.*;
import java.sql.*;
@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "Userid")
    private long userid;

    @Column(name = "AccCode")
    private long acccode;

	@Column(name = "Rating")
	private Float rating;

	@Column(name = "Content")
	private String content;

	@Column(name = "Postdate")
	private Date postdate;


	public Comment() {

	}
	
	public Comment(long userid, long acccode, Float rating, String content, Date postdate) {
		this.userid = userid;
		this.acccode = acccode;
        this.rating = rating;
		this.content = content;
		this.postdate = postdate;
	}

    public long getAcccode() {
        return acccode;
    }
    public long getId() {
        return id;
    }
    public Date getPostdate() {
        return postdate;
    }
    public long getUserid() {
        return userid;
    }
    public String getContent() {
        return content;
    }
    public Float getRating() {
        return rating;
    }
    
    public void setUserid(long userid) {
		this.userid = userid;
	}

    public void setAcccode(long acccode) {
		this.acccode = acccode;
    }
    
    public void setPostdate(Date postdate) {
		this.postdate = postdate;
    }
    
    public void setContent(String content) {
		this.content = content;
    }
    
    public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Tutorial [Id=" + id + ", UserId=" + userid + ", AccommodationCode=" + acccode + ", Rating=" + rating + ", Content=" + content + ", PostDate=" + postdate + "]";
	}
}