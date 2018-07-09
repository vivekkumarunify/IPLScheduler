package com.fabHotels.IplSchedulerProject.Services;

import com.fabHotels.IplSchedulerProject.Models.*;

import java.util.*;

public class MatchScheduler {

    private int totalNoOfMatches;

    private  Date date = Calendar.getInstance().getTime();

    public ArrayList<CricketMatch> cricketMatchesForIPL(IPL ipl){

        int totalNoOfTeams = ipl.getTotalNumberOfTeams();
        int totalVenues = ipl.getTotalNumberOfVenues();

        ArrayList<CricketMatch> cricketMatches = new ArrayList<>();

        int numberOfSingleMatch = this.getNumberOfMatches(totalNoOfTeams,totalVenues);

        totalNoOfMatches = numberOfSingleMatch*2;

        ArrayList<TeamX> teamsList = new ArrayList<TeamX>(ipl.getIplTeams());

        for (int i=0;i< numberOfSingleMatch ;i++){

            for(int j = 0; i < totalNoOfTeams-1; j++)
            {

                for(int k = j+1; j < totalNoOfTeams; k++)
                {
                    CricketMatch match_1 = new CricketMatch();
                    match_1.setOpponents(teamsList.get(j), teamsList.get(k));
                    cricketMatches.add(match_1);

                    CricketMatch match_2 = new CricketMatch();
                    match_2.setOpponents(teamsList.get(k),teamsList.get(j));
                    cricketMatches.add(match_2);
                }
            }
        }

        return cricketMatches;
    };

    public ArrayList<CricketMatch> createScheduleOnVanue(IPL ipl, ArrayList<CricketMatch> matches) {


        ArrayList<CricketMatch> cricketSchedule = new ArrayList<>();

        ArrayList<Venue> venueList = new ArrayList<Venue>(ipl.getIplVenues());
        int totalVenues = venueList.size();
        int totalMatches = totalNoOfMatches;
        int totalTeams = ipl.getTotalNumberOfTeams();

        int matchCount = 0;
        int venueIndex = 0;
        int venueCount = ipl.getTotalNumberOfVenues();

        while (totalMatches > 0){

            CricketMatch match = matches.get(matchCount);

            if (match.isPlayed() == false){

                if (match.getOpponent1().isPlaying() ==false && match.getOpponent2().isPlaying()==false){

                    if (match.getOpponent1().getMatchesPlayed() < (totalTeams -1)*2 && match.getOpponent1().getHomeGroundMatches() < totalTeams-1){
                        matches.get(matchCount).setVanue(match.getOpponent1().getHomeGround());
                        int matchCountForThisTeam = match.getOpponent1().getMatchesPlayed();
                        match.getOpponent1().setMatchesPlayed(++matchCountForThisTeam);
                    }
                    else if (match.getOpponent2().getMatchesPlayed() < (totalTeams -1)*2 && match.getOpponent2().getHomeGroundMatches() < totalTeams-1){
                        matches.get(matchCount).setVanue(match.getOpponent2().getHomeGround());
                        int matchCountForThisTeam = match.getOpponent1().getMatchesPlayed();
                        match.getOpponent1().setMatchesPlayed(++matchCountForThisTeam);
                    }

                    matchCount++;
                    totalMatches--;
                    match.setPlayed(true);
                    cricketSchedule.add(match);
                }
            }
        }
        return cricketSchedule;
    }

    public ArrayList<CricketMatch> createScheduleOnDay(IPL ipl, ArrayList<CricketMatch> matches){

        ArrayList<CricketMatch> cricketMatches = new ArrayList<>();

        int count = matches.size();
        while (count > 0){
            Random random = new Random();
            CricketMatch match = matches.get(random.nextInt(matches.size()));
            int x = match.getMatchDate().getMatchesPlayed();
            ArrayList<CricketMatch> matches1 = match.getMatchDate().getCricketMatches();
            matches1.add(match);
            if (match.getMatchDate().getCricketMatches().size() < 1){
                match.setMatchDate(new DayOfMatch(date,x < 2 ? x++ : x,matches1));
                cricketMatches.add(match);
                this.date = dateIncrementer(date,1).getTime();
                count--;
            }
        }

        return cricketMatches;
    };

    private int getNumberOfMatches(int totalNoOfTeams, int totalVenues) {

        int numberOfMatches = 0;

        if (totalNoOfTeams > 1 && totalVenues > 1){

            int factNoOfTeams = factorial(totalNoOfTeams);
            int factNoOfComb = factorial(totalNoOfTeams-2);

            numberOfMatches = factNoOfTeams / (2* factNoOfComb);
        }

        return numberOfMatches;
    }

    private int factorial(int totalNoOfTeams) {

        if (totalNoOfTeams <= 1)
            return 1;

        return factorial(totalNoOfTeams)*factorial(totalNoOfTeams-1);
    }


    private Calendar dateIncrementer(Date currentDate, int numberOfDaysToIncrement)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, numberOfDaysToIncrement);

        return cal;
    }
}
