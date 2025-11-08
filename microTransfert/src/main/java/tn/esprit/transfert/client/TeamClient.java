package tn.esprit.transfert.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transfert.DTO.Player;
import tn.esprit.transfert.DTO.Team;


import java.util.List;
import java.util.Optional;

@FeignClient(name = "club-s", url = "http://localhost:8200")
public interface TeamClient {
    @GetMapping
    public List<Team> getAllClubs();

    @GetMapping("/{id}")
    public Optional<Team> getClubById(@PathVariable String id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team createClub(@RequestBody Team club);

    @PutMapping("/{id}")
    public Team updateClub(@PathVariable String id, @RequestBody Team clubDetails);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClub(@PathVariable String id);
}
