package com.example.microlocal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "microClub")
public interface ClubFeign {
    @GetMapping("/clubs/{id}")

    public Optional<ClubDto> getClubById(@PathVariable String id) ;
}
