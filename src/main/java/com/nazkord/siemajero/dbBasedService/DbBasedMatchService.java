package com.nazkord.siemajero.dbBasedService;

import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.repositories.MatchRepository;
import com.nazkord.siemajero.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DbBasedMatchService implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<>();
        matchRepository.findAll().forEach(matches::add);
        return matches;
    }

    @Override
    public Match getMatchById(Long matchId) {
        Optional<Match> match = matchRepository.findById(matchId);
        return match.orElse(null);
    }

    //TODO: by default get those matches from today
    @Override
    public List<Match> getMatchesByCompetition(Long competitionId) {
        return matchRepository.findByCompetitionId(competitionId);
    }
}
