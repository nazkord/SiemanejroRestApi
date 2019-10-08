package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.dto.footballData.Match;

import java.util.List;

public interface MatchService {
    List<Match> getAllMatches();
    Match getMatchById(Long matchId);
    List<Match> getMatchesByCompetition(Long competitionId);
    //public Mono<MatchResponse> getMatchesByDate(String dateFrom, String dateTo);
}
