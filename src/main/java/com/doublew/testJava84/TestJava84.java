package com.doublew.testJava84;

//java8内置的四大函数式接口
//        1.Consumer<T>：消费型接口
//          void accept(T t);
//
//        2.Supplier<T>: 供给型接口
//          T get();
//
//        3.Function<T, R>：函数型接口
//          R apply(T t);
//
//        4.Predicate<T>：断言型接口
//          boolean test(T t);

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestJava84 {
    //消费型接口
    @Test
    public void test01(){
        happy(100d,m->{
            System.out.println("今天吃饭，花了" + m + "元钱");
        });
    }

    public void happy(double money, Consumer con){
        con.accept(money);
    }
    //供给型接口
    @Test
    public void test02(){
        System.out.println(getList(10, () -> {
            return (int) (Math.random() * 100);
        }));
    }

    public List<Integer> getList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    //函数型接口
    @Test
    public void test03(){
        System.out.println(operationString("wangmeng", x -> x.toUpperCase()));
    }
    public String operationString(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    //断言型接口
    @Test
    public void test04(){
        System.out.println(operationBoolean(1, x -> x > 0));
    }

    public Boolean operationBoolean(int num, Predicate<Integer> pre){
        return pre.test(num);
    }

}
