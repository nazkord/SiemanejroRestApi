package com.nazkord.siemajero.Bet;

import com.nazkord.siemajero.Model.Score;
import com.nazkord.siemajero.User.User;
import com.nazkord.siemajero.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/bets")
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Bet> getAllBets() {
        //TODO: only admin
        return betService.getAllBets();
        //TODO: real user
//        return betService.getAllUsersBets(getLoggedInUser(httpSession).getId());
    }

    @RequestMapping(value = "/{betId}")
    public Bet getBet(@PathVariable int betId) {
        // TODO: check whether user has that betId
        return betService.getBet(betId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBet(@RequestParam int matchId, @RequestParam int homeTeam, @RequestParam int awayTeam, HttpSession httpSession) {
        Bet bet = new Bet();
        bet.setMatch_id(matchId);
        bet.setUser_id(getLoggedInUser(httpSession).getId());
        bet.setMatchScore(new Score(homeTeam,awayTeam));
        betService.addBet(bet);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userIdToReplaced}")
    public void updateUser(@RequestBody User userToUpdate, @PathVariable int userIdToReplaced) {

    }



    @RequestMapping(method = RequestMethod.DELETE, value = "/{betId}")
    public void deleteBet(@PathVariable int betId) {
        betService.deleteBet(betId);
    }

    private User getLoggedInUser(HttpSession httpSession) {
        return userService.getUserById(2);
//        return (User) httpSession.getAttribute("LOGGEDIN_USER");
    }
}
