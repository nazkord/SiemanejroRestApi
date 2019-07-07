package com.nazkord.siemajero.Bet;

import java.util.List;

public interface BetService {

    List<Bet> getAllBets();
    List<Bet> getAllUsersBets(int userId);
    Bet getBet(int bet_id);
    void deleteBet(int bet_id);
    void addBet(Bet bet);
}
