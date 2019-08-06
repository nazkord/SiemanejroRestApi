package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.Bet;

import java.util.Map;

public interface BetService {

    Map<Long, Bet> getAllBets();
    Map<Long, Bet> getAllUserBets(Long userId);
    Bet getBetById(Long betId, Long userId);
    Bet getBet(Long betId);
    Map<Long, Bet> getBetsByMatchId(Long matchId);
    Bet addBet(Bet bet);
    Boolean updateBet(Bet bet);
    void deleteBet(Long betId);
}
