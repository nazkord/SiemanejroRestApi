package com.nazkord.siemajero.Bet;

import com.nazkord.siemajero.Model.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapBasedBetService implements BetService {
    private List<Bet> bets = new ArrayList<>(Arrays.asList(
            new Bet(1,234,2,new Score(0,2),0),
            new Bet(2,456,1,new Score(1,1),3),
            new Bet(3,341,4, new Score(1,0),2),
            new Bet(4,567,3,new Score(2,2),0),
            new Bet(5,202,4,new Score(1,0),4),
            new Bet(6,908, 4, new Score(3,1),1),
            new Bet(7,132,2, new Score(0,1),2)
    ));

    public List<Bet> getAllBets() {
        return bets;
    }

    public List<Bet> getAllUsersBets(int userId) {
        return bets.stream()
                .filter(bet -> bet.getUser_id() == userId)
                .collect(Collectors.toList());
    }

    public Bet getBet(int bet_id) {
        // done: change to lambdas
        return bets.stream()
                .filter(bet -> bet.getBet_id() == bet_id)
                .collect(Collectors.toList()).get(0);
    }

    public void deleteBet(int bet_id) {
        bets.remove(getBet(bet_id));
    }

    public void addBet(Bet bet) {
        // TODO:
        bet.setBet_id(10);
        bets.add(bet);
    }
}
