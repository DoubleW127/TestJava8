package com.doublew.testJava8.service;

import com.doublew.testJava8.inter.MyPredicate;
import com.doublew.testJava8.pojo.Employee;

public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee e) {
        return e.getAge()>35;
    }
}
