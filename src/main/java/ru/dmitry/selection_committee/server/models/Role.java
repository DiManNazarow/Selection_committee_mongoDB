package ru.dmitry.selection_committee.server.models;

import com.sun.istack.internal.Nullable;

public enum  Role {

    ENROLLE(1), ADMIN(0);

    private int role;

    Role(int role) {
        this.role = role;
    }

    public int getRoleCode() {
        return role;
    }

    @Nullable
    public static Role getByType(int type){
        switch (type){
            case 1: return ENROLLE;
            case 0: return ADMIN;
            default: return null;
        }
    }

}