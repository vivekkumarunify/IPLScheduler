package com.fabHotels.IplSchedulerProject.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "DayOfMatch")
@Access(value=AccessType.FIELD)
public class DayOfMatch implements Serializable{

    @Id
    @Column(name = "MATCH_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @DateTimeFormat
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column
    private int matchesPlayed;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "cricketMatches",joinColumns=@JoinColumn(name = "MATCH_ID"),
            inverseJoinColumns = @JoinColumn(name = "CRICKETMATCH_ID"))
    private List<CricketMatch> cricketMatches;

    public DayOfMatch(Date date, int matchesPlayed, ArrayList<CricketMatch> cricketMatches) {
        this.date = date;
        this.matchesPlayed = matchesPlayed;
        this.cricketMatches = cricketMatches;
    }

    public DayOfMatch() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCricketMatches(List<CricketMatch> cricketMatches) {
        this.cricketMatches = cricketMatches;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public ArrayList<CricketMatch> getCricketMatches() {
        return (ArrayList<CricketMatch>) cricketMatches;
    }

    public void setCricketMatches(ArrayList<CricketMatch> cricketMatches) {
        this.cricketMatches = cricketMatches;
    }
}
