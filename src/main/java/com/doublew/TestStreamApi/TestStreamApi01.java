package com.doublew.TestStreamApi;

import com.doublew.testJava8.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamApi01 {

    //创建Stream流
    @Test
    public void test1(){
        //1.可以通过 Collection 系列集合提供的 stream() 或 paralleStream()
        List list = new ArrayList<>();
        Stream stream = list.stream();

        //2.通过 Arrays 中的静态方法 stream() 获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> employeeStream = Arrays.stream(employees);

        //3.通过 Stream 类中的静态方法 of()
        Stream<String> aaStream = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> iterateStream = Stream.iterate(0, x -> x + 2);
        iterateStream.limit(10).forEach(System.out::print);
        //输出0 2 4 6 8 10 12 14 16 18

        //生成/
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);



    }
}
