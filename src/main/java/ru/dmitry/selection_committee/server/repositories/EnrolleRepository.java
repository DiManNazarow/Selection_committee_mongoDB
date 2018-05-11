package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.User;

import java.util.List;

public interface EnrolleRepository extends MongoRepository<Enrollee, String> {

    User findUserByEmailAndPassword(String email, String password);

    User findUserByLoginAndPassword(String login, String password);

    List<Enrollee> findByEnteredYear(int enteredYear);

    List<Enrollee> findByFirstNameContainingOrSecondNameContainingOrPatronymicContaining(String firstName, String secondName, String patronymic);

}
