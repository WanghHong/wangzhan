package com.wh.kaifa.service;

import com.wh.kaifa.DTO.CanlaDaDTO;
import com.wh.kaifa.mapper.CaipaiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghong on 2020/8/8.
 */
@Service
public class CanlanDaServiceImpl implements  CanlanDaService {

    @Autowired
    private CaipaiMapper caipaiMapper;
    @Override
    public String getCanladaPosition(String openNum, Integer m) {

        try {
            Map<Integer, Integer> fenzi = new HashMap<>();
            List<Integer> idList = caipaiMapper.queryCanladaPosition(openNum);
            System.out.println("idlist====" + idList.size());
            for (Integer id : idList) {
                List<Integer> list = new ArrayList<>();
                for (int i = 1; i <= m; i++) {
                    list.add(id + i);
                }
                List<CanlaDaDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfo1(list);
                String newopenNum = "";
                for (CanlaDaDTO caipiaoDTO : caipiaoDTOS) {
                    String openNumNext = caipiaoDTO.getOpenNum();
                    newopenNum += openNumNext;
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
            }
            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (fenzi.containsKey(i)) {
                    BigDecimal result = new BigDecimal(fenzi.get(i)).divide
                            (new BigDecimal(idList.size()), 4, BigDecimal.ROUND_HALF_UP);
                       resultMap.put(i, result.toString());
                }
            }
            System.out.println("分子" + fenzi);
            System.out.println("result=====" + resultMap);
            return  resultMap.toString();
        } catch (Exception e) {
            System.out.println("方法异常" + e);
        }
        return null;

    }

    @Override
    public String getCanladaDouble(String openNum, String preOpenNum, Integer m) {
        try {
            Map<Integer, Integer> fenzi = new HashMap<>();
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("openNum", openNum);
            paramMap.put("preOpenNum", preOpenNum);
            List<Integer> idList = caipaiMapper.queryCanladaDouble(paramMap);
            if (idList ==  null || idList.size() == 0) {
                return "no data";
            }
            System.out.println("idlist====" + idList.size());
            for (Integer id : idList) {
                List<Integer> list = new ArrayList<>();
                for (int i = 2; i <= m + 1; i++) {
                    list.add(id + i);
                }
                List<CanlaDaDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfo1(list);
                String newopenNum = "";
                for (CanlaDaDTO caipiaoDTO : caipiaoDTOS) {
                    String openNumNext = caipiaoDTO.getOpenNum();
                    newopenNum += openNumNext;
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
            }
            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (fenzi.containsKey(i)) {
                    BigDecimal result = new BigDecimal(fenzi.get(i)).divide
                            (new BigDecimal(idList.size()), 4, BigDecimal.ROUND_HALF_UP);
                    resultMap.put(i, result.toString());
                }
            }
            System.out.println("分子" + fenzi);
            System.out.println("result=====" + resultMap);
            return  resultMap.toString();
        } catch (Exception e) {
            System.out.println("方法异常" + e);
        }
        return null;
    }

    @Override
    public String getCanladaDoubleNew(String openNum, String preOpenNum, Integer m) {
        try {
            Map<Integer, Integer> fenzi = new HashMap<>();
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("openNum", openNum);
            paramMap.put("preOpenNum", preOpenNum);
            List<Integer> idList = caipaiMapper.queryCanladaDoubleNew(paramMap);
            if (idList ==  null || idList.size() == 0) {
                return "no data";
            }
            System.out.println("idlist====" + idList.size());
            for (Integer id : idList) {
                List<Integer> list = new ArrayList<>();
                for (int i = 2; i <= m + 1; i++) {
                    list.add(id + i);
                }
                List<CanlaDaDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfo1(list);
                String newopenNum = "";
                for (CanlaDaDTO caipiaoDTO : caipiaoDTOS) {
                    String openNumNext = caipiaoDTO.getOpenNum();
                    newopenNum += openNumNext;
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
            }
            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (fenzi.containsKey(i)) {
                    BigDecimal result = new BigDecimal(fenzi.get(i)).divide
                            (new BigDecimal(idList.size()), 4, BigDecimal.ROUND_HALF_UP);
                    resultMap.put(i, result.toString());
                }
            }
            System.out.println("分子" + fenzi);
            System.out.println("result=====" + resultMap);
            return  resultMap.toString();
        } catch (Exception e) {
            System.out.println("方法异常" + e);
        }
        return null;
    }
}
