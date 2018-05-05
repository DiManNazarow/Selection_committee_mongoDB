package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Pulpit;

public interface PulpitRepository extends MongoRepository<Pulpit, String> {

    Pulpit findByFullName(String fullName);

}
