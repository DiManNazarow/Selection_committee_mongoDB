package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.User;

public interface UserServices {

    User findUser(String searchValue, String password);

    String insertUser(User user);

}
