package com.fabHotels.IplSchedulerProject.Services;

import com.fabHotels.IplSchedulerProject.Models.CricketMatch;
import com.fabHotels.IplSchedulerProject.Models.IPL;
import com.fabHotels.IplSchedulerProject.Models.TeamX;
import com.fabHotels.IplSchedulerProject.Repository.CricketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MatchServiceImpl implements MatchService{


    @Autowired
    private CricketRepository cricketRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void saveTeam(TeamX team) {

        cricketRepository.save(team);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public List<TeamX> findAllTeam() {
        List<TeamX> teamList = cricketRepository.findAll();
        return teamList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void deleteTeam(long id) {

        Optional<TeamX> team = cricketRepository.findById(id);
        cricketRepository.delete(team.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public TeamX findById(long id) {
        Optional<TeamX> team = cricketRepository.findById(id);
        return team.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public List<CricketMatch> findAllMatches() {

        IPL ipl = new IPL();
        return  ipl.getIplMatches();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public List<CricketMatch> findByDate(Date date) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public List<CricketMatch> findByVanue(String venue) {
        return null;
    }
}
