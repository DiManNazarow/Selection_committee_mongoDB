package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.repositories.InstitutionRepository;
import ru.dmitry.selection_committee.server.services.InstitutionService;

import java.util.List;

public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public String insert(Institution institution) {
        return institutionRepository.insert(institution).id;
    }

    @Override
    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }
}
