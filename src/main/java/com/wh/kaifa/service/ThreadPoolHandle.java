package com.wh.kaifa.service;

import com.wh.kaifa.DTO.CaipiaoDTO;
import com.wh.kaifa.mapper.CaipaiMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wanghong on 2020/7/11.
 */
public class ThreadPoolHandle implements Callable<ConcurrentHashMap<Integer, Integer>> {

    private Integer mark;
    private ConcurrentHashMap<Integer, Integer> fenziMap;
    private Integer openNum;
    private CaipaiMapper caipaiMapper;
    private Integer m;

    public ThreadPoolHandle(Integer mark, ConcurrentHashMap<Integer, Integer> fenzi,
                            Integer num, CaipaiMapper caipaiMapper, Integer m) {
        this.mark = mark;
        this.fenziMap = fenzi;
        this.openNum = num;
        this.caipaiMapper = caipaiMapper;
        this.m = m;
    }

    @Override
    public ConcurrentHashMap<Integer, Integer> call() {
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            int page = (mark - 1) * 1000 + 1;
            paramMap.put("page", page);
            paramMap.put("tableName", "T_CAIPIAO_INFO" + "_" + openNum);
            List<Integer> idList = caipaiMapper.queryPages(paramMap);
            long threadId = Thread.currentThread().getId();
            try {
                getFenzi(threadId, idList, m, fenziMap);
            } catch (Exception e) {
                System.out.println("查询出异常" + e);
            }
            mark++;
        }
        return fenziMap;
    }

    //计算分子
    private void getFenzi(long ThreadId, List<Integer> list, Integer m,
                          ConcurrentHashMap<Integer, Integer> fenzi) throws Exception {
        System.out.println("list的长度为：" + list.size());
        for (Integer id : list) {
            List<Integer> idList = new ArrayList<>();
            for (int i = 1; i <= m; i++) {
                idList.add(id + i);
            }
            List<CaipiaoDTO> caipiaosTemp = caipaiMapper.queryCaipiaoInfos(idList);
            String newopenNum = "";
            for (CaipiaoDTO caipiaoDTO : caipiaosTemp) {
                Integer openNumNext = caipiaoDTO.getOpenNum();
                String openNumStr = "";
                if (openNumNext < 10000) {
                    openNumStr = "0" + openNumNext;
                } else {
                    openNumStr = String.valueOf(openNumNext);
                }
                newopenNum += openNumStr;
            }
            for (int i = 0; i <= 9; i++) {
                String num = String.valueOf(i);
                if (newopenNum.contains(num)) {
                    if (fenzi.containsKey(i)) {
                        int value = fenzi.get(i);
                        fenzi.put(i, value + 1);
                    } else {
                        fenzi.put(i, 1);
                    }
                }
            }
            System.out.println("线程:id" + ThreadId + "===" + "分子" + fenzi);
        }
    }
}
