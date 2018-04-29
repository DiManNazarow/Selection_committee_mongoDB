package ru.dmitry.selection_committee.server.models;

/**
 * Модель "Администратор"
 */
public class Admin extends User {

    public Admin(){
        super();
    }

    @Override
    protected int getRole() {
        return Role.ADMIN.getRoleCode();
    }
}
