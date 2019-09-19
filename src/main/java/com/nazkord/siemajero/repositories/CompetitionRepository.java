package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.dto.footballData.Competition;
import com.nazkord.siemajero.model.dto.footballData.Match;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

}
