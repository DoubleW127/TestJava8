package com.doublew.TestStreamApi;

import com.doublew.testJava8.pojo.Employee;
import com.doublew.testJava8.pojo.Employee.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestStreamApi04lianxi {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18,9999.99, Status.FREE),
            new Employee("李四", 38,5555.99, Status.BUSY),
            new Employee("王五", 50,6666.66, Status.VOCATION),
            new Employee("赵六", 16,3333.33, Status.FREE),
            new Employee("田七", 10,7777.77, Status.BUSY),
            new Employee("田七", 16,8888.88, Status.VOCATION)
    );

    /**
     * 1.给定一个数列表，返回一个由每个数的平方根构成的列表；
     *   给定【1，2，3，4，5】，应返回【1，4，9，16, 25】
     */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map(e->e*e).collect(Collectors.toList()).forEach(System.out::print);

    }

    /**
     * 2.用 map 和 reduce 方法数一数流中有多少个Employee?
     */
    @Test
    public void test2(){
        Integer reduce = emps.stream().map(e -> 1).reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        Optional<Integer> opt = emps.stream().map(e -> 1).reduce((x, y) -> Integer.sum(x, y));
        System.out.println(opt.get());
        opt = emps.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(opt.get());
        long count = emps.stream().count();
        System.out.println(count);

        System.out.println(reduce);
    }

}
