package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Department;

public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department findByFullName(String fullName);

}
