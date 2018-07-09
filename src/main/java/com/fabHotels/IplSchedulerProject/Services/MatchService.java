package com.fabHotels.IplSchedulerProject.Services;

import com.fabHotels.IplSchedulerProject.Models.CricketMatch;
import com.fabHotels.IplSchedulerProject.Models.TeamX;

import java.util.Date;
import java.util.List;

public interface MatchService {

        void saveTeam(TeamX team);

        List<TeamX> findAllTeam();

        void deleteTeam(long id);

        TeamX findById(long id);

        List<CricketMatch> findAllMatches();

        List<CricketMatch> findByDate(Date date);

        List<CricketMatch> findByVanue(String venue);

}
