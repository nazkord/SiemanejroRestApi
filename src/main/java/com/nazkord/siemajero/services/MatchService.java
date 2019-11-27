package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.dto.footballData.Match;

import java.util.List;

public interface MatchService {
    List<Match> getAllMatches();
    Match getMatchById(Long matchId);
    List<Match> getMatchesByCompetition(Long competitionId);
    List<Match> getMatchesByCompetitionIds(List<Long> competitionIds);
    //public Mono<MatchResponse> getMatchesByDate(String dateFrom, String dateTo);
}
