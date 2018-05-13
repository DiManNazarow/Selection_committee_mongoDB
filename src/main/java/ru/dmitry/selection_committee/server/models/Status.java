package ru.dmitry.selection_committee.server.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Status {

    ENTERED(0, "Поступает"), IN_FIRST_PART(1, "В первой волне"), IN_SECOND_PART(2, "Во второй волне"), STUDYING(3, "Обучается"), DEDUCTED(4, "Отчислен"), FINISHED(5, "Закончил");

    private int code;

    private String name;

    Status(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Status getByCode(int code){
        return values()[code];
    }

    public static Status getByName(String name){
        switch (name){
            case "Поступает": return ENTERED;
            case "Обучается": return STUDYING;
            case "Отчислен": return DEDUCTED;
            case "Закончил": return FINISHED;
            default: return ENTERED;
        }
    }

    public static List<String> getStatusNames(){
        List<String> names = new ArrayList<>();
        for (Status s : values()){
            names.add(s.name);
        }
        return names;
    }

}
