package com.example.microbillet;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microMatch", url = "http://localhost:8600")
public interface MatchFeignClient {
  @GetMapping("/matches/{id}")
  MatchDTO getMatchById(@PathVariable String id);
}
