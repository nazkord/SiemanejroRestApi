package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.dto.footballData.Match;

import java.util.Map;

public interface MatchService {
    Map<Long, Match> getAllMatches();
    Match getMatchById(Long matchId);
    //Map<Long, Match> getMatchesByCompetition(Long competitionId);
    //public Mono<MatchResponse> getMatchesByDate(String dateFrom, String dateTo);
}
