package ru.dmitry.selection_committee.server.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.User;

/**
 * Интерфейс для взаимодействия с документом "Администротор" в БД
 */
public interface AdminRepository extends MongoRepository<Admin, String>{

    User findUserByEmailAndPassword(String email, String password);

    User findUserByLoginAndPassword(String login, String password);

}
