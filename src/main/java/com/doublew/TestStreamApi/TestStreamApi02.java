package com.doublew.TestStreamApi;

import com.doublew.testJava8.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
//筛选和切片
/*filter —— 接收 Lambda，从流中排除某些元素；
   limit —— 截断流，使其元素不超过给定数量；
   skip(n) —— 跳过元素，返回一个仍掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补；
   distinct —— 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素。*/
public class TestStreamApi02 {

    List<Employee> emplpoyees = Arrays.asList(
            new Employee("张四", 33, 988.88),
            new Employee("张三", 23, 888.88),
            new Employee("张吴", 43, 9988.88),
            new Employee("张吴", 43, 9988.88));

    @Test
    public void test01() {
        //中间操作：不会执行任何操作
        Stream<Employee> employeeStream = emplpoyees.
                stream().
                filter(e -> {
                    System.out.println("Stream API 中间操作");
                    return e.getAge() > 34;
                });
        //终止操作：一次性执行全部内容，即“惰性求值”
        employeeStream.forEach(System.out::print);

    }

    @Test
    public void test02() {
        emplpoyees.
                stream().
                filter(e -> {
                    return e.getAge() > 34;
                }).
                limit(1).
                forEach(System.out::print);

    }

    @Test
    public void test03() {
        emplpoyees.
                stream().
                filter(e -> {
                    return e.getAge() > 34;
                }).
                skip(1).
                forEach(System.out::print);

    }

    @Test
    public void test04() {
        emplpoyees.
                stream().
                filter(e -> {
                    return e.getAge() > 34;
                }).
                distinct().
                forEach(System.out::print);

    }

}
