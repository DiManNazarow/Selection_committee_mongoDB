package ru.dmitry.selection_committee.utils;

import java.time.LocalDate;
import java.util.Calendar;

public class DateUtils {

//    public static String getDate(LocalDate date){
//
//    }

    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }


}
