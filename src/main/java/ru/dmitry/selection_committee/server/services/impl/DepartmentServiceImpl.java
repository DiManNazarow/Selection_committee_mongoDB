package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Department;
import ru.dmitry.selection_committee.server.repositories.DepartmentRepository;
import ru.dmitry.selection_committee.server.services.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public String insert(Department department) {
        return departmentRepository.insert(department).id;
    }
}
