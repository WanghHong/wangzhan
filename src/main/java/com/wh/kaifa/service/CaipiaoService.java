package com.wh.kaifa.service;

/**
 * Created by wanghong on 2020/7/11.
 */
public interface CaipiaoService {
    String getDoubleNum(Integer m, Integer num);

    String getDiffMN(Integer m, Integer openNum);

    String getPlanb(Integer m, Integer n, Integer openNum);

    String getPlan0X(Integer openNum, Integer m);

    String getPlanDoule(Integer openNum, Integer preOpenNum, Integer m);

    String getPlanFive(String openNum1, String openNum2, String openNum3, Integer m);

    String getPlanFiveNew(Integer openNum1, Integer openNum2, Integer openNum3, Integer m, Integer position);

    String getPanPosition(String openNum, Integer m);

    String getSizeRate(String numStr,  String openNum, Integer n);

    String getDoubleNew(String openNum, Integer m, Integer n);

    String  getDoubleRate(String openNum, Integer m, Integer n, Integer a);

    String getsizeRateNew(String openNun, Integer m);

}
