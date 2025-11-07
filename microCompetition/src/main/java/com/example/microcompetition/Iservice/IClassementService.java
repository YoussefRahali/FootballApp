package com.example.microcompetition.Iservice;

import com.example.microcompetition.entity.Classement;
import com.example.microcompetition.entity.TypeCompetition;

import java.util.List;

public interface IClassementService {

    List<Classement> updateClassements(String idCompetition, TypeCompetition typeCompetition);

    List<Classement> getClassement(String idCompetition, TypeCompetition typeCompetition);
}
