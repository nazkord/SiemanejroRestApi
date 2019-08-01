package com.nazkord.siemajero.Services;

import com.nazkord.siemajero.Model.Bet;

import java.util.Map;

public interface BetService {

    Map<Long, Bet> getAllBets();
    Map<Long, Bet> getAllUserBets(Long userId);
    Bet getBetById(Long betId, Long userId);
    Map<Long, Bet> getBetsByMatchId(Long matchId);
    Bet addBet(Bet bet);
    Boolean updateBet(Bet bet);
    void deleteBet(Long betId);
}
