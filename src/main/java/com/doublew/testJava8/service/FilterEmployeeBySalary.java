package com.doublew.testJava8.service;

import com.doublew.testJava8.inter.MyPredicate;
import com.doublew.testJava8.pojo.Employee;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee e) {
        return e.getSalary()>1000;
    }
}
