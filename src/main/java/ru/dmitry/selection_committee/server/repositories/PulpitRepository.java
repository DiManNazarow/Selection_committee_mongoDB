package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Pulpit;

import java.util.List;

public interface PulpitRepository extends MongoRepository<Pulpit, String> {

    Pulpit findByFullName(String fullName);

    List<Pulpit> findByShortNameContainingOrFullNameContaining(String shortName, String fullName);


}
