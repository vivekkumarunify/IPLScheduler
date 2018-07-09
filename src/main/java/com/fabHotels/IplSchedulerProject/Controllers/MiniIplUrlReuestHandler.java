package com.fabHotels.IplSchedulerProject.Controllers;
import com.fabHotels.IplSchedulerProject.Models.CricketMatch;
import com.fabHotels.IplSchedulerProject.Models.TeamX;
import com.fabHotels.IplSchedulerProject.Services.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/*")
public class MiniIplUrlReuestHandler {


    @Autowired
    private MatchServiceImpl matchService;

    @RequestMapping(method = GET,value = "/schedule",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CricketMatch> getAllTeamSchedule(){

        return matchService.findAllMatches();
    }


    @RequestMapping(method = POST,value = "/addTeam",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveTeam(@Valid @RequestBody TeamX team){

        matchService.saveTeam(team);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/removeTeam/{name}")
    public HttpStatus deleteNode(@PathVariable(value = "name") String name){
        int id =1;
        List<TeamX> teamList = matchService.findAllTeam();
        TeamX team = teamList.stream().filter(Team -> Team.getTeam_name().equals(name)).collect(Collectors.toList()).get(0);
        matchService.deleteTeam(team.getId());
        return HttpStatus.OK;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/findbyId/{id}")
    public TeamX findById(@PathVariable long id){
        TeamX team = matchService.findById(id);
        return team;
    }

}
