package com.wh.kaifa.service;

import com.alibaba.fastjson.JSON;
import com.wh.kaifa.DTO.CaipiaoDTO;
import com.wh.kaifa.mapper.CaipaiMapper;
import com.wh.kaifa.utils.MapSort;
import org.apache.tomcat.util.modeler.FeatureInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service
public class GetCaipiaoServiceImpl implements GetCaipiaoService {


    @Autowired
    private CaipaiMapper caipaiMapper;

    @Override
    public String getCaipiaoInfo(Integer paramId, Integer openNum) {
        long statTime = System.currentTimeMillis();
//        Integer openNum = openNum;
        List<Integer> ids = caipaiMapper.queryCaipiaoInfo(openNum);
        System.out.println("====" + ids.size());
        List<Integer> list;
        Map<Integer, Integer> numCount = new HashMap<>();
        for (Integer id : ids) {
            list = new ArrayList<Integer>();
            for (int i = 1; i <= paramId; i++) {
                list.add(id + i);
            }
            List<CaipiaoDTO> caipiaosTemp = caipaiMapper.queryCaipiaoInfos(list);
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
                    if (numCount.containsKey(i)) {
                        int value = numCount.get(i);
                        numCount.put(i, value + 1);

                    } else {
                        numCount.put(i, 1);
                    }
                }
            }


        }
        Map<Integer, String> resultMap = new HashMap<>();
        System.out.println(numCount);
        for (int i = 0; i <= 9; i++) {
            if (numCount.containsKey(i)) {
                int value = numCount.get(i);
                BigDecimal bd = new BigDecimal(value);
                BigDecimal result = bd.divide(new BigDecimal(ids.size()), 4, BigDecimal.ROUND_HALF_UP);
                System.out.println(i + "后" + paramId + "期概率" + result.toString());
                if (paramId == 1) {
                    if (result.compareTo(new BigDecimal(0.44)) >= 0) {
                        System.out.println(i);
                        resultMap.put(i, result.toString());
                    }
                }

                if (paramId == 2) {
                    if (result.compareTo(new BigDecimal(0.68)) >= 0) {
                        System.out.println(i);
                        resultMap.put(i, result.toString());
                    }
                }
                if (paramId >= 3 && paramId < 5) {
                    if (result.compareTo(new BigDecimal(0.80)) >= 0) {
                        System.out.println(i);
                        resultMap.put(i, result.toString());
                    }
                }

                if (paramId >= 5 && paramId < 9) {
                    if (result.compareTo(new BigDecimal(0.93)) >= 0) {
                        System.out.println(i);
                        resultMap.put(i, result.toString());
                    }
                }

                if (paramId >= 9 && paramId < 12) {
                    if (result.compareTo(new BigDecimal(0.997)) >= 0) {
                        System.out.println(i);
                        resultMap.put(i, result.toString());
                    }

                }
                if (paramId >= 12) {
                    if (result.compareTo(new BigDecimal(1)) >= 0) {
                        System.out.println(i);
                        resultMap.put(i, result.toString());
                    }

                }
//                if (result.compareTo(new BigDecimal(1)) >= 0) {
//                    System.out.println(i);
//                    resultMap.put(i, result.toString());
//                }
            }
            continue;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time is :" + (endTime - statTime) / 1000 + "s");
        return JSON.toJSONString(resultMap);
    }

    @Override
    public String getCaipiaoInfoNew(Integer paramId, Integer openNum) {
        long statTime = System.currentTimeMillis();
        List<Integer> ids = caipaiMapper.queryCaipiaoInfo(openNum);
        System.out.println("====" + ids.size());
        List<Integer> numList = new ArrayList<>();
        for (Integer id : ids) {
            numList.add(id - 1);
        }
        Integer sumCount = 0;
        List<CaipiaoDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfos(numList);
        Map<String, List<Integer>> countMap = new HashMap<>();
        for (CaipiaoDTO caipiaoDTO : caipiaoDTOS) {
            Integer openNumNext = caipiaoDTO.getOpenNum();
            List<String> lianggeNew = isLianggeNew(openNumNext);
            for (String s : lianggeNew) {
                if (countMap.containsKey(s)) {
                    List<Integer> value = countMap.get(s);
                    value.add(caipiaoDTO.getId());
                    countMap.put(s, value);
                } else {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(caipiaoDTO.getId());
                    countMap.put(s, list1);
                }
            }
        }
        List<Integer> list;
        Map<Integer, Integer> numCount = null;
        Map<String, Object> resultMap = new TreeMap<>();
        Map<Integer, String> mapTmp;
        for (Map.Entry<String, List<Integer>> entries : countMap.entrySet()) {
            numCount = new HashMap<>();
            List<Integer> paramLIst = entries.getValue();
            for (Integer id : paramLIst) {
                list = new ArrayList<Integer>();
                for (int i = 2; i <= paramId + 1; i++) {
                    list.add(id + i);
                }
                List<CaipiaoDTO> caipiaosTemp = caipaiMapper.queryCaipiaoInfos(list);
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
                        if (numCount.containsKey(i)) {
                            int value = numCount.get(i);
                            numCount.put(i, value + 1);
                        } else {
                            numCount.put(i, 1);
                        }
                    }
                }
            }

            mapTmp = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (numCount.containsKey(i)) {
                    int value = numCount.get(i);
                    BigDecimal bd = new BigDecimal(value);
                    BigDecimal result = bd.divide(new BigDecimal(entries.getValue().size()), 4, BigDecimal.ROUND_HALF_UP);
                    if (paramId >= 0 && paramId < 3) {
                        if (result.compareTo(new BigDecimal(0.50)) >= 0) {
                            mapTmp.put(i, result.toString());
                        }
                    }
                    if (paramId >= 3 && paramId < 5) {
                        if (result.compareTo(new BigDecimal(0.80)) >= 0) {
                            mapTmp.put(i, result.toString());
                        }
                    }
                    if (paramId >= 5) {
                        if (result.compareTo(new BigDecimal(0.93)) >= 0) {
                            mapTmp.put(i, result.toString());
                        }
                    }
                    System.out.println(i + "后" + paramId + "期概率" + result.toString());
                }

                resultMap.put(entries.getKey(), mapTmp);

            }
        }
        Map<String, Object> map = sortMapByKey(resultMap);
        long endTime = System.currentTimeMillis();
        System.out.println("time is :" + (endTime - statTime) / 1000 + "s");
        return JSON.toJSONString(map);
    }

    @Override
    public String getCaipiaoNext(Integer id, Integer openNum) {
        List<Integer> ids = caipaiMapper.queryCaipiaoInfo(openNum);
        List<Integer> numList = new ArrayList<>();
        for (Integer idd : ids) {
            numList.add(idd + 1);
        }
        List<CaipiaoDTO> caipiaosTemp = caipaiMapper.queryCaipiaoInfos(numList);
        Map<Integer, Integer> fenmu = new HashMap<>();
        Map<Integer, Integer> fenzi = new HashMap<>();
        for (CaipiaoDTO caipiaoDTO : caipiaosTemp) {
            Integer openNumNext = caipiaoDTO.getOpenNum();
            String numStr = getFiveNumber(openNumNext);
            Map<Integer, Integer> resultZ = getDetailsZ(numStr);
            Map<Integer, Integer> resultM = getDetailsM(numStr);
            for (int i = 0; i <= 9; i++) {
                if (fenmu.containsKey(i)) {
                    Integer value = fenmu.get(i) + resultM.get(i);
                    fenmu.put(i, value);
                } else {
                    fenmu.put(i, resultM.get(i));
                }

                if (fenzi.containsKey(i)) {
                    Integer value = fenzi.get(i) + resultZ.get(i);
                    fenzi.put(i, value);
                } else {
                    fenzi.put(i, resultZ.get(i));
                }
            }
        }
        System.out.println("分母" + fenmu);
        System.out.println("分子" + fenzi);
        Map<Integer, String> resultMap = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            int valueZ = fenzi.get(i);
            int valueM = fenmu.get(i);
            BigDecimal result = new BigDecimal(valueZ).divide(new BigDecimal(valueM), 4, BigDecimal.ROUND_HALF_UP);
            if (result.compareTo(new BigDecimal(1.1)) >= 0) {
                resultMap.put(i, result.toString());
            }
        }
        return resultMap.toString();
    }

    @Override
    public String getNM(Integer m, Integer n, Integer openNum, Integer k) {
        try {
            List<Integer> ids = caipaiMapper.queryCaipiaoInfo(openNum);
            Map<Integer, Object> resultMap = new HashMap<>();
            Map<Integer, Integer> fenzi = new HashMap<>();
            Map<Integer, Integer> fenmu = new HashMap<>();
            List<Integer> idList = null;
            for (Integer id : ids) {
                idList = new ArrayList();
                Integer openNumN = caipaiMapper.queryOpenNumById(id + n);
                Integer openNumM = caipaiMapper.queryOpenNumById(id + n + m);
                Integer openNumMpre = caipaiMapper.queryOpenNumById(id + n + m + 1);
                Integer openNum2M = -1;
                if (m == n) {
                    openNum2M = caipaiMapper.queryOpenNumById(id + n + 2 * m);
                }
                List<Integer> list = veryNum(openNumN, openNumM, openNumMpre, openNum2M);
                if (list.size() == 0) {
                    continue;
                } else {
                    for (int i = 1; i <= k; i++) {
                        idList.add(id + i);
                    }
                    List<CaipiaoDTO> caipiaosTemp = caipaiMapper.queryCaipiaoInfos(idList);
                    if (caipiaosTemp == null || caipiaosTemp.size() == 0) {
                        continue;
                    }
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
                    for (Integer num : list) {
                        if (fenmu.containsKey(num)) {
                            Integer value = fenmu.get(num);
                            fenmu.put(num, value + 1);
                        } else {
                            fenmu.put(num, 1);
                        }
                        if (newopenNum.contains(String.valueOf(num))) {
                            if (fenzi.containsKey(num)) {
                                Integer value = fenzi.get(num);
                                fenzi.put(num, value + 1);
                            } else {
                                fenzi.put(num, 1);
                            }
                        }
                    }
                }
            }
            for (int i = 0; i <= 9; i++) {
                if (!fenmu.containsKey(i)) {
                    continue;
                }
                int valueM = fenmu.get(i);
                if (!fenzi.containsKey(i)) {
                    fenzi.put(i, 0);
                }
                int valueZ = fenzi.get(i);
                BigDecimal result = new BigDecimal(valueZ).divide(new BigDecimal(valueM), 4, BigDecimal.ROUND_HALF_UP);
//                if(result.compareTo(new BigDecimal(1.1)) >= 0) {
                resultMap.put(i, result.toString());
//                }
            }
            return resultMap.toString();

        } catch (Exception e) {
            System.out.println("e" + e);
            return "系统异常";
        }
    }

    @Override
    public String queryDiff(Integer m, Integer openNum) {
        Map<Integer, Integer> param = new HashMap<>();
        Map<Integer, Integer> fenzi = new HashMap<>();
        Map<Integer, Integer> fenmu = new HashMap<>();
        Map<Integer, Object> resultMap = new HashMap<>();
        String tableName = "";
        Integer id = 0;
        try {
            if (openNum == 0) {
                id = 104663;
                param.put(104663, 133);
                tableName = "T_CAIPIAO_INFO_0";
            }
            if (openNum == 1) {
                id = 104685;
                param.put(104685, 11279);
                tableName = "T_CAIPIAO_INFO_1";
            }
            if (openNum == 2) {
                id = 104676;
                param.put(104676, 22466);
                tableName = "T_CAIPIAO_INFO_2";
            }
            if (openNum == 3) {
                id = 104658;
                param.put(104658, 33588);
                tableName = "T_CAIPIAO_INFO_3";
            }
            if (openNum == 4) {
                id = 104674;
                param.put(104674, 12449);
                tableName = "T_CAIPIAO_INFO_4";
            }
            if (openNum == 5) {
                id = 104660;
                param.put(104660, 2557);
                tableName = "T_CAIPIAO_INFO_5";
            }
            if (openNum == 6) {
                id = 104654;
                param.put(104654, 46679);
                tableName = "T_CAIPIAO_INFO_6";
            }
            if (openNum == 7) {
                id = 104662;
                param.put(104662, 12477);
                tableName = "T_CAIPIAO_INFO_7";
            }
            if (openNum == 8) {
                id = 104658;
                param.put(104658, 33588);
                tableName = "T_CAIPIAO_INFO_8";
            }
            if (openNum == 9) {
                id = 104656;
                param.put(104656, 7999);
                tableName = "T_CAIPIAO_INFO_9";
            }
            while (true) {
                Integer nextId = getNum(m, id, fenmu, fenzi, tableName);
                if (nextId == id) {
                    break;
                }
                id = nextId;
            }
        Integer fenmuValue = fenmu.get(0);
        for (int k = 0; k <= 9; k++) {
            Integer fenziValue = fenzi.get(k);
            BigDecimal result = new BigDecimal(fenziValue).divide(new BigDecimal(fenmuValue), 4, BigDecimal.ROUND_HALF_UP);
            resultMap.put(k, result.toString());
        }
    } catch(Exception e){
        System.out.println("e" + e);
    }
        return resultMap.toString();
}

    @Override
    public String queryDiffNew(Integer m, Integer openNum) {




        return null;
    }

    private Integer getNum(Integer m, Integer minId, Map<Integer, Integer> fenmu,
                           Map<Integer, Integer> fenzi, String tableName) {
        Map<String, Object> param = new HashMap<>();
        param.put("m", m + minId);
        param.put("minId", minId);
        param.put("tableName", tableName);
        Integer nextId = 0;
        List<Integer> list = caipaiMapper.between0(param);
        if (list != null && list.size() == 1) {
            if (fenmu.containsKey("0")) {
                Integer value = fenmu.get(0);
                fenmu.put(0, value + 1);
            } else {
                fenmu.put(0, 1);
            }
            List<Integer> idList = new ArrayList<>();
            for (int i = 1; i <= m; i++) {
                idList.add(minId + i);
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


            nextId = caipaiMapper.queryId(param);
        } else if(list != null && list.size() >= 2){
            Collections.sort(list);
            nextId = list.get(list.size() - 1);
        }
        return nextId;
    }

    private List<Integer> veryNum(Integer openNumN, Integer openNumM,
                                  Integer openNumMpre, Integer openNum2M) {
        List<Integer> list = new ArrayList<>();
        String openNumMStr = getFiveNumber(openNumM);
        String openNumNStr = getFiveNumber(openNumN);
        String openNumMpreStr = getFiveNumber(openNumMpre);
        String openNum2MStr = "";
        if (openNum2M != -1) {
            openNum2MStr = getFiveNumber(openNum2M);
        }
        for (int i = 0; i <= 9; i++) {
            String str = String.valueOf(i);
            if (openNumMStr.contains(str) &&
                    openNumNStr.contains(str) &&
                    !openNumMpreStr.contains(str) &&
                    !openNum2MStr.contains(str)) {
                list.add(i);
            }
        }
        return list;
    }


    private Map<Integer, Integer> getDetailsZ(String numStr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < numStr.length(); j++) {
                if (String.valueOf(i).equals(String.valueOf(numStr.charAt(j)))) {
                    if (map.containsKey(i)) {
                        int value = map.get(i);
                        map.put(i, value + 1);
                    } else {
                        map.put(i, 1);
                    }
                }
            }
        }
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            if (map.containsKey(i)) {
                Integer value = map.get(i);
                result.put(i, (2 * value) - 1);
            } else {
                result.put(i, 0);
            }
        }
        return result;
    }

    private Map<Integer, Integer> getDetailsM(String numStr) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            if (numStr.contains(String.valueOf(i))) {
                result.put(i, 0);
            } else {
                result.put(i, 1);
            }
        }
        return result;
    }

    private String getFiveNumber(Integer number) {
        String numStr = String.valueOf(number);
        for (int k = 1; k <= 5; k++) {
            if (numStr.length() < 5) {
                numStr = "0" + numStr;
            }
            break;
        }
        return numStr;
    }

    //只判断5位数
    public boolean isLiangge(Integer number) {
        String numStr = String.valueOf(number);
        for (int k = 1; k <= 5; k++) {
            if (numStr.length() < 5) {
                numStr = "0" + numStr;
            }
            break;
        }
        for (int i = 0; i < 10; i++) {
            boolean contains = numStr.contains(i + "" + i);
            boolean contains1 = false;
            if (contains) {
                for (int j = 0; j < 10; j++) {
                    contains1 = numStr.contains(j + "" + j + "" + j);
                    if (contains1) {
                        return false;
                    }
                }
            }
            if (contains == true && contains1 == false) {
                return true;
            }
        }
        return false;
    }


    public List<String> isLianggeNew(Integer number) {
        List<String> list = new ArrayList<>();
        String numStr = String.valueOf(number);
        for (int k = 1; k <= 5; k++) {
            if (numStr.length() < 5) {
                numStr = "0" + numStr;
            }
            continue;
        }
        for (int i = 0; i < 10; i++) {
            boolean contains = numStr.contains(i + "" + i);
            boolean contains1 = numStr.contains(i + "" + i + "" + i);
            if (contains == true && contains1 == false) {
                list.add(i + "" + i);
            }
        }
        return list;
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);//升序排序
            }
        });
        sortMap.putAll(map);
        return sortMap;
    }

}
