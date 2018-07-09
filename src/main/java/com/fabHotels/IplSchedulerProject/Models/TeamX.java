package com.fabHotels.IplSchedulerProject.Models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamX implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAM_ID")
    private long id;

    @Column(name = "team_name",nullable = false,updatable = false,columnDefinition = "default")
    private String team_name ="default";

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @JoinTable(name = "players",joinColumns = @JoinColumn(name="team_name"))
    private List<Players> playersLists = new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
            name="TEAM_TABLE_VENUE",
            joinColumns = @JoinColumn( name="TEAM_ID"),
            inverseJoinColumns = @JoinColumn( name="VENUE_ID")
    )
    private Venue HomeGround;

    @Column
    private int homeGroundMatches=0;

    @Column
    private boolean playing = false;

    @Column
    private int matchesPlayed =0;

    @Column
    private int totalPoints =0;

    public TeamX() {
        this.team_name = "default";
    }

    public TeamX(long id, String team_name, List<Players> playersLists, Venue homeGround, int homeGroundMatches, boolean playing, int matchesPlayed, int totalPoints) {
        this.id = id;
        this.team_name = team_name;
        this.playersLists = playersLists;
        HomeGround = homeGround;
        this.homeGroundMatches = homeGroundMatches;
        this.playing = playing;
        this.matchesPlayed = matchesPlayed;
        this.totalPoints = totalPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public List<Players> getPlayersLists() {
        return playersLists;
    }

    public void setPlayersLists(List<Players> playersLists) {
        this.playersLists = playersLists;
    }

    public Venue getHomeGround() {
        return HomeGround;
    }

    public void setHomeGround(Venue homeGround) {
        HomeGround = homeGround;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getHomeGroundMatches() {
        return homeGroundMatches;
    }

    public void setHomeGroundMatches(int homeGroundMatches) {
        this.homeGroundMatches = homeGroundMatches;
    }
}
