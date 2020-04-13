package com.nazkord.siemajero.controllers;

import com.nazkord.siemajero.model.Bet;
import com.nazkord.siemajero.model.dto.footballData.Match;
import com.nazkord.siemajero.services.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@Tag(name = "Matches", description = "the match API")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Operation(summary = "Find matches in week range", description = "Get all matches from a week before to a week later. Possibility to filter by competitionIds", tags = { "matches" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))) })
    @RequestMapping(method = RequestMethod.GET)
    public List<Match> getAllMatches(
            @Parameter(description = "Filter bets by competitionIds. By default is null")
                @RequestParam(required = false) List<Long> competitionIds) {
        if(competitionIds == null) {
            return matchService.getAllMatches();
        } else {
            return matchService.getMatchesByCompetitionIds(competitionIds);
        }
    }

    @Operation(summary = "Find matches by ID", description = "Return a single match", tags = { "matches" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Match.class))) })
    @RequestMapping(method = RequestMethod.GET, value = "/{matchId}")
    public Match getMatch(
            @Parameter(description="Id of the match to be find",
                    required=true)
            @PathVariable Long matchId) {
        return matchService.getMatchById(matchId);
    }
}
