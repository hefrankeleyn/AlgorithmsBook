package com.hef.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lifei
 * @since 2020/9/15
 */
public class ShellSortTest {

    @Test
    public void test01(){
        char[] c = "anagram".toCharArray();

        System.out.println(Arrays.toString(c));
        shellSortCharArray(c);
        System.out.println(Arrays.toString(c));

    }

    private void shellSortCharArray(char[] c){
        for(int step=c.length/2;step>0;step/=2){
            for(int i=0;i+step<c.length;i++){
                for(int j=i;j>=0;j-=step){
                    if(c[j]>c[j+step]){
                        char t = c[j];
                        c[j] = c[j+step];
                        c[j+step] = t;
                    }
                }
            }
        }
    }
}
