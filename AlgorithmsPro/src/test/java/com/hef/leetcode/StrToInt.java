package com.hef.leetcode;

import org.junit.Test;

/**
 * @author lifei
 * @since 2020/9/17
 */
public class StrToInt {

    @Test
    public void test01(){
        String s = " 000 ";
        int i = myAtoi(s);
        System.out.println(i);

    }

    public int myAtoi(String str) {
        char[] sc = str.toCharArray();
        if(sc.length==0) return 0;
        int i=0,j=sc.length-1;
        boolean negtive = false;
        while(i<=j && (sc[i]==' ' || sc[i]=='0')) i++;
        if(i>j) return 0;
        if(sc[i]=='-') negtive=true;
        if(sc[i]=='+' || sc[i]=='-') {
            i++;
            while(i<=j && sc[i]=='0') i++;
        }
        if(i>j || !validChar(sc[i])) return 0;
        int e = i;
        while(e<j && validChar(sc[e+1])) e++;
        int fcl = e-i+1;
        if(negtive) fcl+=1;
        char[] fc = new char[fcl];
        int y=0;
        if(negtive) fc[y++] = '-';
        for(int x=i;x<=e;x++){
            fc[y++] = sc[x];
        }
        if(fc[0]=='-'){
            char[] minC = (Integer.MIN_VALUE+"").toCharArray();
            if(fc.length>minC.length) return Integer.MIN_VALUE;
            else if(fc.length<minC.length) return Integer.parseInt(new String(fc));
            else {
                int n=0;
                while(n<fc.length && fc[n]==minC[n]){
                    n++;
                }
                if(n>=fc.length || fc[n]>minC[n]){
                    return Integer.MIN_VALUE;
                }
                return Integer.parseInt(new String(fc));
            }
        }else{
            char[] maxC = (Integer.MAX_VALUE+"").toCharArray();
            if(fc.length>maxC.length) return Integer.MAX_VALUE;
            else if(fc.length<maxC.length) return Integer.parseInt(new String(fc));
            else {
                int n=0;
                while(n<fc.length && fc[n]==maxC[n]){
                    n++;
                }
                if(n>=fc.length || fc[n]>maxC[n]){
                    return Integer.MAX_VALUE;
                }
                return Integer.parseInt(new String(fc));
            }
        }

    }

    /**
     * 验证数字
     */
    private boolean validChar(char c){
        if(c>='0' && c<='9'){
            return true;
        }
        return false;
    }

    /**
     * 验证第一个字符
     */
    private boolean firstValidChar(char c){
        if(c=='-' || c=='+' || (c>='0' && c<='9')){
            return true;
        }
        return false;
    }
}
