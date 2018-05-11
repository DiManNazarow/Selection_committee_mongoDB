package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.User;
import ru.dmitry.selection_committee.server.repositories.AdminRepository;
import ru.dmitry.selection_committee.server.repositories.EnrolleRepository;
import ru.dmitry.selection_committee.server.services.UserServices;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserServicesImpl implements UserServices {

    private final String EMAIL_CHAR = "@";

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EnrolleRepository enrolleRepository;

    @Override
    public User findUser(String searchValue, String password) {
        User user = null;
        if (!AppTextUtils.isTextEmpty(searchValue) && !AppTextUtils.isTextEmpty(password)){
            if (searchValue.contains(EMAIL_CHAR)){
                user = adminRepository.findUserByEmailAndPassword(searchValue, password);
                if (user == null) {
                    user = enrolleRepository.findUserByEmailAndPassword(searchValue, password);
                }
            } else {
                user = adminRepository.findUserByLoginAndPassword(searchValue, password);
                if (user == null) {
                    user = enrolleRepository.findUserByLoginAndPassword(searchValue, password);
                }
            }
        }
        return user;
    }

    @Override
    public String insertAdmin(Admin admin) {
        return adminRepository.save(admin).id;
    }

    @Override
    public String insertEnrolle(Enrollee enrollee) {
        return enrolleRepository.save(enrollee).id;
    }


    @Override
    public List<Enrollee> getEnrolleByEnteredYear(int year) {
        return enrolleRepository.findByEnteredYear(year);
    }

    @Override
    public List<Enrollee> getAllEnrolle() {
        return enrolleRepository.findAll();
    }

    @Override
    public List<Enrollee> getEnrolleByName(String name) {
        return enrolleRepository.findByFirstNameContainingOrSecondNameContainingOrPatronymicContaining(name, name, name);
    }

    @Override
    public void deleteEnrolle(Enrollee enrollee) {
        enrolleRepository.delete(enrollee);
    }

    @Override
    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }
}
