package com.nazkord.siemajero.Match;

import com.nazkord.siemajero.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("matches")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @RequestMapping("matches/{matchId")
    public Match getMatch(@PathVariable int matchId) {
        return matchService.getMatchById(matchId);
    }
}
