package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Institution;

public interface InstitutionRepository extends MongoRepository<Institution, String> {



}
