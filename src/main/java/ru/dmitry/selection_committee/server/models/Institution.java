package ru.dmitry.selection_committee.server.models;

import org.springframework.data.annotation.Id;

/**
 * Модель "Учебное заведение"
 */
public class Institution {

    @Id
    public String id;
    /**
     * Название
     */
    private String name;
    /**
     * город
     */
    private String city;
    /**
     * улица
     */
    private String street;
    /**
     * дом
     */
    private String home;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
