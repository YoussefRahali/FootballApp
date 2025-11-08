package com.example.microcompetition.client;

import com.example.microcompetition.dto.ClubDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microClub")
public interface ClubClient {

    @GetMapping("/clubs")
    List<ClubDTO> getAllClubs();

    @GetMapping("/clubs/{id}")
    ClubDTO getClubById(@PathVariable("id") String id);
}
