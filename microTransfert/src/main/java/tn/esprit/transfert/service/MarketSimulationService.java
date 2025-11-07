package tn.esprit.transfert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.transfert.entity.*;
import tn.esprit.transfert.repository.OfferRepository;
import tn.esprit.transfert.repository.TeamRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MarketSimulationService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RecommenderService recommender;

    @Autowired
    private PlayerValuationService valuation;

    @Autowired
    private OfferRepository offerRepository;

    private ScheduledExecutorService executor;
    private boolean running = false;

    // Stocker les logs pour le front
    private final List<String> logs = new ArrayList<>();

    /**
     * Démarre la simulation du marché.
     */
    public synchronized void startSimulationStep() {
        if (running) return; // déjà en cours
        running = true;

        // Crée un nouvel executor pour éviter les conflits
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::runSimulationStep, 0, 3, TimeUnit.SECONDS);
    }

    /**
     * Arrête la simulation du marché.
     */
    public synchronized void stopSimulationStep() {
        running = false;
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
    }

    /**
     * Indique si la simulation est en cours.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Retourne les logs actuels de la simulation.
     */
    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    /**
     * Exécute un step de simulation.
     */
    private void runSimulationStep() {
        List<Team> teams = teamRepository.findAll();
        if (teams.isEmpty()) return;

        List<Player> alreadyTargeted = new ArrayList<>();

        for (Team t : teams) {
            List<Player> recommendations = recommender.recommendForTeam(t, 5)
                    .stream()
                    .filter(p -> !t.getSquad().contains(p))
                    .filter(p -> !alreadyTargeted.contains(p))
                    .collect(Collectors.toList());

            if (recommendations.isEmpty()) continue;

            // Choisir un joueur au hasard parmi les recommandations
            Player target = recommendations.get((int) (Math.random() * recommendations.size()));

            double estimatedPrice = valuation.estimateValue(target);
            double offerAmount = 0.8 * estimatedPrice;

            if (t.getBudget() >= offerAmount) {
                // Créer une offre
                Offer offer = new Offer();
                offer.setAmount(offerAmount);
                offer.setStatus(Status.PENDING);
                offer.setCreatedAt(LocalDateTime.now());
                offer.setBuyer(t);
                offer.setPlayer(target);

                offerRepository.save(offer);
                alreadyTargeted.add(target);

                // Ajouter log
                String log = "Team " + t.getName() + " fait une offre pour " + target.getName() + " : " + Math.round(offerAmount) + "€";
                System.out.println(log);
                logs.add(log);

                // Limiter la taille du log pour éviter la mémoire infinie
                if (logs.size() > 1000) {
                    logs.remove(0);
                }
            }
        }
    }
}
