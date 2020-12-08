package com.hef.leetcode;

/**
 * @author lifei
 * @since 2020/9/13
 */
public class ReverseInt02 {

    public static void main(String[] args) {
        int x = -123;
        int y = Integer.MIN_VALUE -3;
        System.out.println(y);
        ReverseInt02 reverseInt02 = new ReverseInt02();
        int reverse = reverseInt02.reverse(x);
        System.out.println(reverse);
    }

    public int reverse(int x){
        int sign = x;
        if (sign<0){
            x = -x;
        }
        long result = 0;
        do {
            result = result*10 + x%10;
        }while ((x/=10)>0);

        if (sign<0){
            if((result = -result)<Integer.MIN_VALUE){
                return 0;
            }
            return (int)result;
        }else {
            if (result>Integer.MAX_VALUE){
                return 0;
            }
            return (int)result;
        }
    }
}
