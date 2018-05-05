package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Department;
import ru.dmitry.selection_committee.server.repositories.DepartmentRepository;
import ru.dmitry.selection_committee.server.services.DepartmentService;

import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

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
}
