package com.example.microbillet;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microMatch", url = "http://localhost:8200")
public interface MatchFeignClient {
  @GetMapping("/api/matches/{id}")
  MatchDTO getMatchById(@PathVariable String id);
}
