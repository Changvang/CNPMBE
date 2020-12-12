
package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "Accommodations")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "Price")
    private double price;

    @Column(name = "Area")
    private double area;

    @Column(name = "Facility")
    private String facility;

    @Column(name = "Address")
    private String address;

    @Column(name = "UserID")
    private long userID;
    

    public Accommodation() {

    }

    public Accommodation(Double Price, Double Area, String Facility, String Address, Long UserID) {
        this.price = Price;
        this.area = Area;
        this.facility = Facility;
        this.address = Address;
        this.userID = UserID;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public double getArea() {
        return area;
    }

    public String getFacility() {
        return facility;
    }

    public long getUserID() {
        return userID;
    }

    public byte[] getPicByte() {
        return picByte;
    }
    
    @Override
    public String toString() {
        return "Accommodation[id=" + id + ", price=" + price + ", address=" + address + ", area=" + area + ", facility="
                + facility + ", userID=" + userID + "]";
    }

}