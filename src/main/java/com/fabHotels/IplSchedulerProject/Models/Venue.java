package com.fabHotels.IplSchedulerProject.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class Venue implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VENUE_ID")
    private long id;

    @Column
    private String vanueName;

    @Column
    private boolean occupied;

    public Venue() {
    }

    public Venue(@NotBlank String vanueName, boolean occupied) {
        this.vanueName = vanueName;
        this.occupied = occupied;
    }

    public Venue(@NotBlank String vanueName) {
        this.vanueName = vanueName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVanueName() {
        return vanueName;
    }

    public void setVanueName(String vanueName) {
        this.vanueName = vanueName;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
