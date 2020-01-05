package com.nazkord.siemajero.dbBasedService;

import com.nazkord.siemajero.model.Bet;
import com.nazkord.siemajero.repositories.BetRepository;
import com.nazkord.siemajero.services.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbBasedBetService implements BetService {

    @Autowired
    BetRepository betRepository;

    @Override
    public List<Bet> getAllBets() {
        List<Bet> bets = new ArrayList<>();
        betRepository.findAll().forEach(bets::add);
        return bets;
    }

    @Override
    public List<Bet> getAllUserBets(Long userId) {
        return betRepository.findByUserId(userId);
    }

    @Override
    public Bet getBetById(Long betId) {
        return betRepository.findById(betId).orElse(null);
    }

    @Override
    public List<Bet> getBetsByMatchId(Long matchId) {
        return betRepository.findByMatchId(matchId);
    }

    @Override
    public void saveOrUpdateBet(Bet bet) {
        if(bet.getId() == null) {
            betRepository.save(bet);
        } else {
            Optional<Bet> optionalBet = betRepository.findById(bet.getId());
            if (optionalBet.isPresent()) {
                Bet betToUpdate = optionalBet.get();
                betToUpdate.copyFrom(bet);
                betRepository.save(betToUpdate);
            }
        }
    }

    @Override
    public void deleteBet(Long betId) {
        betRepository.findById(betId).ifPresent(bet -> betRepository.delete(bet));
    }
}
