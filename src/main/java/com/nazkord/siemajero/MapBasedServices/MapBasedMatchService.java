package com.nazkord.siemajero.MapBasedServices;

import com.nazkord.siemajero.Model.Match;
import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.Services.MatchService;

import java.util.*;

public class MapBasedMatchService implements MatchService {

    // TODO: make this work with real API (connect with Siemanejro project)

    private Map<Long, Match> matches = new HashMap<Long, Match>() {{
            put(Long.valueOf(234), new Match("ManUtd", "ManCity", new Score(1, 1), Long.valueOf(234)));
            put(Long.valueOf(456), new Match("Chelsea", "Arsenal", new Score(1, 1), Long.valueOf(456)));
            put(Long.valueOf(341), new Match("Dynamo", "Barcelona", new Score(2, 1), Long.valueOf(341)));
            put(Long.valueOf(567), new Match("Shakhtar", "Malme", new Score(0, 3), Long.valueOf(567)));
            put(Long.valueOf(202), new Match("Arsenal", "ManUtd", new Score(1, 0), Long.valueOf(202)));
            put(Long.valueOf(908), new Match("Bayern", "Borrusia", new Score(3, 1), Long.valueOf(908)));
            put(Long.valueOf(132), new Match("Juventus", "ManCity", new Score(2, 1), Long.valueOf(132)));
    }};

    public Map<Long, Match> getAllMatches() {
        return matches;
    }

    public Match getMatchById(Long matchId) {
        return matches.get(matchId);
    }
}
