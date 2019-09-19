package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.dto.footballData.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Long> {
}
