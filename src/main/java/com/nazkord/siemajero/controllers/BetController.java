package com.nazkord.siemajero.controllers;

import com.nazkord.siemajero.model.Bet;
import com.nazkord.siemajero.model.User;
import com.nazkord.siemajero.security.Role;
import com.nazkord.siemajero.services.BetService;
import com.nazkord.siemajero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/bets")
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Map<Long, Bet> getAllBets(SecurityContextHolderAwareRequestWrapper securityWrapper,
                                     @RequestParam(required = false) Long matchId, HttpSession httpSession) {

        if(securityWrapper.isUserInRole("ADMIN")) { // get all bets
            return betService.getAllBets();
        } else if(securityWrapper.isUserInRole("USER")) {
            if (matchId == null) { // get all user's bets
                return betService.getAllUserBets(getLoggedInUser(httpSession).getId());
            } else { // get only bet for giver match
                return betService.getBetsByMatchId(matchId);
            }
        }
        return Collections.emptyMap();
    }

    @RequestMapping(value = "/{betId}", method = RequestMethod.GET)
    public Bet getBet(@PathVariable Long betId, HttpSession httpSession, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if(securityWrapper.isUserInRole(String.valueOf(Role.ADMIN))) {
            return betService.getBet(betId);
        } else {
            return betService.getBetById(betId, getLoggedInUser(httpSession).getId());
        }
    }

    // in UI add bet button in matchActivity
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBet(@RequestBody Bet bet, HttpSession httpSession) {
        if(bet.getUser().getId().equals(getLoggedInUser(httpSession).getId())) {
            return new ResponseEntity<>(betService.addBet(bet), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("what are you doing?", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{betId}")
    public ResponseEntity<?> updateBet(@RequestBody Bet bet, @PathVariable Long betId) {
        if(bet.getId().equals(betId)) {
            try {
                betService.updateBet(bet);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                String errorMessage = e + " <== error";
                return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{betId}", method = RequestMethod.DELETE)
    public void deleteBet(@PathVariable Long betId) {
        betService.deleteBet(betId);
    }

    // TODO: work with authenticated user
    private User getLoggedInUser(HttpSession httpSession) {
        return userService.getUserById(1L);
    }
}
