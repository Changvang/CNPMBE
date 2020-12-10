
package com.example.demo.model;

import javax.persistence.*;
@Entity
@Table(name = "PathFiles")
public class PathFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name="Path")
    private String path;

    @Column(name="RoomID")
    private long roomID;

    public PathFile(){}

    public PathFile(String path, long roomID)
    {
        this.path=path;
        this.roomID=roomID;
    }

    public long getId() {
        return id;
    }
    public String getPath() {
        return path;
    }
    public long getRoomID() {
        return roomID;
    }
    @Override
    public String toString()
    {
        return "PathFile[id="+id+",path="+path+",roomID="+roomID+"]";
    }
}

