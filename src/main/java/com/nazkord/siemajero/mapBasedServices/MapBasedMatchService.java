package com.nazkord.siemajero.mapBasedServices;

import com.nazkord.siemajero.model.Match;
import com.nazkord.siemajero.model.Score;
import com.nazkord.siemajero.services.MatchService;

import java.util.*;

public class MapBasedMatchService implements MatchService {

    // TODO: make this work with real API (connect with Siemanejro project)

    private Map<Long, Match> matches = new HashMap<Long, Match>() {{
            put(234L, new Match("ManUtd", "ManCity", new Score(1, 1), 234L));
            put(456L, new Match("Chelsea", "Arsenal", new Score(1, 1), 456L));
            put(341L, new Match("Dynamo", "Barcelona", new Score(2, 1), 341L));
            put(567L, new Match("Shakhtar", "Malme", new Score(0, 3), 567L));
            put(202L, new Match("Arsenal", "ManUtd", new Score(1, 0), 202L));
            put(908L, new Match("Bayern", "Borrusia", new Score(3, 1), 908L));
            put(132L, new Match("Juventus", "ManCity", new Score(2, 1), 132L));
    }};

    public Map<Long, Match> getAllMatches() {
        return matches;
    }

    public Match getMatchById(Long matchId) {
        return matches.get(matchId);
    }


}
