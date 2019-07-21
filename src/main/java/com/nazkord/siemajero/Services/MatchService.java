package com.nazkord.siemajero.Services;

import com.nazkord.siemajero.Model.Bet;
import com.nazkord.siemajero.Model.Match;

import java.util.Map;

public interface MatchService {
    Map<Long, Match> getAllMatches();
    Match getMatchById(Long matchId);
}
