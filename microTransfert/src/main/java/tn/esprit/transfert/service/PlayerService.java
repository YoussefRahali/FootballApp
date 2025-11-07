package tn.esprit.transfert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.transfert.entity.Player;
import tn.esprit.transfert.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerValuationService valuationService;

    public Player createPlayer(Player player) {
        player.setEstimatedValue(valuationService.estimateValue(player));
        return playerRepository.save(player);
    }
}
