package com.example.microlocal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locals")

public class LocalRestApi {
    private final LocalService service;
    @Autowired  // Constructor injection
    public LocalRestApi(LocalService service) {
        this.service = service;
    }


    @GetMapping
    public List<Local> getAll() {
        return service.getAll();
    }


    @GetMapping("/{id}")
    public Local getById(@PathVariable String id) {
        return service.getById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Local create(@RequestBody Local local) {
        return service.create(local);
    }


    @PutMapping("/{id}")
    public Local update(@PathVariable String id, @RequestBody Local local) {
        return service.update(id, local);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
