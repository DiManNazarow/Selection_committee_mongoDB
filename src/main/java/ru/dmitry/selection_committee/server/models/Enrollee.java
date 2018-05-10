package ru.dmitry.selection_committee.server.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Модель "Абитуриент"
 */
public class Enrollee extends User {
    /**
     * Дата рождения
     */
    private String dateBirth;
    /**
     * Номер пасспорта
     */
    private String passportNumber;
    /**
     * Адресс регистрации
     */
    private String registrationAddress;
    /**
     * Телефон
     */
    private String phone;
    /**
     * Город
     */
    private String city;
    /**
     * Улица
     */
    private String street;
    /**
     * Дом
     */
    private String house;
    /**
     * Квартира
     */
    private String flat;
    /**
     * Номер аттестата
     */
    private String attestatNumber;
    /**
     * Название школы
     */
    private String schoolName;
    /**
     * Ссылка на изображение паспорта
     */
    private String pasportLink;
    /**
     * Ссылка на изображение аттестата
     */
    private String attestatLink;
    /**
     * Список специальностей
     */
    @DBRef
    private List<Speciality> specialities;
    /**
     * Статус обучающегося
     */
    private int status = Status.ENTERED.getCode();

    public Enrollee(){
        super();
    }

    @Override
    public int getRole() {
        return Role.ENROLLE.getRoleCode();
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getAttestatNumber() {
        return attestatNumber;
    }

    public void setAttestatNumber(String attestatNumber) {
        this.attestatNumber = attestatNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPasportLink() {
        return pasportLink;
    }

    public void setPasportLink(String pasportLink) {
        this.pasportLink = pasportLink;
    }

    public String getAttestatLink() {
        return attestatLink;
    }

    public void setAttestatLink(String attestatLink) {
        this.attestatLink = attestatLink;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
