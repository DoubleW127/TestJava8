package com.doublew.testJava82;

import com.doublew.testJava82.inter.MyFun;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Consumer;

public class TestLambda2 {

    //语法格式一：无参，无返回值
    @Test
    public void test01(){

        int num = 0;//JDK1.7前，必须是final,1.8不用加，但本质上还是final
        //内部类写法
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world"+num);
            }
        };
        r.run();
        System.out.println("===============================");
        //Lambda写法
        Runnable r1 = () -> { System.out.println("hello Lambda"); };
        r1.run();
    }

    //语法格式二：有一个参数，无返回值
    @Test
    public void test02(){
        Consumer c = (x) ->{System.out.println(x); };
        c.accept("有一个参数，无返回值");

        System.out.println("=================================");
        //语法格式三：若只有一个参数，小括号可以不写
        Consumer con = x ->{System.out.println(x); };
        con.accept("若只有一个参数，小括号可以不写");
    }

    //语法格式四：有多个参数，有返回值，Lambda体中有多行代码
    @Test
    public void test03(){
        Comparator<Integer> com  = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        System.out.println("================");
        //语法格式五：Lambda体中只有一行语句，return和大括号可以不写
        Comparator<Integer> c  = (x,y)-> Integer.compare(x,y);
    }

    //语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”
    @Test
    public void test04(){
        //语法格式五：Lambda体中只有一行语句，return和大括号可以不写
        Comparator<Integer> c  = (Integer x,Integer y)-> Integer.compare(x,y);
        Comparator<Integer> c2  = (x,y)-> Integer.compare(x,y);
    }

    //类型推断
    @Test
    public void test05(){
        String[] s = {"aaa","bbb","bbb"};
        //无法完成类型推断
        //String[] str;
        //str = {"aaa","bbb","bbb"};
        List<String> list = new ArrayList<>();
        //类型推断，无需写出Map里面的参数类型（1.8）
        show(new HashMap<>());

    }

    public void show(Map<String, Integer> map){

    }
    //需求：对一个数进行运算
    //类型推断
    @Test
    public void test06(){
        System.out.println(operation(100,num -> num*num));

    }
    public Integer operation(Integer num, MyFun mf){
        return mf.getValue(num);
    }

}
