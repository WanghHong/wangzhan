package com.wh.kaifa.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by wanghong on 2020/6/7.
 */
public class MapSort  implements Comparator<Map.Entry> {
    @Override
    public int compare(Map.Entry o1, Map.Entry o2) {
        return ((String)o1.getKey()).compareTo((String)o2.getKey());
    }
}
