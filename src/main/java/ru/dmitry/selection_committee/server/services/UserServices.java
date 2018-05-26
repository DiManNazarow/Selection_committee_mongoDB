package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.User;

import java.util.List;

/**
 * Интерфейс, содержащий методы для работы с записями таблицы «Администратор» и «Абитуриент»
 */
public interface UserServices {

    User findUser(String searchValue, String password);

    String insertAdmin(Admin user);

    String insertEnrolle(Enrollee enrollee);

    List<Enrollee> getEnrolleByEnteredYear(int year);

    List<Enrollee> getAllEnrolle();

    List<Enrollee> getEnrollesByName(String name);

    void deleteEnrolle(Enrollee enrollee);

    void deleteAdmin(Admin admin);

}
