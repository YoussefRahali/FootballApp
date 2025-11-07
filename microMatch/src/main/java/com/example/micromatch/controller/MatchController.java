package com.example.micromatch.controller;

import com.example.micromatch.dto.*;
import com.example.micromatch.entity.Match;
import com.example.micromatch.service.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/v1/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping
    public Match createMatch(@Valid @RequestBody CreateMatchRequest request) {
        return matchService.createMatch(request.getTeam1Id(), request.getTeam2Id(), request.getDate());
    }


}
