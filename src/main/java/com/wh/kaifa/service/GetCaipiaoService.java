package com.wh.kaifa.service;


public interface GetCaipiaoService {

    String getCaipiaoInfo(Integer id, Integer openNum);

    String getCaipiaoInfoNew(Integer id, Integer openNum);

    String getCaipiaoNext(Integer id, Integer openNum);

    String getNM(Integer m, Integer n, Integer openNum, Integer k);

    String queryDiff(Integer m, Integer openNum);

    String queryDiffNew(Integer m, Integer openNum);
}
