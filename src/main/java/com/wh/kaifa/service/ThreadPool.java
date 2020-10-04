package com.wh.kaifa.service;

import com.wh.kaifa.DTO.CaipiaoDTO;
import com.wh.kaifa.mapper.CaipaiMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadPool implements Callable<Map<Integer, Map<Integer, Integer>>> {

    private Integer m;
    private Integer openNum;
    private Integer mark;
    private CaipaiMapper caipaiMapper;

    public ThreadPool(Integer mark, Integer m, Integer openNum, CaipaiMapper caipaiMapper) {
        this.m = m;
        this.openNum = openNum;
        this.mark = mark;
        this.caipaiMapper = caipaiMapper;
    }

    @Override
    public Map<Integer, Map<Integer, Integer>> call() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        int page = (mark - 1) * 5000 + 1;
        paramMap.put("page", page);
        paramMap.put("tableName", "T_CAIPIAO_INFO" + "_" + openNum);
        List<Integer> idList = caipaiMapper.queryDiffPages(paramMap);
        System.out.println("=======" + idList.size());
        List<Integer> newList = getVali(idList, m);
        System.out.println("=======new" + newList.size());
        long threadId = Thread.currentThread().getId();
        return getFenzi(threadId, newList, m);
    }


    private List<Integer> getVali(List<Integer> idList, Integer m) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < idList.size() - 1; i++) {
            if (idList.get(i + 1) - idList.get(i) > m + 1) {
                newList.add(idList.get(i));
            }
        }
        return newList;
    }

    private Map<Integer, Map<Integer, Integer>> getFenzi(long ThreadId, List<Integer> list, Integer m) throws Exception {
        System.out.println("list的长度为：" + list.size());
        Map<Integer, Integer> fenzi = new HashMap<>();
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

        Map<Integer, Map<Integer, Integer>> resultMap = new HashMap();
        resultMap.put(list.size(), fenzi);
        return resultMap;
    }
}
