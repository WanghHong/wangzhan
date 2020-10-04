package com.wh.kaifa;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        System.out.println(1 + "" + 1);
        String a = "0123456";
        System.out.println(String.valueOf(a.charAt(0)));
//

    }


    public static List<Integer> getIndexs(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    list.add(i);
                    list.add(j);
                    return list;
                }
            }
        }
        list.add(-1);
        return list;
    }
}
