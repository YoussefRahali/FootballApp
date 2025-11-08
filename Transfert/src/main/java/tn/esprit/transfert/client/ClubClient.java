package tn.esprit.transfert.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "microClub")
public interface ClubClient {

    @GetMapping("/clubs")
    List<Club> getAllClubs() ;


    @GetMapping("/clubs/{id}")
    Optional<Club> getClubById(@PathVariable String id) ;

}
