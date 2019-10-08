package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.Bet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetRepository extends CrudRepository<Bet, Long> {
    // there should by my own methods
    List<Bet> findByMatchId(Long matchId);
    List<Bet> findByUserId(Long userId);
}
