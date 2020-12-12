
package com.example.demo.model;

import java.sql.Blob;

import javax.persistence.*;

@Entity
@Table(name = "PathFiles")
public class PathFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "picByte", length = 2000)
    private byte[] picByte;

    @Column(name = "RoomID")
    private long roomID;

    public PathFile() {
    }

    public PathFile(byte[] data, long roomID) {
        this.picByte = data;
        this.roomID = roomID;
    }

    public long getId() {
        return id;
    }

    public long getRoomID() {
        return roomID;
    }

    public byte[] getPicByte() {
        return picByte;
    }

}
