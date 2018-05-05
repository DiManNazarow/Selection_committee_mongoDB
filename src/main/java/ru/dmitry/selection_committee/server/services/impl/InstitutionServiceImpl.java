package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.repositories.InstitutionRepository;
import ru.dmitry.selection_committee.server.services.InstitutionService;

import java.util.ArrayList;
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

    @Override
    public Institution findByFullName(String fullName) {
        return institutionRepository.findByFullName(fullName);
    }

    @Override
    public List<String> getInstitutionsNames(){
        List<String> names = new ArrayList<>();
        institutionRepository.findAll().forEach(institution -> names.add(institution.getFullName()));
        return names;
    }

}
