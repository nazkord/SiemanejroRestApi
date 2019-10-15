package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.dto.footballData.*;
import com.nazkord.siemajero.repositories.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbService {

    private final MatchRepository matchRepository;
    private final CompetitionRepository competitionRepository;
    private final FootballTeamRepository footballTeamRepository;
    private final ScoreRepository scoreRepository;
    private final FullTimeResultRepository fullTimeResultRepository;

    public DbService(MatchRepository matchRepository, CompetitionRepository competitionRepository, FootballTeamRepository footballTeamRepository, ScoreRepository scoreRepository, FullTimeResultRepository fullTimeResultRepository) {
        this.matchRepository = matchRepository;
        this.competitionRepository = competitionRepository;
        this.footballTeamRepository = footballTeamRepository;
        this.scoreRepository = scoreRepository;
        this.fullTimeResultRepository = fullTimeResultRepository;
    }

    public void sync(List<Match> matches)  {

        List<FootBallTeam> awayTeams = matches.stream()
                .map(Match::getAwayTeam)
                .collect(Collectors.toList());

        List<FootBallTeam> homeTeams = matches.stream()
                .map(Match::getHomeTeam)
                .collect(Collectors.toList());

        List<Competition> competitions = matches.stream()
                .map(Match::getCompetition)
                .collect(Collectors.toList());

        List<Score> scores = matches.stream()
                .map(Match::getScore)
                .collect(Collectors.toList());

        List<FullTimeResult> fullTimeResults = matches.stream()
                .map(Match::getScore)
                .map(Score::getFullTime)
                .collect(Collectors.toList());


        fullTimeResultRepository.saveAll(fullTimeResults);
        scoreRepository.saveAll(scores);
        competitionRepository.saveAll(competitions);
        footballTeamRepository.saveAll(homeTeams);
        footballTeamRepository.saveAll(awayTeams);
        matchRepository.saveAll(matches);
    }
}
