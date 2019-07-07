package com.nazkord.siemajero.MapBasedServices;

import com.nazkord.siemajero.Model.Bet;
import com.nazkord.siemajero.Model.Match;
import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.Model.User;
import com.nazkord.siemajero.Services.BetService;
import com.nazkord.siemajero.Services.MatchService;
import com.nazkord.siemajero.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

public class MapBasedBetService implements BetService {

    @Autowired
    UserService userService;

    @Autowired
    MatchService matchService;

    private Map<Long, Bet> bets = new HashMap<Long, Bet>() {{
            put(Long.valueOf(1), new Bet(Long.valueOf(1),matchService.getMatchById(Long.valueOf(234)), userService.getUserById(Long.valueOf(2)),new Score(0,2),0));
            put(Long.valueOf(2), new Bet(Long.valueOf(2),matchService.getMatchById(Long.valueOf(456)), userService.getUserById(Long.valueOf(1)),new Score(1,1),3));
            put(Long.valueOf(3), new Bet(Long.valueOf(3),matchService.getMatchById(Long.valueOf(341)), userService.getUserById(Long.valueOf(4)), new Score(1,0),2));
            put(Long.valueOf(4), new Bet(Long.valueOf(4),matchService.getMatchById(Long.valueOf(567)), userService.getUserById(Long.valueOf(3)),new Score(2,2),0));
            put(Long.valueOf(5), new Bet(Long.valueOf(5),matchService.getMatchById(Long.valueOf(202)), userService.getUserById(Long.valueOf(4)),new Score(1,0),4));
            put(Long.valueOf(6), new Bet(Long.valueOf(6),matchService.getMatchById(Long.valueOf(908)), userService.getUserById(Long.valueOf(4)), new Score(3,1),1));
            put(Long.valueOf(7), new Bet(Long.valueOf(7),matchService.getMatchById(Long.valueOf(132)), userService.getUserById(Long.valueOf(2)), new Score(0,1),2));
    }};

    public Map<Long, Bet> getAllBets() {
        return bets;
    }

    public Map<Long, Bet> getAllUsersBets(Long userId) {
        //TODO
        return null;
    }

    public Bet getBet(Long betId, Long userId) {
        Bet bet = bets.get(betId);
        if(bet.getUser().getId().equals(userId))
            return bet;
        return null;
    }

    public void addBet(Match match, Score score, User user) {
        // TODO: work with ENUM class results and checking with actual matchScore
        Bet bet = new Bet(Long.valueOf(bets.size() + 1), match, user, score, 2);
        bets.put(bet.getId(), bet);
    }
}
