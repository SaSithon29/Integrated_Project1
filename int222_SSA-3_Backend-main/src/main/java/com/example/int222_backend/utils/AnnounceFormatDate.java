package com.example.int222_backend.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AnnounceFormatDate {


//    Time
    public static String sqlToJson(String date){
        if(date==null) return null;
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //  บอกฟอแมทเก่า
        Date dateConvert = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
        //  แปลงข้างบนให้สนใจไทม์โซน #เผื่อไว้

        String newDate = DateTimeFormatter.ISO_INSTANT.format(dateConvert.toInstant());
        //แปลงให้เป็นไอเอสโอฟอแมท
        return newDate;

    }


}
