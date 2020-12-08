package com.hef.leetcode;


import org.junit.Test;

/**
 * @author lifei
 * @since 2020/9/16
 */
public class ValidPalindrome {

    @Test
    public void test01(){
        String s = ".,";
        System.out.println('0'<'9');
        boolean res = isPalindrome(s);
        System.out.println(res);
    }

    public boolean isPalindrome(String s) {
        char[] sc = s.toLowerCase().toCharArray();
        int i=0,j=sc.length-1;
        while(i<j){
            while(i<j && !isLowerCaseRange(sc[i])) i++;
            while(j>i && !isLowerCaseRange(sc[j])) j--;
            if(i<j && sc[i]!=sc[j]) {
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean isLowerCaseRange(char c){
        if(c>='a' && c<='z'){
            return true;
        }
        return false;
    }
}
