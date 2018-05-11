package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.Pulpit;

import java.util.List;

public interface PulpitService {

    String insert(Pulpit department);

    List<Pulpit> getAll();

    List<String> getPulpitNames();

    Pulpit findByFullName(String fullName);

    List<Pulpit> findByName(String name);

    List<Pulpit> findByInstitution(String institution);

    List<Pulpit> findByDepartment(String department);

    List<Pulpit> findByInstitutionAndDepartment(String institution, String department);

    void delete(Pulpit pulpit);

}
