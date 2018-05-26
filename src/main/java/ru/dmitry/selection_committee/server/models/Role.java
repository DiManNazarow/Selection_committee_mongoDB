package ru.dmitry.selection_committee.server.models;

/**
 * Перечисление ролей пользователя
 */
public enum  Role {

    ENROLLE(1), ADMIN(0);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRoleCode() {
        return role;
    }

    public static Role getByType(int type){
        switch (type){
            case 1: return ENROLLE;
            case 0: return ADMIN;
            default: return null;
        }
    }

}
