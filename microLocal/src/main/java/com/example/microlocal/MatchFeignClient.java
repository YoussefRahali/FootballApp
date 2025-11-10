package com.example.microlocal;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microMatch")
public interface MatchFeignClient {
  @GetMapping("/matches/{id}")
  MatchDTO getMatchById(@PathVariable String id);
  @GetMapping("/matches")
  List<MatchDTO> getAllMatches() ;
}
