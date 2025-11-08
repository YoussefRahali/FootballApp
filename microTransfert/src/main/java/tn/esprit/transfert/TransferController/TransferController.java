package tn.esprit.transfert.TransferController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transfert.DTO.Player;
import tn.esprit.transfert.DTO.Team;
import tn.esprit.transfert.entity.Transfer;
import tn.esprit.transfert.repository.PlayerRepository;
import tn.esprit.transfert.repository.TeamRepository;
import tn.esprit.transfert.service.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerValuationService valuationService;
    private final CareerPredictionService predictionService;
    private final RecommenderService recommenderService;
    private final MarketSimulationService marketSimulationService;
    private final PlayerService playerService;
    private final TransferService transferService;
    private final Logger log = LoggerFactory.getLogger(TransferController.class);

    @Autowired private PlayerRepository playerRepo;
    @Autowired private TeamRepository teamRepo;

    @Autowired
    public TransferController(PlayerRepository playerRepository,
                              TeamRepository teamRepository,
                              PlayerValuationService valuationService,
                              CareerPredictionService predictionService,
                              RecommenderService recommenderService,
                              MarketSimulationService marketSimulationService,
                              PlayerService playerService,
                              TransferService transferService) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.valuationService = valuationService;
        this.predictionService = predictionService;
        this.recommenderService = recommenderService;
        this.marketSimulationService = marketSimulationService;
        this.playerService = playerService;
        this.transferService = transferService;
    }

    // ---------------- PLAYERS ----------------

    @PostMapping("/players")
    @Operation(summary = "Create a new player",
            requestBody = @RequestBody(
                    description = "Player object to create",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Player.class),
                            examples = @ExampleObject(
                                    name = "Example Player",
                                    value = "{\n" +
                                            "  \"name\": \"Lionel Messi\",\n" +
                                            "  \"age\": 36,\n" +
                                            "  \"position\": \"Forward\",\n" +
                                            "  \"goals\": 25,\n" +
                                            "  \"assists\": 12,\n" +
                                            "  \"appearances\": 30,\n" +
                                            "  \"baseValue\": 120000000,\n" +
                                            "  \"estimatedValue\": 88542666.67,\n" +
                                            "  \"contractEndDate\": \"2026-06-30\"\n" +
                                            "}"
                            )
                    )
            ),
            responses = @ApiResponse(responseCode = "200", description = "Player created successfully")
    )
    public Player createPlayer(@Valid @org.springframework.web.bind.annotation.RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @GetMapping("/players")
    @Operation(summary = "Get all players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }


    // ---------------- Estimation a cost of the player----------------

    @GetMapping("/cost estimation-player")
    @Operation(summary = "Estimation a cost of the player")
    public double estimatePlayerValue(@RequestParam String playerName) {
        Player player = playerRepository.findByNameIgnoreCase (playerName)
                .orElseThrow(() -> new RuntimeException("Player not found with name " + playerName));
        return valuationService.estimateValue(player);
    }

    // ---------------- Predict player skill curve for future years----------------

    @GetMapping("/players/predict")
    @Operation(summary = "Predict player skill curve for future years")
    public List<Double> predictCareer(
            @RequestParam String playerName,
            @RequestParam int years) {

        Player player = playerRepository.findByNameIgnoreCase (playerName)
                .orElseThrow(() -> new RuntimeException("Player not found with name " + playerName));

        return predictionService.predictFutureSkillCurve(player, years);
    }


    // ---------------- Recommend players for a team----------------

    @GetMapping("/teams/{teamName}/recommend/{limit}")
    @Operation(summary = "Recommend players for a team")
    public List<Player> recommendPlayers(@RequestParam String TeamName, @PathVariable int limit) {
        Team team = teamRepository.findByNameIgnoreCase (TeamName)
                .orElseThrow(() -> new RuntimeException("Team not found with name " + TeamName));
        return recommenderService.recommendForTeam(team, limit);
    }



    // ---------------- TEAMS ----------------

    @GetMapping("/teams")
    @Operation(summary = "Get all teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }


    @PostMapping("/teams")
    @Operation(summary = "Add a new team")
    public Team addTeam(@Valid @org.springframework.web.bind.annotation.RequestBody Team team) {
        return teamRepository.save(team);
    }


    // ---------------- TRANSFERS ----------------

    @PostMapping("/transfet")
    @Operation(summary = "Transfert Part")
    public ResponseEntity<Transfer> executeTransfer(
            @RequestParam String playerName,
            @RequestParam String fromTeamName,
            @RequestParam String toTeamName) {

        Transfer transfer = transferService.transferPlayer(playerName, fromTeamName, toTeamName);
        return ResponseEntity.ok(transfer);
    }

    // ---------------- GET ALL TRANSFERS ----------------
    @GetMapping("/transfers")
    @Operation(summary = "Get all transfers")
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        List<Transfer> transfers = transferService.getAllTransfers();
        return ResponseEntity.ok(transfers);
    }





}
