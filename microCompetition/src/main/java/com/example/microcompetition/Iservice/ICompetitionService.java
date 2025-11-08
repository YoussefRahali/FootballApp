package com.example.microcompetition.Iservice;

import com.example.microcompetition.dto.ClubDTO;
import com.example.microcompetition.entity.Competition;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ICompetitionService {

    List<Competition> getAll();

    Optional<Competition> getById(String id);

    Competition add(Competition c);

    Competition update(String id, Competition c);

    void delete(String id);


    }
