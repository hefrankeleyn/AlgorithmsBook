package com.hef.chapter2;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author lifei
 * @since 2020/10/8
 */
public class Transaction implements Comparable<Transaction> {

    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction){
        if (transaction==null || transaction.trim().equals("")){
            throw new RuntimeException("Inconsistent transaction");
        }
        try {
            String[] ta = transaction.split(" ");
            who = ta[0];
            when = new SimpleDateFormat("yyyy-MM-dd").parse(ta[1]);
            amount = Double.valueOf(ta[2]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String who(){
        return who;
    }
    public Date when(){
        return when;
    }
    public double amount(){
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "who='" + who + '\'' +
                ", when=" + when +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Transaction that) {
        if (this.amount()>that.amount()) return -1;
        else if (this.amount() < that.amount()) return 1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(who, that.who) &&
                Objects.equals(when, that.when);
    }

    @Override
    public int hashCode() {
        return Objects.hash(who, when, amount);
    }
}
