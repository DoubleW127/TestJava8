package com.doublew.testJava8;

import com.doublew.testJava8.inter.MyPredicate;
import com.doublew.testJava8.pojo.Employee;
import com.doublew.testJava8.service.FilterEmployeeByAge;
import com.doublew.testJava8.service.FilterEmployeeBySalary;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.util.*;

//匿名内部类
public class AnonymousInnerClass {

    public static void main(String[] args) {
        System.out.println("aaaa");
    }

    @Test
    public void test01(){
        //匿名内部类
        Comparator<Integer> com = new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {

                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //lambda 表达式
    @Test
    public void test02(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //需求：获取当前公司中年龄大于35的员工
    List<Employee> emplpoyees = Arrays.asList(
            new Employee("张四",33,988.88),
            new Employee("张三",23,888.88),
            new Employee("张吴",43,9988.88),
            new Employee("张六",53,8998.88));

    //按年龄过滤
    public List<Employee> filterEmpAge(List<Employee> emps){
        List<Employee> result = new ArrayList<>();
        for (Employee emp:emps){
            if(emp.getAge()>=35)result.add(emp);
        }
        return result;
    }
    @Test
    public void test03(){
        List<Employee> employees = filterEmpAge(emplpoyees);
        for (Employee emp:employees
             ) {
            System.out.println(emp);
        }

        System.out.println(filterEmpAge(emplpoyees).size());
    }

    //按工资过滤
    public List<Employee> filterEmpSalary(List<Employee> emps){
        List<Employee> result = new ArrayList<>();
        for (Employee emp:emps){
            if(emp.getSalary()>=1000)result.add(emp);
        }
        return result;
    }
    @Test
    public void test04(){
        List<Employee> employees = filterEmpSalary(emplpoyees);
        for (Employee emp:employees
        ) {
            System.out.println(emp);
        }
    }

    //优化方式一：策略设计模式
    public List<Employee> filterEmp(List<Employee> emps,MyPredicate<Employee> mp) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp:emps){
            if(mp.test(emp))result.add(emp);
        }
        return result;

}

    @Test
    public void test05(){
        List<Employee> employees = filterEmp(emplpoyees,new FilterEmployeeByAge());
        for (Employee emp:employees
        ) {
            System.out.println(emp);
        }
    }

    @Test
    public void test06(){
        List<Employee> employees = filterEmp(emplpoyees,new FilterEmployeeBySalary());
        for (Employee emp:employees
        ) {
            System.out.println(emp);
        }
    }

    //优化方式二:匿名内部类
    @Test
    public void test07(){
        List<Employee> employees = filterEmp(emplpoyees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee e) {
                return e.getAge()>35;
            }
        });
        for (Employee emp:employees
        ) {
            System.out.println(emp);
        }
    }
    //优化方式三：lambda表达式
    @Test
    public void test08(){
        filterEmp(emplpoyees,(employee) -> employee.getAge()>30).forEach(System.out::println);

    }
    //优化方式四：stream API
    @Test
    public void test09(){
        //filterEmp(emplpoyees,(employee) -> employee.getAge()>30).forEach(System.out::println);
        emplpoyees
                .stream()
                .filter(employee -> employee.getAge()>35)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("================================");

        emplpoyees.stream().map(Employee::getName).forEach(System.out::println);
    }
}
