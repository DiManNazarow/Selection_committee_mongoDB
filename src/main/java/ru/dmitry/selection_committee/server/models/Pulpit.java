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
    private String departmentId;

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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
