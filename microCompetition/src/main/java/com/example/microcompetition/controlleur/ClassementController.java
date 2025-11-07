package com.example.microcompetition.controlleur;

import com.example.microcompetition.entity.Classement;
import com.example.microcompetition.entity.TypeCompetition;
import com.example.microcompetition.service.ClassementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classements")
@CrossOrigin
public class ClassementController {

    private final ClassementService service;

    public ClassementController(ClassementService service) {
        this.service = service;
    }

    // Récupérer les classements d'une compétition
    @GetMapping("/{idCompetition}/{typeCompetition}")
    public List<Classement> getClassements(
            @PathVariable String idCompetition,
            @PathVariable TypeCompetition typeCompetition) {
        return service.getClassement(idCompetition, typeCompetition);
    }


    @PostMapping("/mise-a-jour/{idCompetition}/{typeCompetition}")
    public List<Classement> updateClassements(
            @PathVariable String idCompetition,
            @PathVariable TypeCompetition typeCompetition) {
        return service.updateClassements(idCompetition, typeCompetition);
    }


}