package com.nazkord.siemajero.controllers;

import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Match> getAllMatches(@RequestParam(required = false) List<Long> competitionIds) {
        if(competitionIds == null) {
            return matchService.getAllMatches();
        } else {
            return matchService.getMatchesByCompetitionIds(competitionIds);
        }
    }

    @RequestMapping("/{matchId}")
    public Match getMatch(@PathVariable Long matchId) {
        return matchService.getMatchById(matchId);
    }
}
