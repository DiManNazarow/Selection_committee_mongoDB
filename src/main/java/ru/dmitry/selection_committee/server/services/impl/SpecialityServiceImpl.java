package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.repositories.SpecialityRepository;
import ru.dmitry.selection_committee.server.services.SpecialityService;

import java.util.ArrayList;
import java.util.List;

public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public String insert(Speciality speciality) {
        return specialityRepository.save(speciality).id;
    }

    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    @Override
    public List<String> getSpecialityNames() {
        List<String> names = new ArrayList<>();
        specialityRepository.findAll().forEach(institution -> names.add(institution.getName()));
        return names;
    }

    @Override
    public Speciality findByName(String name) {
        return specialityRepository.findByName(name);
    }
}
