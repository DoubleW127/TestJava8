package com.doublew.TestStreamApi;

import com.doublew.testJava8.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class TestStreamApi04paixu {

    List<Employee> emplpoyees = Arrays.asList(
            new Employee("张四", 33, 988.88),
            new Employee("张三", 23, 888.88),
            new Employee("张吴", 43, 9988.88),
            new Employee("张吴", 43, 9988.88));

    @Test
    public void test01() {
        //中间操作：不会执行任何操作
        List<String> list = Arrays.asList("ccc", "bbb", "aaa");
        list.stream().
        sorted().forEach(System.out::print);
    }

    @Test
    public void test02() {
        emplpoyees.stream().
                sorted((x,y)->{
                    if(x.getAge()==y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else{
                        return y.getAge()-x.getAge();
                    }
                }).forEach(System.out::println);
    }



}
