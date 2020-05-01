package com.hef.chapter3.item31.exercises.exe01;

import com.hef.chapter3.item31.SequentialSearchST;
import com.hef.chapter3.item31.exercises.ST;
import com.hef.commons.StdIn;

/**
 * @Date 2020/5/1
 * @Author lifei
 */
public class GPA {

    public static void main(String[] args) {
        ST<String, Double> st = new SequentialSearchST<>();
        st.put("A+",4.33);
        st.put("A", 4.00);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);

        int n = 0;
        double total = 0.0;
        while (!StdIn.isEmpty()){
            String grade = StdIn.readString();
            System.out.println(grade);
            Double value = st.get(grade);
            total += value.doubleValue();
            n++;
        }
        double gpa = total/n;
        System.out.println("GPA = " + gpa);
    }
}



