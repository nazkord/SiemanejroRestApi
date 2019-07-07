package com.nazkord.siemajero.Controllers;

import com.nazkord.siemajero.Model.Bet;
import com.nazkord.siemajero.Model.Match;
import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.Services.BetService;
import com.nazkord.siemajero.Model.User;
import com.nazkord.siemajero.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bets")
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Map<Long, Bet> getAllBets(HttpSession httpSession) {
        //TODO: only admin
//        return betService.getAllBets();
        //TODO: real user
        return betService.getAllUsersBets(getLoggedInUser(httpSession).getId());
    }

    @RequestMapping(value = "/{betId}")
    public Bet getBet(@PathVariable Long betId, HttpSession httpSession) {
        return betService.getBet(betId, getLoggedInUser(httpSession).getId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBet(@RequestBody Match match, @RequestParam int homeTeam, @RequestParam int awayTeam, HttpSession httpSession) {
        betService.addBet(match, new Score(homeTeam, awayTeam), getLoggedInUser(httpSession));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userIdToReplaced}")
    public void updateUser(@RequestBody User userToUpdate, @PathVariable int userIdToReplaced) {

    }

    private User getLoggedInUser(HttpSession httpSession) {
        return userService.getUserById(Long.valueOf(1));
    }
}
