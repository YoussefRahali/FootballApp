package com.example.microjoueur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoueurService {
    private final JoueurRepository repository;

    public JoueurService(JoueurRepository repository) {
        this.repository = repository;
    }

    public List<Joueur> getAll() {
        return repository.findAll();
    }

    public Joueur getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Joueur create(Joueur j) {
        // Valider que le club existe si un clubId est fourni
        if (j.getClubId() != null && !j.getClubId().isEmpty()) {
            Optional<Club> club = clubClient.getClubById(j.getClubId());
            if (club.isEmpty()) {
                throw new IllegalArgumentException("Club with ID " + j.getClubId() + " does not exist");
            }
        }
        return repository.save(j);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Joueur update(String id, Joueur payload) {
        // Valider que le club existe si un clubId est fourni
        if (payload.getClubId() != null && !payload.getClubId().isEmpty()) {
            Optional<Club> club = clubClient.getClubById(payload.getClubId());
            if (club.isEmpty()) {
                throw new IllegalArgumentException("Club with ID " + payload.getClubId() + " does not exist");
            }
        }

        return repository.findById(id).map(j -> {
            j.setNom(payload.getNom());
            j.setPrenom(payload.getPrenom());
            j.setAge(payload.getAge());
            j.setPosition(payload.getPosition());
            j.setNumero(payload.getNumero());
            j.setClubId(payload.getClubId());
            j.setNationalite(payload.getNationalite());
            j.setGoals(payload.getGoals());
            j.setAssists(payload.getAssists());
            j.setAppearances(payload.getAppearances());
            j.setBaseValue(payload.getBaseValue());
            j.setEstimatedValue(payload.getEstimatedValue());
            j.setContractEndDate(payload.getContractEndDate());
            return repository.save(j);
        }).orElse(null);
    }

    /**
     * Recherche de joueurs avec filtres multiples
     * Tous les paramètres sont optionnels
     * @param nom Recherche partielle sur le nom (insensible à la casse)
     * @param prenom Recherche partielle sur le prénom (insensible à la casse)
     * @param nationalite Recherche partielle sur la nationalité (insensible à la casse)
     * @param position Filtre exact sur la position (insensible à la casse)
     * @return Liste des joueurs correspondant aux critères
     */
    public List<Joueur> searchJoueurs(String nom, String prenom, String nationalite, String position) {
        // Si tous les paramètres sont vides, retourner tous les joueurs
        if (isEmpty(nom) && isEmpty(prenom) && isEmpty(nationalite) && isEmpty(position)) {
            return repository.findAll();
        }

        // Utiliser la recherche combinée
        List<Joueur> results = repository.findAll();

        // Filtrer par nom si fourni
        if (!isEmpty(nom)) {
            results = results.stream()
                    .filter(j -> j.getNom() != null && j.getNom().toLowerCase().contains(nom.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Filtrer par prénom si fourni
        if (!isEmpty(prenom)) {
            results = results.stream()
                    .filter(j -> j.getPrenom() != null && j.getPrenom().toLowerCase().contains(prenom.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Filtrer par nationalité si fourni
        if (!isEmpty(nationalite)) {
            results = results.stream()
                    .filter(j -> j.getNationalite() != null && j.getNationalite().toLowerCase().contains(nationalite.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Filtrer par position si fourni
        if (!isEmpty(position)) {
            results = results.stream()
                    .filter(j -> j.getPosition() != null && j.getPosition().equalsIgnoreCase(position))
                    .collect(Collectors.toList());
        }

        return results;
    }

    /**
     * Recherche par nom uniquement
     */
    public List<Joueur> searchByNom(String nom) {
        return repository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Recherche par prénom uniquement
     */
    public List<Joueur> searchByPrenom(String prenom) {
        return repository.findByPrenomContainingIgnoreCase(prenom);
    }

    /**
     * Recherche par nationalité uniquement
     */
    public List<Joueur> searchByNationalite(String nationalite) {
        return repository.findByNationaliteContainingIgnoreCase(nationalite);
    }

    /**
     * Filtre par position uniquement
     */
    public List<Joueur> filterByPosition(String position) {
        return repository.findByPositionIgnoreCase(position);
    }

    /**
     * Trier tous les joueurs par nombre de buts (décroissant par défaut)
     * @param ascending true pour ordre croissant, false pour décroissant
     * @return Liste des joueurs triés par nombre de buts
     */
    public List<Joueur> sortByGoals(boolean ascending) {
        Sort sort = ascending ? Sort.by("goals").ascending() : Sort.by("goals").descending();
        return repository.findAll(sort);
    }

    /**
     * Trier tous les joueurs par nombre d'assistances (décroissant par défaut)
     * @param ascending true pour ordre croissant, false pour décroissant
     * @return Liste des joueurs triés par nombre d'assistances
     */
    public List<Joueur> sortByAssists(boolean ascending) {
        Sort sort = ascending ? Sort.by("assists").ascending() : Sort.by("assists").descending();
        return repository.findAll(sort);
    }

    /**
     * Récupérer les meilleurs buteurs (top N)
     * @param limit Nombre de joueurs à retourner
     * @return Liste des meilleurs buteurs
     */
    public List<Joueur> getTopScorers(int limit) {
        List<Joueur> sorted = sortByGoals(false);
        return sorted.stream().limit(limit).collect(Collectors.toList());
    }

    /**
     * Récupérer les meilleurs passeurs (top N)
     * @param limit Nombre de joueurs à retourner
     * @return Liste des meilleurs passeurs
     */
    public List<Joueur> getTopAssisters(int limit) {
        List<Joueur> sorted = sortByAssists(false);
        return sorted.stream().limit(limit).collect(Collectors.toList());
    }

    /**
     * Vérifie si une chaîne est vide ou null
     */
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Autowired
    private ClubClient clubClient;

    public List<Club> getAllClubs(){
        return clubClient.getAllClubs();
    }

    public Optional<Club> getClubById(String id){
        return clubClient.getClubById(id);
    }
}
