package com.nazkord.siemajero.Services;

import com.nazkord.siemajero.Model.Bet;
import com.nazkord.siemajero.Model.Match;
import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.Model.User;

import java.util.Map;

public interface BetService {

    //TODO: only for admin
    //Map<Long, Bet> getAllBets();
    Map<Long, Bet> getAllUsersBets(Long userId);
    Bet getBet(Long betId, Long userId);
    void addBet(Match match, Score score, User user);
}
