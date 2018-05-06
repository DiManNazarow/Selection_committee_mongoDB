package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Institution;

import java.util.List;

public interface InstitutionRepository extends MongoRepository<Institution, String> {

    Institution findByFullName(String fullName);

    List<Institution> findByCityContaining(String city);

    List<Institution> findByFullNameContainingOrShortNameContaining(String shortNane, String fullName);

}
