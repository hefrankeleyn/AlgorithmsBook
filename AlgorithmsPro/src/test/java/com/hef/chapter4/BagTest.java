package com.hef.chapter4;

import org.junit.Test;

/**
 * @author lifei
 * @since 2020/7/4
 */
public class BagTest {

    @Test
    public void bagTest(){
        Bag<String> names = new Bag<>();
        names.add("aaa");
        names.add("bbb");
        names.add("ccc");

        for (String name : names) {
            System.out.println(name);
        }
    }
}
