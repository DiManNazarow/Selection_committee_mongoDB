package ru.dmitry.selection_committee.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Модель "Факультет"
 */
public class Department {

    @Id
    public String id;
    /**
     * Короткое название
     */
    private String shortName;
    /**
     * Полное название
     */
    private String fullName;
    /**
     * Описание
     */
    private String description;
    /**
     * Идентификатор учебного заведения
     * {@link Institution}
     */
    @DBRef
    private Institution institution;

    @DBRef
    private List<Pulpit> pulpits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pulpit> getPulpits() {
        return pulpits;
    }

    public void setPulpits(List<Pulpit> pulpits) {
        this.pulpits = pulpits;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
