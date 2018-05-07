package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Department;

import java.util.List;

public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department findByFullName(String fullName);

    List<Department> findByFullNameContainingOrShortNameContaining(String shortName, String fullName);

}
