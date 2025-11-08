package com.example.microjoueur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/joueurs")
@CrossOrigin
public class JoueurRestAPI {
    private final JoueurService service;

    @Autowired
    public JoueurRestAPI(JoueurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Joueur> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joueur> one(@PathVariable String id) {
        Joueur j = service.getById(id);
        return (j == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(j);
    }

    /**
     * Endpoint de recherche avec filtres multiples
     * Tous les paramètres sont optionnels
     * Exemples d'utilisation:
     * - /joueurs/search?nom=ron (trouve Ronaldo, Ronaldinho, etc.)
     * - /joueurs/search?prenom=cris (trouve Cristiano, etc.)
     * - /joueurs/search?nationalite=fra (trouve France, etc.)
     * - /joueurs/search?position=ATT (trouve tous les attaquants)
     * - /joueurs/search?nom=mes&position=ATT (trouve Messi qui est attaquant)
     *
     * @param nom Recherche partielle sur le nom (insensible à la casse)
     * @param prenom Recherche partielle sur le prénom (insensible à la casse)
     * @param nationalite Recherche partielle sur la nationalité (insensible à la casse)
     * @param position Filtre exact sur la position (GK, DEF, MID, ATT)
     * @return Liste des joueurs correspondant aux critères
     */
    @GetMapping("/search")
    public List<Joueur> search(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String nationalite,
            @RequestParam(required = false) String position) {
        return service.searchJoueurs(nom, prenom, nationalite, position);
    }

    /**
     * Recherche par nom uniquement
     * Exemple: /joueurs/search/nom?q=ron
     */
    @GetMapping("/search/nom")
    public List<Joueur> searchByNom(@RequestParam String q) {
        return service.searchByNom(q);
    }

    /**
     * Recherche par prénom uniquement
     * Exemple: /joueurs/search/prenom?q=cris
     */
    @GetMapping("/search/prenom")
    public List<Joueur> searchByPrenom(@RequestParam String q) {
        return service.searchByPrenom(q);
    }

    /**
     * Recherche par nationalité uniquement
     * Exemple: /joueurs/search/nationalite?q=fra
     */
    @GetMapping("/search/nationalite")
    public List<Joueur> searchByNationalite(@RequestParam String q) {
        return service.searchByNationalite(q);
    }

    /**
     * Filtre par position uniquement
     * Exemple: /joueurs/filter/position?q=ATT
     */
    @GetMapping("/filter/position")
    public List<Joueur> filterByPosition(@RequestParam String q) {
        return service.filterByPosition(q);
    }

    /**
     * Trier les joueurs par nombre de buts
     * Exemples:
     * - /joueurs/sort/goals (ordre décroissant par défaut)
     * - /joueurs/sort/goals?asc=true (ordre croissant)
     * - /joueurs/sort/goals?asc=false (ordre décroissant)
     *
     * @param ascending true pour ordre croissant, false pour décroissant (défaut: false)
     * @return Liste des joueurs triés par nombre de buts
     */
    @GetMapping("/sort/goals")
    public List<Joueur> sortByGoals(@RequestParam(required = false, defaultValue = "false") boolean asc) {
        return service.sortByGoals(asc);
    }

    /**
     * Trier les joueurs par nombre d'assistances
     * Exemples:
     * - /joueurs/sort/assists (ordre décroissant par défaut)
     * - /joueurs/sort/assists?asc=true (ordre croissant)
     * - /joueurs/sort/assists?asc=false (ordre décroissant)
     *
     * @param ascending true pour ordre croissant, false pour décroissant (défaut: false)
     * @return Liste des joueurs triés par nombre d'assistances
     */
    @GetMapping("/sort/assists")
    public List<Joueur> sortByAssists(@RequestParam(required = false, defaultValue = "false") boolean asc) {
        return service.sortByAssists(asc);
    }

    /**
     * Récupérer les meilleurs buteurs
     * Exemple: /joueurs/top/scorers?limit=10
     *
     * @param limit Nombre de joueurs à retourner (défaut: 10)
     * @return Liste des meilleurs buteurs
     */
    @GetMapping("/top/scorers")
    public List<Joueur> getTopScorers(@RequestParam(required = false, defaultValue = "10") int limit) {
        return service.getTopScorers(limit);
    }

    /**
     * Récupérer les meilleurs passeurs
     * Exemple: /joueurs/top/assisters?limit=10
     *
     * @param limit Nombre de joueurs à retourner (défaut: 10)
     * @return Liste des meilleurs passeurs
     */
    @GetMapping("/top/assisters")
    public List<Joueur> getTopAssisters(@RequestParam(required = false, defaultValue = "10") int limit) {
        return service.getTopAssisters(limit);
    }

    @PostMapping
    public Joueur create(@RequestBody Joueur j) {
        return service.create(j);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Joueur> update(@PathVariable String id, @RequestBody Joueur j) {
        Joueur updated = service.update(id, j);
        return (updated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clubs")
    public List<Club> getAllClubs() {
        return service.getAllClubs();
    }

    @GetMapping("/clubs/{id}")
    public Optional<Club> getClubById(@PathVariable String id) {
        return service.getClubById(id);
    }
}
