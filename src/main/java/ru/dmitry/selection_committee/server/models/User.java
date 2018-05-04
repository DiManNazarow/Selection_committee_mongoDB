package ru.dmitry.selection_committee.server.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Общая модель пользователя
 */
public abstract class User {

    @Id
    public String id;
    /**
     * Имя
     */
    private String firstName;
    /**
     * Фамилия
     */
    private String secondName;
    /**
     * Отчество
     */
    private String patronymic;
    /**
     * Электронная почта
     */
    private String email;
    /**
     * Логин
     */
    private String login;
    /**
     * Пароль в виде хеша
     */
    private String password;
    /**
     * Роль
     * {@link Role}
     */
    private int role;

    public User(){
        role = getRole();
    }

    public abstract int getRole();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(@NotNull String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = String.valueOf(password.hashCode());
    }
}
