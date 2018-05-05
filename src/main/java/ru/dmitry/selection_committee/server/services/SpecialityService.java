package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.Pulpit;
import ru.dmitry.selection_committee.server.models.Speciality;

import java.util.List;

public interface SpecialityService {

    String insert(Speciality speciality);

    List<Speciality> getAll();

    List<String> getSpecialityNames();

    Speciality findByName(String name);

}
