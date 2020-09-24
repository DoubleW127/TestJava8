package com.doublew.testJava8Time;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestLocalDateTime {

    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2019, 9, 26, 21, 48);
        System.out.println(ldt2);

        //可以进行年月计算
        //加两年
        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);
        //减两个月
        LocalDateTime ldt4 = ldt.minusMonths(2);
        System.out.println(ldt4);

        System.out.println("年："+ldt.getYear());
        System.out.println("月："+ldt.getMonth());
        System.out.println("月："+ldt.getMonthValue());
        System.out.println("日："+ldt.getDayOfMonth());
        System.out.println("日："+ldt.getDayOfWeek());
        System.out.println("日："+ldt.getDayOfYear());
    }

    //2. Instant 时间戳（以 Unix 元年： 1970年1月1日 00:00:00 到某个时间之间的毫秒值）
    @Test
    public void test2(){
        Instant ins1 = Instant.now();//默认获取 UTC 时区
        System.out.println(ins1);

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
        //转时间戳
        System.out.println(ins1.getEpochSecond());
    }

    //3. Druation: 计算两个“时间”之间的间隔
    //Period: 计算两个“日期”之间的间隔
    @Test
    public void test3(){
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        Instant ins2 = Instant.now();

        Duration btn = Duration.between(ins1, ins2);
        System.out.println(btn.getSeconds());//秒
        System.out.println(btn.toMillis());//毫秒

        System.out.println("----------------------------------------");

        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        LocalTime lt2 = LocalTime.now();

        System.out.println(Duration.between(lt1, lt2).toMillis());

        System.out.println("----------------------------------------");

        LocalDate of = LocalDate.of(2020, 8, 20);
        LocalDate now = LocalDate.now();
        Period period = Period.between(of, now);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }

    //自定义时间格式化
    @Test
    public void test4() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        System.out.println(dtf.format(ldt));//2019-09-26 23:11:53

        System.out.println("-------------------------------------------");

        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
        System.out.println(dtf.format(zdt));
    }
}
