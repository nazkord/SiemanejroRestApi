package com.nazkord.siemajero.dbBasedService;

import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.repositories.MatchRepository;
import com.nazkord.siemajero.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DbBasedMatchService implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Map<Long, Match> getAllMatches() {
        Map<Long, Match> matches = new HashMap<>();
        matchRepository.findAll().forEach(match -> matches.put(match.getId(), match));
        return matches;
    }

    @Override
    public Match getMatchById(Long matchId) {

        //TODO: make this work with ifPresent

        Optional<Match> match = matchRepository.findById(matchId);
        if(match.isPresent()) {
            return match.get();
        } else {
            return null;
        }
    }
}
