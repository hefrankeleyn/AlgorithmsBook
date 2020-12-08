package com.hef.leetcode;

import org.junit.Test;

/**
 * @author lifei
 * @since 2020/9/20
 */
public class CountSay {

    @Test
    public void countSayTest(){
        String s = countAndSay(4);
        System.out.println(s);
    }

    public String countAndSay(int n) {
        if(n<1 || n>30) return "";
        if(n==1) return "1";
        else{
            String lastCountStr = countAndSay(n-1);
            char[] cA = createCharArray(lastCountStr.toCharArray());
            char[] resC = new char[cA.length*2];
            int j=0;
            for(int i=0,cs=1;i<cA.length;i++){
                while(i<=cA.length-2 && cA[i]==cA[i+1]) {
                    cs++;
                    i++;
                }
                char[] csSC = (cs + "").toCharArray();
                for(int x=0;x<csSC.length;x++){
                    resC[j++] = csSC[x];
                }
                resC[j++] = cA[i];
                cs = 1;
            }
            char[] res = createCharArray(resC);
            return new String(res);
        }

    }

    private char[] createCharArray(char[] oneCharArray){
        int maxIndex=0;
        while(maxIndex<=oneCharArray.length-1 && oneCharArray[maxIndex]>='0' && oneCharArray[maxIndex]<='9'){
            maxIndex++;
        }
        char[] res = new char[maxIndex];
        for(int oneIndex=0;oneIndex<maxIndex;oneIndex++){
            res[oneIndex] = oneCharArray[oneIndex];
        }
        return res;
    }
}
