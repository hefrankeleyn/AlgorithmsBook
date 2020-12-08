package com.hef.chapter2.pq;

import com.hef.algorithms.fundamentals.bags_queues_stacks.Stack;
import com.hef.chapter2.Transaction;
import com.hef.commons.StdIn;

/**
 * @author lifei
 * @since 2020/10/8
 */
public class TopM {

    public static void main(String[] args) {
        int M = 10;
        MinPQ<Transaction> pq = new MinPQ<>(M+1);
        while (StdIn.hasNextLine()){
            // Create an entry from the next line and put on the PQ.
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size()>M){
                pq.delMin(); // Remove minimum if M+1 entries on the PQ.
            }
        }  // Top M entries are on the PQ.
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()){
            stack.push(pq.delMin());
        }
        for (Transaction t : stack) {
            System.out.println(t);
        }
    }
}
