package com.nazkord.siemajero.dbBasedService;

import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.repositories.MatchRepository;
import com.nazkord.siemajero.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DbBasedMatchService implements MatchService {

    private final DateTimeFormatter  dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<>();
        matchRepository.findAll().forEach(matches::add);
        return getParsedMatchesWeekRange(matches);
    }

    @Override
    public Match getMatchById(Long matchId) {
        Optional<Match> match = matchRepository.findById(matchId);
        return match.orElse(null);
    }

    @Override
    public List<Match> getMatchesByCompetition(Long competitionId) {
        return getParsedMatchesWeekRange(matchRepository.findByCompetitionId(competitionId));
    }

    // return list of matches from a week before to a week later
    private List<Match> getParsedMatchesWeekRange(List<Match> matches) {
        return matches.stream()
                .filter(m -> isWithinRange(getDateFromString(m.getUtcDate())))
                .collect(Collectors.toList());
    }

    private LocalDate getDateFromString(String s) {
        return LocalDate.parse(s.substring(0,10), dateFormat);
    }

    private Boolean isWithinRange(LocalDate dateToCheck) {
        LocalDate startTime = LocalDate.now().minusDays(8);
        LocalDate endTime = LocalDate.now().plusDays(8);
        return !(dateToCheck.isAfter(endTime) || dateToCheck.isBefore(startTime));
    }
}
