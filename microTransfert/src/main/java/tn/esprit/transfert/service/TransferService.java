package tn.esprit.transfert.service;

import org.springframework.stereotype.Service;
import tn.esprit.transfert.DTO.Player;
import tn.esprit.transfert.DTO.Team;
import tn.esprit.transfert.entity.Transfer;
import tn.esprit.transfert.repository.PlayerRepository;
import tn.esprit.transfert.repository.TeamRepository;
import tn.esprit.transfert.repository.TransferRepository;

import java.util.List;

@Service
public class TransferService {

    private final TeamRepository teamRepo;
    private final PlayerRepository playerRepo;
    private final TransferRepository transferRepo;

    // Constructeur explicite
    public TransferService(TeamRepository teamRepo, PlayerRepository playerRepo, TransferRepository transferRepo) {
        this.teamRepo = teamRepo;
        this.playerRepo = playerRepo;
        this.transferRepo = transferRepo;
    }

    public Transfer transferPlayer(String playerName, String fromTeamName, String toTeamName) {
        Player player = playerRepo.findByNameIgnoreCase(playerName).orElseThrow();
        Team fromTeam = teamRepo.findByNameIgnoreCase(fromTeamName).orElseThrow();
        Team toTeam = teamRepo.findByNameIgnoreCase(toTeamName).orElseThrow();

        fromTeam.getSquad().removeIf(p -> p.getId().equals(player.getId()));
        toTeam.getSquad().add(player);

        teamRepo.save(fromTeam);
        teamRepo.save(toTeam);

        Transfer transfer = new Transfer(player, fromTeam, toTeam);
        return transferRepo.save(transfer);
    }
    public List<Transfer> getAllTransfers() {
        return transferRepo.findAll();
    }
}
