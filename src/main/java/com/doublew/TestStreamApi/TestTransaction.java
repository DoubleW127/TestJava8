package com.doublew.TestStreamApi;

import com.doublew.testJava8.pojo.Trader;
import com.doublew.testJava8.pojo.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class TestTransaction {
    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );
    }

    //1.找出2011年发生的所有交易，并按交易额排序（从高到底）
    @Test
    public void test1(){
        List<Boolean> collect = transactions.stream().map(tran -> {
            return 2011 == tran.getYear();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        transactions
                .stream()
                .filter(tran -> tran.getYear() == 2011)
                .sorted((t1, t2) -> t2.getValue() - t1.getValue())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        transactions
                .stream()
                .filter(tran -> tran.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        transactions
                .stream()
                .filter(tran -> tran.getYear() == 2011)
                .sorted((x,y)->Integer.compare(y.getValue(),x.getValue()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

    //2.交易员都在哪些不同的城市工作过？
    @Test
    public void test2(){
        transactions
                .stream()
                .map(tr -> tr.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    //3.查找来自剑桥的交易员，并按姓名排序
    @Test
    public void test3(){
        transactions
                .stream()
                .map(tran->tran.getTrader())
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    //4.返回所有交易员的姓名字符串，并按字母顺序排序
    @Test
    public void test4(){
        transactions
                .stream()
                .map(tran->tran.getTrader().getName())
                .sorted((x,y)-> x.compareTo(y))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        String s = transactions
                .stream()
                .map(tran -> tran.getTrader().getName())
                .sorted()
                .collect(Collectors.joining());

        String s2 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",String::concat);
        System.out.println(s2);


    }

    //5.有没有交易员是在米兰工作的？
    @Test
    public void test5(){
        boolean boo = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }

    //6.打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6(){
        Integer sum = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::sum);
        Optional<Integer> sum1 = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .map(transaction -> transaction.getValue())
                .reduce(Integer::sum);
        sum1.get();
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .map(transaction -> transaction.getValue())
                .mapToInt(t->t).sum();
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .map(transaction -> transaction.getValue())
                .collect(Collectors.summarizingInt(t->t));

    }

    //7.所有交易中，最高的交易额是多少？
    @Test
    public void test7(){
        OptionalInt max = transactions.stream()
                .map(Transaction::getValue)
                .mapToInt(t -> t).max();
        System.out.println(max.getAsInt());

        System.out.println("-----------------------");
        Optional<Integer> max1 = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);
        System.out.println(max1.get());
    }

    //8.找到交易额最小的交易
    @Test
    public void test8(){
        OptionalInt min = transactions.stream()
                .map(Transaction::getValue)
                .mapToInt(t -> t).min();
        transactions.stream()
                .filter(t -> t.getValue() <= min.getAsInt())
                .forEach(System.out::println);

        System.out.println("---------------------");
        Optional<Transaction> min1 = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));
        //                .min(Comparator.comparingInt(x -> x.getValue()));
        //                .min((x,y) -> Integer.compare(x.getValue(),y.getValue()));
        System.out.println(min1.get());

    }
}
