package com.example.microbillet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microClub", url = "http://localhost:8200")
public interface ClubClient {
  @GetMapping("/clubs/{id}")
  ClubDTO getClubById(@PathVariable("id") String id);
}