package com.nazkord.siemajero.Bet;

import com.nazkord.siemajero.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BetController {

    @Autowired
    private BetService betService;

    @RequestMapping("/bets")
    public List<Bet> getAllBets() {
        return betService.getAllBets();
    }

    // TODO: find out how to connect those mappings

    @RequestMapping("/bets/{betId}")
    public Bet getBet(@PathVariable int betId) {
        return betService.getBet(betId);
    }

    @RequestMapping("users/{userId}/bets/{betId}")
    public Bet getBetByUser(@PathVariable int betId) {
        return betService.getBet(betId);
    }

    /// those ABOVE

    @RequestMapping("users/{userId}/bets")
    public List<Bet> getAllUsersBets(@PathVariable int userId) {
        return betService.getAllUsersBets(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "users/{userId}/bets/{betId}")
    public void postBet(@RequestBody Bet bet, @PathVariable int userId, @PathVariable int betId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userIdToReplaced}")
    public void updateUser(@RequestBody User userToUpdate, @PathVariable int userIdToReplaced) {

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable int userId) {
    }
}
