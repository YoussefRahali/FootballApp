package tn.esprit.transfert.TransferController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transfert.service.MarketSimulationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // autorise le front Angular
@RequestMapping("/api/simulation")
public class MarketSimulationController {

    @Autowired
    private MarketSimulationService simulationService;

    // ---------------- MARKET ----------------

    /**
     * Démarrer la simulation
     */
    @PostMapping("/start")
    public ResponseEntity<String> startSimulation() {
        if (simulationService.isRunning()) {
            return ResponseEntity.badRequest().body("Simulation déjà en cours");
        }
        simulationService.startSimulationStep();
        return ResponseEntity.ok("Simulation démarrée");
    }

    /**
     * Arrêter la simulation
     */
    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        if (!simulationService.isRunning()) {
            return ResponseEntity.badRequest().body("Simulation déjà arrêtée");
        }
        simulationService.stopSimulationStep();
        return ResponseEntity.ok("Simulation arrêtée");
    }

    /**
     * Vérifier le statut
     */
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok(simulationService.isRunning() ? "Running" : "Stopped");
    }

    /**
     * Obtenir les logs de la simulation
     */
    @GetMapping("/logs")
    public ResponseEntity<List<String>> getLogs() {
        List<String> logs = simulationService.getLogs();
        return ResponseEntity.ok(logs);
    }
}
