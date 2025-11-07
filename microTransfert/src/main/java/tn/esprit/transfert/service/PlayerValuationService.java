package tn.esprit.transfert.service;

import org.springframework.stereotype.Service;
import tn.esprit.transfert.DTO.Player;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PlayerValuationService {

    /**
     * Estime la valeur marchande d'un joueur en euros.
     */
    public double estimateValue(Player p) {
        if (p == null) return 1_000_000;

        double perfScore = computePerformanceScore(p); // 0-100%
        double ageFactor = ageFactor(p.getAge(), p.getPosition());
        double contractFactor = contractFactor(p.getContractEndDate());

        double baseValue = Math.max(p.getBaseValue(), 5_000_000); // minimum 5M€
        double performanceBoost = perfScore / 100.0 * 30_000_000; // jusqu'à +30M€

        double value = (baseValue + performanceBoost) * ageFactor * contractFactor;
        value = Math.max(500_000, value); // valeur minimale

        return Math.round(value);
    }

    private double computePerformanceScore(Player p) {
        double goals = Math.min((p.getGoals() / 40.0) * 50, 50);
        double assists = Math.min((p.getAssists() / 20.0) * 25, 25);
        double apps = Math.min((p.getAppearances() / 50.0) * 25, 25);

        double total = goals + assists + apps;
        if (p.getAge() > 30 && total > 80) total += 5; // bonus joueur expérimenté
        return Math.min(total, 100);
    }

    private double ageFactor(int age, String pos) {
        String position = pos != null ? pos.toUpperCase() : "MF";
        int peak = switch (position) {
            case "FW", "FORWARD" -> 27;
            case "MF", "MIDFIELDER" -> 28;
            case "DF", "DEFENDER" -> 30;
            case "GK", "GOALKEEPER" -> 33;
            default -> 28;
        };

        int diff = age - peak;
        if (diff < 0) {
            return 1.0 + 0.02 * (-diff); // +2% par an avant pic
        } else {
            return Math.max(0.6, 1.0 - 0.03 * diff); // -3% par an après pic
        }
    }

    private double contractFactor(LocalDate end) {
        if (end == null) return 1.0;
        long monthsLeft = ChronoUnit.MONTHS.between(LocalDate.now(), end);
        if (monthsLeft <= 0) return 0.8; // contrat expiré
        return 1.0 + Math.min(0.3, monthsLeft / 24.0 * 0.3); // max +30%
    }
}
