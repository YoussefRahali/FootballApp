package com.example.microlocal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/locals")

public class LocalRestApi {
    private final ObjectMapper objectMapper = new ObjectMapper();
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




    // CREATE with image (multipart). Does not break your existing JSON create.
    @PostMapping(path = "/with-image", consumes = "multipart/form-data")
    public Local createWithImage(@RequestPart("local") String localJson,
                                 @RequestPart("file") MultipartFile file) throws IOException {
        Local local = objectMapper.readValue(localJson, Local.class);
        // ensure id is null for create
        local.setId(null);
        Local saved = service.create(local); // call your existing create/save method
        return service.uploadImage(saved.getId(), file);
    }

    // UPLOAD/REPLACE image for an existing Local
    @PostMapping(path = "/{id}/image", consumes = "multipart/form-data")
    public Local uploadImage(@PathVariable String id, @RequestPart("file") MultipartFile file) throws IOException {
        return service.uploadImage(id, file);
    }

    // STREAM image (used directly in <img src>)
    @GetMapping("/{id}/image")
    public ResponseEntity<InputStreamResource> viewImage(@PathVariable String id) throws IOException {
        return service.streamImage(id);
    }

    // DELETE image
    @DeleteMapping("/{id}/image")
    public void deleteImage(@PathVariable String id) {
        service.deleteImage(id);
    }

    @GetMapping ("/matches/{id}")
    public MatchDTO getMatchById(@PathVariable String id) {
        return service.getMatchById(id);
    }

    @GetMapping("/matches")
    public List<MatchDTO> getAllMatches(){return service.getAllMatches();}

    @GetMapping ("/clubs/{id}")
    public Optional<ClubDto> getClubById(@PathVariable String id) {
        return service.getClubById(id);
    }
}
