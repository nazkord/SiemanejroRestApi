package com.nazkord.siemajero.MapBasedServices;

import com.nazkord.siemajero.Model.Bet;
import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.Services.BetService;
import com.nazkord.siemajero.Services.MatchService;
import com.nazkord.siemajero.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
        bets.put(8L, new Bet(8L, matchService.getMatchById(908L), userService.getUserById(1L), new Score(1,4),0));
    }


    public Map<Long, Bet> getAllBets() {
        return bets;
    }

    public Map<Long, Bet> getAllUserBets(Long userId) {
        return bets.entrySet().stream()
                .filter(map -> map.getValue().getUser().getId().equals(userId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    public Bet getBetById(Long betId, Long userId) {
        Bet bet = bets.get(betId);
        if(bet.getUser().getId().equals(userId))
            return bet;
        return null;
    }

    public Map<Long, Bet> getBetsByMatchId(Long matchId) {
        return bets.entrySet().stream()
                .filter(map -> map.getValue().getMatch().getId().equals(matchId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Bet addBet(Bet bet) {
        bet.setId(createNewId());
        // TODO: work with ENUM class results and checking with actual matchScore
        bets.put(bet.getId(), bet);
        return bet;
    }

    private synchronized Long createNewId() {
        Long newId =  Collections.max(bets.keySet()) + 1;
        bets.put(newId, null);
        return newId;
    }

    public Boolean updateBet(Bet bet) {
        bets.replace(bet.getId(), bet);
        return true;
    }

    public void deleteBet(Long betId) {
        bets.remove(betId);
    }
}
