package ru.dmitry.selection_committee.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Модель "Специальность"
 */
public class Speciality {

    @Id
    public String id;
    /**
     * Название
     */
    private String name;
    /**
     * Код специальности
     */
    private String code;
    /**
     * Описание
     */
    private String description;
    /**
     * Идентификатор кафедры
     * {@link Pulpit}
     */
    @DBRef
    private Pulpit pulpit;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pulpit getPulpit() {
        return pulpit;
    }

    public void setPulpit(Pulpit pulpit) {
        this.pulpit = pulpit;
    }

}
