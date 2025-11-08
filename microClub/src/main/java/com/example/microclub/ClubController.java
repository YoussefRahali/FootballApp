package com.example.microclub;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }

    @GetMapping("/{id}")
    public Optional<Club> getClubById(@PathVariable String id) {
        return clubService.getClubById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Club createClub(@RequestBody Club club) {
        return clubService.createClub(club);
    }

    @PutMapping("/{id}")
    public Club updateClub(@PathVariable String id, @RequestBody Club clubDetails) {
        return clubService.updateClub(id, clubDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClub(@PathVariable String id) {
        clubService.deleteClub(id);
    }

    @GetMapping("/joueurs")
    public List<Joueur> getAllJoueurs(){
        return clubService.all();
    }

    @GetMapping("/joueurs/{id}")
    public Joueur getJoueurById(@PathVariable String id){
        return clubService.one(id);
    }
}
