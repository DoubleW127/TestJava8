package com.doublew.testJava8Time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSimpleDateFormatJava8 {

    public static void main(String[] args) throws Exception{

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = ()->{return LocalDate.parse("20200903",formatter);};

        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> result = new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            result.add(pool.submit(task));
        }
        for (Future<LocalDate> dateFuture : result) {
            System.out.println(dateFuture.get());
        }

    }
}
