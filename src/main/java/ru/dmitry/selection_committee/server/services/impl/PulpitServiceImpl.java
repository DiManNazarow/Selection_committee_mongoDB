package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Pulpit;
import ru.dmitry.selection_committee.server.repositories.PulpitRepository;
import ru.dmitry.selection_committee.server.services.PulpitService;

import java.util.ArrayList;
import java.util.List;

public class PulpitServiceImpl implements PulpitService {

    @Autowired
    private PulpitRepository pulpitRepository;

    @Override
    public String insert(Pulpit pulpit) {
        return pulpitRepository.save(pulpit).id;
    }

    @Override
    public List<Pulpit> getAll() {
        return pulpitRepository.findAll();
    }

    @Override
    public List<String> getPulpitNames() {
        List<String> names = new ArrayList<>();
        pulpitRepository.findAll().forEach(institution -> names.add(institution.getFullName()));
        return names;
    }

    @Override
    public Pulpit findByFullName(String fullName) {
        return pulpitRepository.findByFullName(fullName);
    }

}
