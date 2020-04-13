package com.nazkord.siemajero.controllers;

import com.nazkord.siemajero.model.Bet;
import com.nazkord.siemajero.model.BetList;
import com.nazkord.siemajero.model.User;
import com.nazkord.siemajero.security.Role;
import com.nazkord.siemajero.services.BetService;
import com.nazkord.siemajero.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/bets")
@Tag(name = "Bets", description = "the bet API")
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    //TODO: replace checking isUserInRole many times (???)
    //TODO: methods should return responseBody with list if everything went good and
    // with error in body when sth went wrong

    @Operation(summary = "Get all user bets", description = "Get all logged user bets by default. Possibility to filter by matchId", tags = { "Bets" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Bet.class)))) })

    @RequestMapping(method = RequestMethod.GET)
    public List<Bet> getAllBets(SecurityContextHolderAwareRequestWrapper securityWrapper,
                                @Parameter(description = "Filter bets by matchId. By default is null")
                                    @RequestParam(required = false) Long matchId) {

        if(securityWrapper.isUserInRole(Role.ADMIN.name())) { // get all bets
            return betService.getAllBets();
        } else if(securityWrapper.isUserInRole(Role.USER.name())) {
            if (matchId == null) { // get all user's bets
                User currentUser = userService.getUserByName(securityWrapper.getRemoteUser());
                return betService.getAllUserBets(currentUser.getId());
            } else { // get only bet for given match
                return betService.getBetsByMatchId(matchId);
            }
        }
        return Collections.emptyList();
    }

    @RequestMapping(value = "/{betId}", method = RequestMethod.GET)
    public Bet getBet(@PathVariable Long betId, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        Bet betToReturn = betService.getBetById(betId);
        Long userIdToCheck = betToReturn.getUser().getId();
        if(isOperationPermitted(userIdToCheck, securityWrapper)) {
            return betToReturn;
        } else {
            return null;
        }
    }

    //TODO: methods should return responseEntity success or error (according an article)

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBet(@RequestBody BetList betList, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        for(Bet bet : betList) {
            if (isOperationPermitted(bet.getUser().getId(), securityWrapper)) {
                betService.saveOrUpdateBet(bet);
            } else {
                return new ResponseEntity<>("what are you doing?", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update an existing bet", description = "", tags = { "Bets" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Bet not found") })
    @RequestMapping(method = RequestMethod.PUT, value = "/{betId}")
    public ResponseEntity<?> updateBet(@RequestBody Bet bet, @PathVariable Long betId) {
        if(bet.getId().equals(betId)) {
            try {
                betService.saveOrUpdateBet(bet);
            } catch (Exception e) {
                String errorMessage = e + " <== error";
                return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("not properly request", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Deletes a bet", description = "", tags = { "Bets" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})
    @RequestMapping(value = "/{betId}", method = RequestMethod.DELETE)
    public void deleteBet(
            @Parameter(description="Id of the bet to be deleted", required=true)
                @PathVariable Long betId) {
        betService.deleteBet(betId);
    }

    //TODO: do smth with this duplicate (maybe create some util class)
    private boolean isOperationPermitted(Long userIdToCheck, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(Role.ADMIN.name())) {
            return true;
        }
        // check whether the logged user want to get his own profile (getRemoteUser return name)
        User currentUser = userService.getUserByName(securityWrapper.getRemoteUser());
        return currentUser.getId().equals(userIdToCheck);
    }
}
