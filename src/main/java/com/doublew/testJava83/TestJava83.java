package com.doublew.testJava83;

import com.doublew.testJava8.pojo.Employee;
import com.doublew.testJava83.inter.MyFunInter2;
import com.doublew.testJava83.inter.MyfunInter;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestJava83 {

    List<Employee> emplpoyees = Arrays.asList(
            new Employee("张四",33,988.88),
            new Employee("张三",23,888.88),
            new Employee("张吴",43,9988.88),
            new Employee("张六",53,8998.88));

    //需求1：调用Collections.sort方法，自定义排序，Employee先按年龄排序，再按名称排序
    @Test
    public void test01(){
        Collections.sort(emplpoyees,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        emplpoyees.forEach(e->{
            System.out.println(e);
        });
    }
    //需求2：定义函数式接口MyFunInter，接口中定义方法getValue,声明类，类中方法 将接口作为参数，将传入的字符串参数转大写，截取
    @Test
    public void test02(){
        System.out.println(toCast("hello wangmeng", x -> x.toUpperCase()));
        System.out.println(toCast("hellowangmeng", x -> x.substring(2,4)));
    }
    //策略设计模式
    public String toCast(String data, MyfunInter mfi){
        return mfi.getValue(data);
    }

    //需求3：

    @Test
    public void test03(){
        System.out.println(longMethod(2l, 5l, (x, y) -> x + y));
        System.out.println(longMethod(2l, 5l, (x, y) -> x * y));
    }
    //策略设计模式
    public Long longMethod(Long t1,Long t2, MyFunInter2<Long, Long> mfi2){
        return mfi2.myMethod(t1,t2);
    }
}
