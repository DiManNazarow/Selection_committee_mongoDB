package ru.dmitry.selection_committee.server.models;

import org.springframework.data.annotation.Id;

/**
 * Модель "Кафедра"
 */
public class Pulpit {

    @Id
    public String id;
    /**
     * Название
     */
    private String name;
    /**
     * Описание
     */
    private String description;
    /**
     * Идентификатор факультета
     * {@link Department}
     */
    private String departmentId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
