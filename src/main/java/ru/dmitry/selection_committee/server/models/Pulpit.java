package ru.dmitry.selection_committee.server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Модель "Кафедра"
 */
public class Pulpit {

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
     * Идентификатор факультета
     * {@link Department}
     */
    @DBRef
    private Department department;

    @DBRef
    private List<Speciality> specialities;

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

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
