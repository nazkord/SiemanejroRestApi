package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.dto.footballData.FootBallTeam;
import org.springframework.data.repository.CrudRepository;

public interface FootballTeamRepository extends CrudRepository<FootBallTeam, Long> {

}
