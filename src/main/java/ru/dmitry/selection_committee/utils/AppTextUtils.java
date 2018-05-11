package ru.dmitry.selection_committee.utils;

import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Speciality;

public class AppTextUtils {

    public static boolean isTextEmpty(CharSequence text){
        return text == null || text.length() == 0;
    }

    public static String getSpecialityNameWithCode(Speciality speciality){
        return String.format("%s %s", speciality.getCode(), speciality.getName());
    }

    public static String getSpecialityNames(Enrollee enrollee){
        StringBuilder specialities = new StringBuilder();
        if (enrollee.getSpecialities().size() == 1){
            specialities = new StringBuilder(getSpecialityNameWithCode(enrollee.getSpecialities().get(0)));
            return specialities.toString();
        } else if (enrollee.getSpecialities().size() > 1){
            for (Speciality speciality : enrollee.getSpecialities()){
                specialities.append(String.format("%s \\n", getSpecialityNameWithCode(speciality)));
            }
            return specialities.toString();
        }
        return specialities.toString();
    }

}
