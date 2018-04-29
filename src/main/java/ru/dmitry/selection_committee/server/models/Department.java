package ru.dmitry.selection_committee.server.models;

import org.springframework.data.annotation.Id;

/**
 * Модель "Факультет"
 */
public class Department {

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
     * Идентификатор учебного заведения
     * {@link Institution}
     */
    private String institutionId;

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

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }
}
