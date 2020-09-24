package com.doublew.TestStreamApi;

import com.doublew.testJava8.pojo.Employee;
import com.doublew.testJava8.pojo.Employee.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TestStreamApi04chazhaopipei {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, Status.FREE),
            new Employee("李四", 38, Status.BUSY),
            new Employee("王五", 50, Status.VOCATION),
            new Employee("赵六", 16, Status.FREE),
            new Employee("田七", 10, Status.BUSY),
            new Employee("李八", 16, Status.VOCATION)
    );

    /**
     * 查找与匹配
     * （1）allMatch —— 检查是否匹配所有元素；
     * （2）anyMatch —— 检查是否至少匹配一个元素；
     * （3）noneMatch —— 检查是否没有匹配所有元素；
     * （4）findFirst —— 返回第一个元素；
     * （5）findAny —— 返回当前流中的任意元素；
     * （6）count —— 返回流中元素的总数；
     * （7）max —— 返回流中最大值；
     * （8）min —— 返回流中最小值；
     */

    @Test
    public void test1() {
        //是否匹配所有元素
        boolean b1 = emps.stream()
                .allMatch(e -> e.getStatus().equals(Status.FREE));
        System.out.println(b1);

        //至少匹配一个元素
        boolean b2 = emps.stream()
                .anyMatch(e -> e.getStatus().equals(Status.FREE));
        System.out.println(b2);

        //没有匹配的元素
        boolean b3 = emps.stream()
                .noneMatch(e -> e.getStatus().equals(Status.FREE));
        System.out.println(b3);

        //先按工资排序，然后再找出第一个
        Optional<Employee> op1 = emps.stream()
                //                .sorted((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()))
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();

        System.out.println(op1.get());

        //返回当前流中的任意元素
        Optional<Employee> op2 = emps.parallelStream()
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(op2.get());

    }

    @Test
    public void test2() {
        //总数
        long count = emps.stream()
                .count();
        System.out.println(count);

        //工资最高
        Optional<Employee> max = emps.stream()
                //                .max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
                .max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(max.get());

        //年龄最小
        Optional<Employee> min = emps.stream()
                //                .min((x, y) -> Double.compare(x.getAge(), y.getAge()));
                .min(Comparator.comparingDouble(Employee::getAge));
        System.out.println(min.get());

        //最低工资
        Optional<Double> minSalary = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println(minSalary.get());

    }

}
