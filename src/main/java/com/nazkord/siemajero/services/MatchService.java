package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.Match;

import java.util.Map;

public interface MatchService {
    Map<Long, Match> getAllMatches();
    Match getMatchById(Long matchId);
}
