package com.nazkord.siemajero.Controllers;

import com.nazkord.siemajero.Model.Match;
import com.nazkord.siemajero.Services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping(method = RequestMethod.GET)
    public Map<Long,Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @RequestMapping("/{matchId}")
    public Match getMatch(@PathVariable Long matchId) {
        return matchService.getMatchById(matchId);
    }
}
