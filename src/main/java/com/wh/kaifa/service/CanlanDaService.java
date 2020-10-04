package com.wh.kaifa.service;

/**
 * Created by wanghong on 2020/8/8.
 */
public interface CanlanDaService {
    String getCanladaPosition(String openNum, Integer m);

    String getCanladaDouble(String openNum, String preOpenNum, Integer m);

    String getCanladaDoubleNew(String openNum, String preOpenNum, Integer m);
}
