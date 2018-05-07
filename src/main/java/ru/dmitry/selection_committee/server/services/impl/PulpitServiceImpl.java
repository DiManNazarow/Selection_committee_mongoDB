package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Pulpit;
import ru.dmitry.selection_committee.server.repositories.PulpitRepository;
import ru.dmitry.selection_committee.server.services.PulpitService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Pulpit> findByName(String name) {
        return pulpitRepository.findByShortNameContainingOrFullNameContaining(name, name);
    }

    @Override
    public List<Pulpit> findByInstitution(String institution) {
        return pulpitRepository.findAll().stream().filter(pulpit ->
                pulpit.getDepartment().getInstitution().getFullName().contains(institution) ||
                        pulpit.getDepartment().getInstitution().getShortName().contains(institution)).collect(Collectors.toList());
    }

    @Override
    public List<Pulpit> findByDepartment(String department) {
        return pulpitRepository.findAll().stream().filter(pulpit ->
                pulpit.getDepartment().getFullName().contains(department) || pulpit.getDepartment().getShortName().contains(department)).collect(Collectors.toList());
    }

    @Override
    public List<Pulpit> findByInstitutionAndDepartment(String institution, String department) {
        return pulpitRepository.findAll().stream().filter(pulpit ->
                (pulpit.getDepartment().getInstitution().getShortName().contains(institution) || pulpit.getDepartment().getInstitution().getFullName().contains(institution)) &&
                        (pulpit.getDepartment().getShortName().contains(department) || pulpit.getDepartment().getFullName().contains(department))).collect(Collectors.toList());
    }

}
