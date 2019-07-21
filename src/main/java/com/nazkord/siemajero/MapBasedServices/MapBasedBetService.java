package com.nazkord.siemajero.MapBasedServices;

import com.nazkord.siemajero.Model.Bet;
import com.nazkord.siemajero.Model.Match;
import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.Model.User;
import com.nazkord.siemajero.Services.BetService;
import com.nazkord.siemajero.Services.MatchService;
import com.nazkord.siemajero.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

public class MapBasedBetService implements BetService {

    @Autowired
    UserService userService;

    @Autowired
    MatchService matchService;

    private Map<Long, Bet> bets = new HashMap<>();

    @PostConstruct
    private void initialize() {
        bets.put(1L, new Bet(1L,matchService.getMatchById(234L), userService.getUserById(2L),new Score(0,2),0));
        bets.put(2L, new Bet(2L,matchService.getMatchById(456L), userService.getUserById(1L),new Score(1,1),3));
        bets.put(3L, new Bet(3L,matchService.getMatchById(341L), userService.getUserById(4L), new Score(1,0),2));
        bets.put(4L, new Bet(4L,matchService.getMatchById(567L), userService.getUserById(3L),new Score(2,2),0));
        bets.put(5L, new Bet(5L,matchService.getMatchById(202L), userService.getUserById(4L),new Score(1,0),4));
        bets.put(6L, new Bet(6L,matchService.getMatchById(908L), userService.getUserById(4L), new Score(3,1),1));
        bets.put(7L, new Bet(7L,matchService.getMatchById(132L), userService.getUserById(2L), new Score(0,1),2));
    }

    //TODO: only for admin
    /*public Map<Long, Bet> getAllBets() {
        return bets;
    }*/

    public Map<Long, Bet> getAllUsersBets(Long userId) {
        return bets.entrySet().stream()
                .filter(map -> map.getValue().getUser().getId().equals(userId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    public Bet getBet(Long betId, Long userId) {
        Bet bet = bets.get(betId);
        if(bet.getUser().getId().equals(userId))
            return bet;
        return null;
    }

    public void addBet(Match match, Score score, User user) {
        // TODO: work with ENUM class results and checking with actual matchScore
        Bet bet = new Bet((long) (bets.size() + 1), match, user, score, 2);
        bets.put(bet.getId(), bet);
    }
}
