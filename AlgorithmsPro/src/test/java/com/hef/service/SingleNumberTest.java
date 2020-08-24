package com.hef.service;

import org.junit.Test;

/**
 * @author lifei
 * @since 2020/8/17
 */
public class SingleNumberTest {

    @Test
    public void test01(){
//        int[] nums = {-1,-1,-2};
//        int[] nums = {4,1,2,1,2};
        int[] nums = {2,2,1};
        int num = singleNumber(nums);
        System.out.println("num: " + num);

    }

    public int singleNumber(int[] nums) {
        int temp = 0;
        for(int i=0;i<nums.length;i++){
            boolean flag = true;
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    nums[i] = j;
                    flag = false;
                    break;
                }
            }
            if(flag){
                for(int t=0; t<i;t++){
                    if(nums[t] == i){
                        nums[i] = t;
                        flag = false;
                        break;
                    }
                }
            }
            if(flag){
                return nums[i];
            }
        }
        return temp;
    }
}
