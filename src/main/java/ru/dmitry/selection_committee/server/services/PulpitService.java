package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.Pulpit;

import java.util.List;

public interface PulpitService {

    String insert(Pulpit department);

    List<Pulpit> getAll();

    List<String> getPulpitNames();

    Pulpit findByFullName(String fullName);

}
