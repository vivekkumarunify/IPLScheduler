package com.fabHotels.IplSchedulerProject.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CricketMatch implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="CRICKETMATCH_ID")
    private long id;

    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private Venue vanue;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private DayOfMatch MatchDate;

    @Column
    private TeamX opponent1;

    @Column
    private TeamX opponent2;

    @Column
    private boolean isPlayed;

    public CricketMatch() {
    }

    public CricketMatch(long id, Venue vanue, DayOfMatch matchDate, TeamX opponent1, TeamX opponent2, boolean isPlayed) {
        this.id = id;
        this.vanue = vanue;
        MatchDate = matchDate;
        this.opponent1 = opponent1;
        this.opponent2 = opponent2;
        this.isPlayed = isPlayed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Venue getVanue() {
        return vanue;
    }

    public void setVanue(Venue vanue) {
        this.vanue = vanue;
    }

    public DayOfMatch getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(DayOfMatch matchDate) {
        MatchDate = matchDate;
    }

    public TeamX getOpponent1() {
        return opponent1;
    }

    public void setOpponent1(TeamX opponent1) {
        this.opponent1 = opponent1;
    }

    public TeamX getOpponent2() {
        return opponent2;
    }

    public void setOpponents(TeamX opponent1,TeamX opponent2){
        this.opponent1=opponent1;
        this.opponent2=opponent2;
    }

    public void setOpponent2(TeamX opponent2) {
        this.opponent2 = opponent2;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }
}
