package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Speciality;

import java.util.List;

public interface SpecialityRepository extends MongoRepository<Speciality, String> {

    Speciality findByName(String name);

    List<Speciality> findByNameContaining(String name);

}
