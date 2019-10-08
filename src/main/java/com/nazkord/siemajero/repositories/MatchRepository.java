package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.dto.footballData.Match;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {
    // there should by my own methods
    List<Match> findByCompetitionId(Long competitionId);
}
