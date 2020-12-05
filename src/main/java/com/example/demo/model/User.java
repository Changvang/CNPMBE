package com.example.demo.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name ="first_name")
	private String firstname;
	@Column(name = "last_name")
	private String lastname;
	@Column(name="dob")
	private Date dob;
	@Column(name ="address")
	private String address;
	@Column(name= "type")
	private int type; //0 is customer, 1 is owner
	
	public User() {
	
	}
	
	public User(String firstname,String lastname, Date dob, String address, int type) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob =dob;
		this.address= address;
		this.type =type;
	}

	public long getId() {
		return id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDOB() {
		return dob;
	}

	public void setDOB(Date dob) {
		this.dob=dob;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", firstname=" + firstname+", lastname=" + lastname + ", birthday=" + dob + ",address= " +address +",type=" +type+ "]";
	}
}

