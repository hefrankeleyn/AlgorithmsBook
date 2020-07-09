package com.hef;

import com.hef.chapter3.item31.SequentialSearchST;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        sequentialSearchSTTest();
    }


    private static void sequentialSearchSTTest(){
        SequentialSearchST<String, String> st = new SequentialSearchST<>();
        st.put("aa","123");
        st.put("bb","111");
        System.out.println(st.size());
        st.delete("aa");
        System.out.println(st.size());
        System.out.println(st.get("bb"));
        Iterable<String> keys = st.keys();
        for (String key : keys) {
            System.out.println(key);
        }
    }
}
