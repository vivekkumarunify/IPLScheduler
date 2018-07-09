package com.fabHotels.IplSchedulerProject.Models;

import com.fabHotels.IplSchedulerProject.Services.MatchScheduler;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class IPL implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IPL_ID")
    private long id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "iplTeams",joinColumns=@JoinColumn(name = "IPL_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEAM_ID"))
    private Set<TeamX> iplTeams = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "iplVenue",joinColumns=@JoinColumn(name = "IPL_ID"),
            inverseJoinColumns = @JoinColumn(name = "VENUE_ID"))
    private Set<Venue> iplVenues = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "IplMathces",joinColumns=@JoinColumn(name = "IPL_ID"),
            inverseJoinColumns = @JoinColumn(name = "CRICKETMATCH_ID"))
    private List<CricketMatch> iplMatches = new ArrayList<>();

    @Column
    private int totalNumberOfTeams;

    @Column
    private int totalNumberOfVenues;

    @Column
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date tournamentBeginDate;

    @OneToOne
    private TeamX winner;

    public IPL() {

        this.iplMatches = getIplMatches();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIplMatches(ArrayList<CricketMatch> iplMatches) {
        this.iplMatches = iplMatches;
    }

    public void setWinner(TeamX winner) {
        this.winner = winner;
    }

    public HashSet<TeamX> getIplTeams() {
        return (HashSet<TeamX>) iplTeams;
    }

    public void setIplTeams(HashSet<TeamX> iplTeams) {
        this.iplTeams = iplTeams;
    }

    public HashSet<Venue> getIplVenues() {
        return (HashSet<Venue>) iplVenues;
    }

    private void setIplVenues(HashSet<Venue> iplVenues)
    {
        this.iplVenues = iplVenues;
    }

    public void setIplVenues(ArrayList<String> veneues) {
        HashSet<Venue> iplVenues = new HashSet<>();
        for (String venue:veneues){
            iplVenues.add(new Venue(venue));
        }
        this.iplVenues = iplVenues;
    }

    public ArrayList<CricketMatch> getIplMatches() {
        //Get matches ..Apply Logic
        MatchScheduler matchScheduler = new MatchScheduler();
        ArrayList<CricketMatch> cricketMatchArrayList =  matchScheduler.cricketMatchesForIPL(this);
        ArrayList<CricketMatch> cricketMatchesOnVanues = matchScheduler.createScheduleOnVanue(this,cricketMatchArrayList);
        this.iplMatches = matchScheduler.createScheduleOnDay(this,cricketMatchesOnVanues);
        return (ArrayList<CricketMatch>) iplMatches;
    }

    public int getTotalNumberOfTeams() {
        if(iplTeams!=null && this.totalNumberOfTeams != iplTeams.size())
            this.totalNumberOfTeams = iplTeams.size();
        return this.totalNumberOfTeams;
    }

    public void setTotalNumberOfTeams(int totalNumberOfTeams) throws Exception {
        if (totalNumberOfTeams <= 0)
            throw new Exception("Team is less than or equal to zero");
        this.totalNumberOfTeams = totalNumberOfTeams;
    }

    public int getTotalNumberOfVenues() {
        if(iplVenues!=null && this.totalNumberOfVenues != iplVenues.size())
            this.totalNumberOfVenues = iplVenues.size();
        return this.totalNumberOfVenues;
    }

    public void setTotalNumberOfVenues(int totalNumberOfVenues) throws Exception {
        if(totalNumberOfVenues>=0)
        {
            this.totalNumberOfVenues = totalNumberOfVenues;
        }
       else {
            throw new Exception("Vanue should be present. Shouldn't be zero or lesser");
        }
    }

    public Date getTournamentBeginDate() {
        return tournamentBeginDate;
    }

    public void setTournamentBeginDate(Date tournamentBeginDate) {
        this.tournamentBeginDate = tournamentBeginDate;
    }

    public TeamX getWinner() {

        winner = this.findWinner();

        return winner;
    }

    private TeamX findWinner() {

        ArrayList<TeamX> teamArrayList = new ArrayList<>(totalNumberOfTeams);

        Collections.sort(teamArrayList, new Comparator<TeamX>() {
            @Override
            public int compare(TeamX team1, TeamX team2) {
                return team1.getTotalPoints() - team2.getTotalPoints();
            }
        });

        return teamArrayList.get(0);
    }

}
