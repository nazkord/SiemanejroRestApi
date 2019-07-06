package com.nazkord.siemajero.Match;

import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.User.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MatchService {

    // TODO: make this work with real API (connect with Siemanejro project)

    private List<Match> matches = new ArrayList<>(Arrays.asList(
            new Match("ManUtd", "ManCity", new Score(1,1), 234),
            new Match("Chelsea", "Arsenal", new Score(1,1), 456),
            new Match("Dynamo", "Barcelona", new Score(2,1),341),
            new Match("Shakhtar", "Malme", new Score(0,3),567),
            new Match("Arsenal","ManUtd", new Score(1,0), 202),
            new Match("Bayern", "Borrusia", new Score(3,1),908),
            new Match("Juventus", "ManCity", new Score(2,1), 132)
    ));

    public List<Match> getAllMatches() {
        return matches;
    }

    public Match getMatchById(int match_id) {
        // TODO: change to lambdas
        for (Match match:
                matches) {
            if(match.getMatch_id() == match_id)
                return match;
        }
        return null;
    }
}
