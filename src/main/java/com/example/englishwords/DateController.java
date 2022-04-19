package com.example.englishwords;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateController {
    public String receiveDateToday(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

}
