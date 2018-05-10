package ru.dmitry.selection_committee.utils;

import ru.dmitry.selection_committee.server.models.Speciality;

public class AppTextUtils {

    public static boolean isTextEmpty(CharSequence text){
        return text == null || text.length() == 0;
    }

    public static String getSpecialityNameWithCode(Speciality speciality){
        return String.format("%s %s", speciality.getCode(), speciality.getName());
    }

}
