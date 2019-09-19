package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.dto.footballData.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    // there should by my own methods
}
