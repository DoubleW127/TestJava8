package com.doublew.TestStreamApi;

import com.doublew.testJava8.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 映射：
 *   map —— 接收 Lambda，将元素转换成其它形式或提取信息。接收一个函数作为参数，
 *          该函数会被应用到每个元素上，并将其映射成一个新的元素；
 *   flatMap —— 接收一个函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连接成一个流；
 */
public class TestStreamApi03 {

    List<Employee> emplpoyees = Arrays.asList(
            new Employee("张四", 33, 988.88),
            new Employee("张三", 23, 888.88),
            new Employee("张吴", 43, 9988.88),
            new Employee("张吴", 43, 9988.88));

    @Test
    public void test01() {
        //中间操作：不会执行任何操作
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream().map(l->{
            return l.toUpperCase();
        }).forEach(System.out::print);
    }

    @Test
    public void test02() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        emplpoyees.
                stream().
                map((e)->e.getName()).forEach(System.out::print);
        emplpoyees.
                stream().
                map(Employee::getName).forEach(System.out::print);

        System.out.println("--------------------------------");
        list.stream()
                .flatMap(TestStreamApi03::filterCharacter)
                .forEach(System.out::print);
        list.stream()
                .flatMap(str->TestStreamApi03.filterCharacter(str))
                .forEach(System.out::print);

    }

    public static Stream<Character> filterCharacter(String str){
        ArrayList<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

}
