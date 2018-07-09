package com.fabHotels.IplSchedulerProject.Repository;

import com.fabHotels.IplSchedulerProject.Models.TeamX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CricketRepository extends JpaRepository<TeamX,Long> {

}
