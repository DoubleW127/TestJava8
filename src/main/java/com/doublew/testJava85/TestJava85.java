package com.doublew.testJava85;

import com.doublew.testJava8.pojo.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

public class TestJava85 {

    //方法引用
    //对象::实例方法名
    @Test
     public void test01(){
        Consumer con = (x)-> System.out.println(x);
        PrintStream ps = System.out;
        Consumer con2 = ps::println;
        Consumer con3 = System.out::println;
        con3.accept("wangmeng");
     }

    @Test
    public void test02(){
        Employee e = new Employee();
        e.setName("wangmeng");
        Supplier s  = ()->e.getName();
        Supplier s2 = e::getName;
        System.out.println(s2.get());

        Employee e2 = new Employee();
        e2.setAge(26);
        Supplier s3 = e2::getAge;
        Supplier s4 = ()->{
           return  e2.getName();
        };
        System.out.println(s3.get());

    }
    //方法引用
    //类::静态方法
    @Test
    public void test03(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        Comparator<Integer> com2 = Integer::compare;

    }

    //方法引用
    //类::实例方法
    @Test
    public void test04(){
        BiPredicate<String,String> bp = (x,y)->x.equals(y);
        BiPredicate<String,String> bp2 = String::equals;

    }

    //构造器引用
    @Test
    public void test05(){
        Supplier sup = ()->new Employee();

        Supplier<Employee> sup2 = Employee::new;
        Employee e = sup2.get();
        System.out.println(e);

        System.out.println("-------------------------------");
        Function<Integer,Employee> fun  = (x)->new Employee(x);
        Function<Integer,Employee> fun2  = Employee::new;
        System.out.println(fun2.apply(111));

        System.out.println("---------------------------------");
        BiFunction<Integer,Integer,Employee> bf = Employee::new;
        System.out.println(bf.apply(123,145));
    }

    //数组引用
    //type::new
    @Test
    public void test06(){
        Function<Integer,String[]> fun  = (x)->new String[x];

        Function<Integer,String[]> fun2 = String[]::new;
        System.out.println(fun2.apply(10).length);

    }



}
