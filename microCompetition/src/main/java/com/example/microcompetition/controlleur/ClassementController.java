package com.example.microcompetition.controlleur;

import com.example.microcompetition.Iservice.IClassementService;
import com.example.microcompetition.dto.ClubDTO;
import com.example.microcompetition.dto.matchdto;
import com.example.microcompetition.entity.Classement;
import com.example.microcompetition.entity.TypeCompetition;
import com.example.microcompetition.service.ClassementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/classements")
public class ClassementController {

    private final IClassementService service;

    public ClassementController(IClassementService service) {
        this.service = service;
    }

    @GetMapping
    public List<Classement> getAll() {
        return service.getAllClassements();
    }

    @GetMapping("/{id}")
    public Classement getById(@PathVariable String id) {
        return service.getClassementById(id);
    }

    @PostMapping
    public Classement create(@RequestBody Classement classement) {
        return service.saveClassement(classement);
    }

    @PutMapping("/{id}")
    public Classement update(@PathVariable String id, @RequestBody Classement classement) {
        return service.updateClassement(id, classement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteClassement(id);
    }

    @GetMapping("/clubs")
    public List<ClubDTO> getClubs() {
        return service.getAllClubs();
    }
    @GetMapping("/clubs/{id}")
    public ClubDTO getClubById(@PathVariable String id) {
        return service.getClubById(id);
    }



}