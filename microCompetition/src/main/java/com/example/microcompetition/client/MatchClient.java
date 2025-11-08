package com.example.microcompetition.client;

import com.example.microcompetition.dto.matchdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microMatch")
public interface MatchClient {

    @GetMapping
    List<matchdto> getAllMatches();

    @GetMapping("/{id}")
    matchdto getMatchById(@PathVariable("id") String id);

    @PostMapping
    matchdto createMatch(@RequestBody matchdto match);
}
