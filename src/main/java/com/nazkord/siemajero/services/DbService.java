package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.repositories.MatchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbService {

    private final MatchRepository matchRepository;

    public DbService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void sync(List<Match> matches)  {
        matchRepository.saveAll(matches);
    }
}
