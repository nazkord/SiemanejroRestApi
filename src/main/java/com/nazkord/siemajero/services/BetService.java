package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.Bet;

import java.util.List;
import java.util.Map;

public interface BetService {

    List<Bet> getAllBets();
    List<Bet> getAllUserBets(Long userId);
    Bet getBetById(Long betId);
    List<Bet> getBetsByMatchId(Long matchId);
    void addBet(Bet bet);
    Boolean updateBet(Bet bet);
    void deleteBet(Long betId);
}
