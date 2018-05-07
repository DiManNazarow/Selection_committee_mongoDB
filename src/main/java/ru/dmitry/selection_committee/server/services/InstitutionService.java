package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.Institution;

import java.util.List;

public interface InstitutionService {

    String insert(Institution institution);

    List<Institution> getAll();

    List<String> getInstitutionsNames();

    Institution findByFullName(String fullName);

    List<Institution> filterByShortNameOrFullName(String name);

    List<Institution> filterByCity(String city);



}
