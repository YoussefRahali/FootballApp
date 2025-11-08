package com.example.micromatch.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "microLocal")
public interface LocalClient {

    @GetMapping("/locals")
    List<Local> findAll();

    @GetMapping("/locals/{id}")
    Optional<Local> findById(@PathVariable String id);
}


