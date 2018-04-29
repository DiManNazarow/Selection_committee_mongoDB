package ru.dmitry.selection_committee.server.services.impl;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.User;
import ru.dmitry.selection_committee.server.repositories.UserRepository;
import ru.dmitry.selection_committee.server.services.UserServices;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class UserServicesImpl implements UserServices {

    private final String EMAIL_CHAR = "@";

    @Autowired
    private UserRepository userRepository;

    @Override
    @Nullable
    public User findUser(String searchValue, String password) {
        if (!AppTextUtils.isTextEmpty(searchValue) && !AppTextUtils.isTextEmpty(password)){
            if (searchValue.contains(EMAIL_CHAR)){
                return userRepository.findUserByEmailAndPassword(searchValue, password);
            } else {
                return userRepository.findUserByLoginAndPassword(searchValue, password);
            }
        }
        return null;
    }

    @Override
    public String insertUser(User user) {
        return userRepository.insert(user).id;
    }
}
