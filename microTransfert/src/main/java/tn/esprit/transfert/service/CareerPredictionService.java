package tn.esprit.transfert.service;

import org.springframework.stereotype.Service;
import tn.esprit.transfert.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareerPredictionService {

    /**
     * Prédit la progression de carrière d'un joueur sur X années.
     * Résultat : pourcentage de niveau (0-100%)
     */
    public List<Double> predictFutureSkillCurve(Player p, int years) {
        List<Double> curve = new ArrayList<>();
        if (p == null) return curve;

        double baseSkill = computePerformanceScore(p); // départ 0-100%
        String pos = normalizePosition(p.getPosition());
        int currentAge = p.getAge();

        for (int y = 0; y < years; y++) {
            int futureAge = currentAge + y;
            double predicted = skillAtAge(baseSkill, futureAge, pos);
            predicted = Math.max(0, Math.min(100, predicted)); // clamp
            curve.add(Math.round(predicted * 10.0) / 10.0);
        }
        return curve;
    }

    private double skillAtAge(double baseSkill, int age, String position) {
        int peakAge = switch (position) {
            case "FW" -> 27;
            case "MF" -> 28;
            case "DF" -> 30;
            case "GK" -> 33;
            default -> 28;
        };

        if (age < peakAge) {
            // Montée progressive vers le pic
            double growth = baseSkill + (100 - baseSkill) * (age - 18) / (peakAge - 18);
            return growth;
        } else if (age <= peakAge + 3) {
            // Pic stable 3 ans
            return Math.min(baseSkill + (100 - baseSkill) * 0.9, 100);
        } else {
            // Déclin doux après pic
            double declineYears = age - (peakAge + 3);
            double factor = Math.max(0.7, 1 - 0.03 * declineYears); // -3%/an
            return Math.min(baseSkill + (100 - baseSkill) * 0.9, 100) * factor;
        }
    }

    private double computePerformanceScore(Player p) {
        // Performance réaliste pour un joueur star
        double goals = Math.min((p.getGoals() / 40.0) * 50, 50);
        double assists = Math.min((p.getAssists() / 20.0) * 25, 25);
        double apps = Math.min((p.getAppearances() / 50.0) * 25, 25);

        double total = goals + assists + apps;
        // Boost pour joueurs expérimentés
        if (p.getAge() > 30 && total > 80) total += 5;

        return Math.min(total, 100);
    }

    private String normalizePosition(String pos) {
        if (pos == null) return "MF";
        return switch (pos.toUpperCase()) {
            case "FORWARD", "STRIKER" -> "FW";
            case "MIDFIELDER" -> "MF";
            case "DEFENDER" -> "DF";
            case "GOALKEEPER", "GK" -> "GK";
            default -> "MF";
        };
    }
}
