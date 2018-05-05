package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.Department;

import java.util.List;

public interface DepartmentService {

    String insert(Department department);

    List<Department> getAll();

    List<String> getDepartmentNames();

    Department findByFullName(String fullName);

}
