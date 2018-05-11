package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Department;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.repositories.DepartmentRepository;
import ru.dmitry.selection_committee.server.repositories.InstitutionRepository;
import ru.dmitry.selection_committee.server.services.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
//    @Autowired
//    private InstitutionRepository institutionRepository;

    @Override
    public String insert(Department department) {
        return departmentRepository.save(department).id;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<String> getDepartmentNames() {
        List<String> names = new ArrayList<>();
        departmentRepository.findAll().forEach(institution -> names.add(institution.getFullName()));
        return names;
    }

    @Override
    public Department findByFullName(String fullName) {
        return departmentRepository.findByFullName(fullName);
    }

    @Override
    public List<Department> findByInstitutionName(String name) {
        return departmentRepository.findAll().stream().filter(department -> department.getInstitution().getShortName().contains(name) || department.getInstitution().getFullName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Department> findByName(String name) {
        return departmentRepository.findByFullNameContainingOrShortNameContaining(name, name);
    }

    @Override
    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
