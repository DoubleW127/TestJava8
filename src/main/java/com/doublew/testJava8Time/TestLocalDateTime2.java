package com.doublew.testJava8Time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime2 {

    //TemporalAdjuster 时间矫正器
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.with(l->{
            LocalDateTime ldt5 = (LocalDateTime) l;
            DayOfWeek dow = ldt5.getDayOfWeek();
            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt5.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt5.plusDays(2);
            }else{
                return ldt5.plusDays(1);
            }
        });
        System.out.println(ldt4);
    }

    @Test
    public void test2(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        System.out.println(strDate);//2019-09-27

        DateTimeFormatter dtf2 = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(ldt.format(dtf2));//2019-09-27T08:34:51.378

        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strDate2 = ldt.format(dtf3);
        System.out.println(strDate2);//2019-09-27 08:34:51

        LocalDateTime newDate = LocalDateTime.parse(strDate2, dtf3);
        System.out.println(newDate);//2019-09-27T08:38:09
    }

    //支持的时区
    @Test
    public void test3(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    //带时区的时间
    @Test
    public void test4(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        System.out.println(ldt);

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.of("Europe/Tallinn"));
        System.out.println(zdt);
    }
}
