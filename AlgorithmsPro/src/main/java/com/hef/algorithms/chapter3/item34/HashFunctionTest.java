package com.hef.algorithms.chapter3.item34;

/**
 * @Date 2020/2/5
 * @Author lifei
 */
public class HashFunctionTest {

    private static final int R = 31;

    public static void main(String[] args) {
        int s = stringHashTestFive("S");
        System.out.println(s);

        System.out.println(("S".hashCode()&0x7fffffff)%5);
    }



    private static int stringHashTestFive(String str){
        return stringHashTest(str, 5);
    }

    private static int stringHashTest(String str, int M){
        int hash = 0;
        for (int i=0;i<str.length();i++){
            hash = (R+str.charAt(i))%M;
        }
        return hash;
    }


}
