package com.wh.kaifa.service;

import com.wh.kaifa.DTO.CaipiaoDTO;
import com.wh.kaifa.mapper.CaipaiMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wanghong on 2020/7/11.
 */
@Service
public class CaipiaoServiceImpl implements CaipiaoService {


    @Autowired
    private CaipaiMapper caipaiMapper;

    @Override
    public String getDoubleNum(Integer m, Integer num) {
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始处理数据");
            List<Integer> listCount = new ArrayList<>();
            listCount.add(57760);
            listCount.add(57712);
            listCount.add(57748);
            listCount.add(57326);
            listCount.add(57623);
            listCount.add(57343);
            listCount.add(56927);
            listCount.add(56975);
            listCount.add(56888);
            listCount.add(57696);
            Integer mark = 0;
            List<ConcurrentHashMap<Integer, Integer>> list = new ArrayList<ConcurrentHashMap<Integer, Integer>>();
            ExecutorService executor = Executors.newCachedThreadPool();
            ConcurrentHashMap<Integer, Integer> fenziSum = null;
            List<Future<ConcurrentHashMap<Integer, Integer>>> fList = new ArrayList<>();
            ThreadPoolHandle threadPoolHandle = null;
            for (int i = 0; i < 12; i++) {
                mark = 5 * i + 1;
                fenziSum = new ConcurrentHashMap<>();
                threadPoolHandle = new ThreadPoolHandle(mark, fenziSum, num, caipaiMapper, m);
                fList.add(executor.submit(threadPoolHandle));
            }
            for (Future<ConcurrentHashMap<Integer, Integer>> future : fList) {
                list.add(future.get());
            }
            Map<Integer, Integer> fenzi = new HashMap<>();
            for (ConcurrentHashMap map : list) {
                for (int i = 0; i <= 9; i++) {
                    Integer value = (Integer) map.get(i);
                    if (fenzi.containsKey(i)) {
                        Integer value1 = fenzi.get(i);
                        fenzi.put(i, value1 + value);
                    } else {
                        fenzi.put(i, value);
                    }
                }
            }
//            for (int k = 1; k <= 12; k++) {
//                fenzi = new ConcurrentHashMap<>();
//                mark = 5 * (k - 1) + 1;
//                FutureTask<ConcurrentHashMap<Integer, Integer>> futureTask =
//                        new FutureTask<>(threadPoolHandle);
//                Thread thread = new Thread(futureTask);
//                thread.start();
//                list.add(futureTask.get());
//            }

            System.out.println("list的结果为" + list);
            System.out.println("fenzi==" + fenzi);

            System.out.println("共消耗" + (System.currentTimeMillis() - startTime) / 1000 + "s");
            Map<Integer, String> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                Integer value = fenzi.get(i);
                BigDecimal result = new BigDecimal(value).divide(new BigDecimal(listCount.get(num)), 4, BigDecimal.ROUND_HALF_UP);
                resultMap.put(i, result.toString());
                System.out.println("第 " + i + "概率" + result);
            }

            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("异常" + e);
        }
        return null;
    }

    @Override
    public String getDiffMN(Integer m, Integer openNum) {
        try {
            long startTime = System.currentTimeMillis();
            ExecutorService executor = Executors.newCachedThreadPool();
            List<Future<Map<Integer, Map<Integer, Integer>>>> fList = new ArrayList<>();
            ThreadPool t = null;
            for (int i = 1; i <= 12; i++) {
                t = new ThreadPool(i, m, openNum, caipaiMapper);
                fList.add(executor.submit(t));
            }
//            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            Integer fenMu = 0;
            List<Map<Integer, Integer>> fenZiList = new ArrayList<>();
            for (Future<Map<Integer, Map<Integer, Integer>>> future : fList) {
                Map<Integer, Map<Integer, Integer>> map = future.get();
                Set<Integer> keySet = map.keySet();
                for (Integer set : keySet) {
                    fenMu += set;
                    fenZiList.add(map.get(set));
                }
            }
            System.out.println("分母=====" + fenMu);
            Map<Integer, Integer> fenzi = new HashMap<>();
            for (Map<Integer, Integer> map1 : fenZiList) {
                for (int i = 0; i <= 9; i++) {
                    Integer value = (Integer) map1.get(i);
                    if (fenzi.containsKey(i)) {
                        Integer value1 = fenzi.get(i);
                        fenzi.put(i, value1 + value);
                    } else {
                        fenzi.put(i, value);
                    }
                }
            }
            System.out.println("分子=====" + fenzi);
            Map<Integer, String> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                Integer value = fenzi.get(i);
                BigDecimal result = new BigDecimal(value).divide(new BigDecimal(fenMu), 4, BigDecimal.ROUND_HALF_UP);
                resultMap.put(i, result.toString());
                System.out.println("第 " + i + "概率" + result);
            }
            System.out.println("共消耗" + (System.currentTimeMillis() - startTime) / 1000 + "s");
            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("方法异常" + e);
        }
        return null;
    }

    @Override
    public String getPlanb(Integer m, Integer n, Integer openNum) {
        try {
            long startTime = System.currentTimeMillis();
            ExecutorService executor = Executors.newCachedThreadPool();
            List<Future<Map<Integer, Map<Integer, Integer>>>> fList = new ArrayList<>();
            ThreadPoolNew t = null;
            for (int i = 1; i <= 12; i++) {
                t = new ThreadPoolNew(i, m, n, openNum, caipaiMapper);
                fList.add(executor.submit(t));
            }
//            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            Integer fenMu = 0;
            List<Map<Integer, Integer>> fenZiList = new ArrayList<>();
            for (Future<Map<Integer, Map<Integer, Integer>>> future : fList) {
                Map<Integer, Map<Integer, Integer>> map = future.get();
                Set<Integer> keySet = map.keySet();
                for (Integer set : keySet) {
                    fenMu += set;
                    fenZiList.add(map.get(set));
                }
            }
            System.out.println("分母=====" + fenMu);
            Map<Integer, Integer> fenzi = new HashMap<>();
            for (Map<Integer, Integer> map1 : fenZiList) {
                for (int i = 0; i <= 9; i++) {
                    Integer value = (Integer) map1.get(i);
                    if (fenzi.containsKey(i)) {
                        Integer value1 = fenzi.get(i);
                        fenzi.put(i, value1 + value);
                    } else {
                        fenzi.put(i, value);
                    }
                }
            }
            System.out.println("分子=====" + fenzi);
            Map<Integer, String> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                Integer value = fenzi.get(i);
                BigDecimal result = new BigDecimal(value).divide(new BigDecimal(fenMu), 4, BigDecimal.ROUND_HALF_UP);
                resultMap.put(i, result.toString());
                System.out.println("第 " + i + "概率" + result);
            }
            System.out.println("共消耗" + (System.currentTimeMillis() - startTime) / 1000 + "s");
            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("1111" + e);
        }
        return null;
    }

    @Override
    public String getPlan0X(Integer openNum, Integer m) {
        try {
            long startTime = System.currentTimeMillis();
            ExecutorService executor = Executors.newCachedThreadPool();
            List<Future<Map<Integer, Map<Integer, Integer>>>> fList = new ArrayList<>();
            ThreadPoolPlanOX t = null;
            for (int i = 1; i <= 11; i++) {
                t = new ThreadPoolPlanOX(i, m, openNum, caipaiMapper);
                fList.add(executor.submit(t));
            }
//            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            Integer fenMu = 0;
            List<Map<Integer, Integer>> fenZiList = new ArrayList<>();
            for (Future<Map<Integer, Map<Integer, Integer>>> future : fList) {
                Map<Integer, Map<Integer, Integer>> map = future.get();
                if (map == null) {
                    continue;
                }
                Set<Integer> keySet = map.keySet();
                for (Integer set : keySet) {
                    fenMu += set;
                    fenZiList.add(map.get(set));
                }
            }
            System.out.println("分母=====" + fenMu);
            Map<Integer, Integer> fenzi = new HashMap<>();
            for (Map<Integer, Integer> map1 : fenZiList) {
                for (int i = 0; i <= 9; i++) {
                    Integer value = (Integer) map1.get(i);
                    if (fenzi.containsKey(i)) {
                        Integer value1 = fenzi.get(i);
                        fenzi.put(i, value1 + value);
                    } else {
                        fenzi.put(i, value);
                    }
                }
            }
            System.out.println("分子=====" + fenzi);
            Map<Integer, String> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                Integer value = fenzi.get(i);
                BigDecimal result = new BigDecimal(value).divide(new BigDecimal(fenMu), 4, BigDecimal.ROUND_HALF_UP);
                resultMap.put(i, result.toString());
                System.out.println("第 " + i + "概率" + result);
            }
            System.out.println("共消耗" + (System.currentTimeMillis() - startTime) / 1000 + "s");
            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("1111" + e);
        }
        return null;
    }

    @Override
    public String getPlanDoule(Integer openNum, Integer preOpenNum, Integer m) {
        try {
            List<Integer> userIds = new ArrayList<>();
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("openNum", openNum);
            paramMap.put("preOpenNUm", preOpenNum);
            List<CaipiaoDTO> caipiaoDTOS = caipaiMapper.queryDouble(paramMap);
            System.out.println("listId:" + caipiaoDTOS.size());
            Map<Integer, Integer> fenzi = new HashMap<>();
            for (CaipiaoDTO caipiaoDTO1 : caipiaoDTOS) {
                List<Integer> postList = new ArrayList<>();
                for (int i = 2; i <= m + 1; i++) {
                    postList.add(caipiaoDTO1.getId() + i);
                }
                List<CaipiaoDTO> caipiaosTemp = caipaiMapper.queryCaipiaoInfos(postList);
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
            }
            System.out.println("分子" + fenzi);
            Map<Integer, String> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (fenzi.containsKey(i)) {
                    BigDecimal result = new BigDecimal(fenzi.get(i)).divide
                            (new BigDecimal(caipiaoDTOS.size()), 4, BigDecimal.ROUND_HALF_UP);
                    resultMap.put(i, result.toString());
                }
            }
            System.out.println("结果为： " + resultMap);
            return resultMap.toString();

        } catch (
                Exception e)

        {
            System.out.println("异常" + e);
            return null;
        }
    }

    @Override
    public String getPlanFive(String openNum1, String openNum2, String openNum3, Integer m) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            Map<Integer, Integer> fenzi;
            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 4; i++) {
                fenzi = new HashMap<>();
                Integer open1 = Integer.valueOf(String.valueOf(openNum1.charAt(i)));
                Integer open2 = Integer.valueOf(String.valueOf(openNum2.charAt(i)));
                Integer open3 = Integer.valueOf(String.valueOf(openNum3.charAt(i)));
                paramMap.put("open1", open1);
                paramMap.put("open2", open2);
                paramMap.put("open3", open3);
                List<Integer> idLIst = caipaiMapper.queryFive(paramMap);
                System.out.println("caipiaoDTOLIst长度为： " + idLIst.size());
                getFenzi(idLIst, m, fenzi, 1);
                Map<Integer, String> resultM = new HashMap<>();
                for (int j = 0; j <= 9; j++) {
                    if (fenzi.containsKey(j)) {
                        BigDecimal result = new BigDecimal(fenzi.get(j)).divide
                                (new BigDecimal(idLIst.size()), 4, BigDecimal.ROUND_HALF_UP);
                        resultM.put(j, result.toString());
                    }
                }
                System.out.println("结果为： " + resultM);
                resultMap.put(i + 1, resultM);
            }
            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("异常" + e);
            return null;
        }
    }

    @Override
    public String getPlanFiveNew(Integer openNum1, Integer openNum2, Integer openNum3, Integer m, Integer position) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("open1", openNum1);
            paramMap.put("open2", openNum2);
            paramMap.put("open3", openNum3);
            List<Integer> idList = null;
            if (position == 1) {
                idList = caipaiMapper.queryFive(paramMap);
            }
            if (position == 2) {
                idList = caipaiMapper.queryFive1(paramMap);
            }
            if (position == 3) {
                idList = caipaiMapper.queryFive2(paramMap);
            }
            if (position == 4) {
                idList = caipaiMapper.queryFive3(paramMap);
            }
            if (position == 5) {
                idList = caipaiMapper.queryFive4(paramMap);
            }
            System.out.println("共发生了" + idList.size());
            Map<Integer, Integer> fenzi = new HashMap<>();
            getFenzi(idList, m, fenzi, position);
            System.out.println("分子" + fenzi);
            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (fenzi.containsKey(i)) {
                    BigDecimal result = new BigDecimal(fenzi.get(i)).divide
                            (new BigDecimal(idList.size()), 4, BigDecimal.ROUND_HALF_UP);
                    if (result.compareTo(new BigDecimal(0.1)) >= 0) {
                        resultMap.put(i, result.toString());
                    }
                }
            }
            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("系统异常" + e);
        }

        return null;
    }

    @Override
    public String getPanPosition(String openNum, Integer m) {
        try {
            Map<Integer, Integer> fenzi = new HashMap<>();
            List<Integer> idList = caipaiMapper.queryPosition(openNum);
            if(idList.size() <= 5) {
                return "no data";
            }
            System.out.println("idList：" + idList.size());
            for (Integer id : idList) {
                List<Integer> list = new ArrayList<>();
                for (int i = 1; i <= m; i++) {
                    list.add(id + i);
                }
                List<CaipiaoDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfos(list);
//                System.out.println("caipiaoDTOS的长度：" + caipiaoDTOS.size());
                String newopenNum = "";
                for (CaipiaoDTO caipiaoDTO : caipiaoDTOS) {
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
            }
            System.out.println("分子==" + fenzi);
            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (fenzi.containsKey(i)) {
                    BigDecimal result = new BigDecimal(fenzi.get(i)).divide
                            (new BigDecimal(idList.size()), 4, BigDecimal.ROUND_HALF_UP);
                    if (m >= 2) {
                        System.out.println("m=" + m + "result:" + result);
                        if (result.compareTo(new BigDecimal(1.00)) >= 0) {
                            resultMap.put(i, result.toString());
                        }
                    }
                    if (m >= 1 && m < 2) {
                        System.out.println("m=" + m + "result:" + result);
                        if (result.compareTo(new BigDecimal(0.65)) >= 0) {
                            resultMap.put(i, result.toString());
                        }
                    }
                }
            }
            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("方法异常" + e);
        }
        return null;
    }

    @Override
    public String getSizeRate(String numStr, String openNum, Integer n) {
        try {
            List<Integer> idList = caipaiMapper.queryPosition(openNum);
            System.out.println("idList：" + idList.size());
            Integer length = numStr.length();
            Map<String, Object> paramMap = null;
            Integer fenzi1 = 0;
            Integer fenzi0 = 0;
            Integer fenmu = 0;
            boolean flag3 = false;
            boolean flag1 = false;
            boolean flag0 = false;
            List<Integer> ids = getAvalibale(idList, length, numStr);
            if (ids.size() == 0 || ids.size() <= 5) {
                return "no data";
            }
            for (Integer id : ids) {
                flag1 = false;
                flag0 = false;
                List<Integer> list = new ArrayList<>();
                for (int i = 1; i <= n; i++) {
                    list.add(id + i);
                }
                List<CaipiaoDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfos(list);
                for (CaipiaoDTO caipiaoDTO : caipiaoDTOS) {
                    if (caipiaoDTO.getN1() + caipiaoDTO.getN2() + caipiaoDTO.getN3()
                            + caipiaoDTO.getN4() + caipiaoDTO.getN5() >= 23) {
                        flag1 = true;
                    } else {
                        flag0 = true;
                    }
                }
                if (flag1) {
                    fenzi1++;
                }
                if (flag0) {
                    fenzi0++;
                }
            }


            String rate0 = new BigDecimal(fenzi0).divide(new BigDecimal(ids.size()), 4, BigDecimal.ROUND_HALF_UP).toString();
            String rate1 = new BigDecimal(fenzi1).divide(new BigDecimal(ids.size()), 4, BigDecimal.ROUND_HALF_UP).toString();
            return rate0 + "=====" + rate1;
        } catch (Exception e) {
            System.out.println("异常" + e);
        }
        return null;
    }

    @Override
    public String getDoubleNew(String openNum, Integer m, Integer n) {
        try {
            List<Integer> idList = caipaiMapper.queryPosition(openNum);
            Map<String, Object> paramMap = null;
            Map<Integer, List<Integer>> fenmu = new HashMap<>();
            for (Integer id : idList) {
                paramMap = new HashMap<>();
                paramMap.put("firstId", id - m);
                paramMap.put("endId", id);
                List<CaipiaoDTO> caipiaoList = caipaiMapper.queryBwteen(paramMap);
                Collections.sort(caipiaoList, new Comparator<CaipiaoDTO>() {
                    @Override
                    public int compare(CaipiaoDTO o1, CaipiaoDTO o2) {
                        return o2.getId() - o1.getId();
                    }
                });
                ableCaipiaoDTO(caipiaoList, fenmu);
            }

            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                List<Integer> list = fenmu.get(i);
                if (list == null || list.size() == 0) {
                    continue;
                }
                Map<Integer, Integer> fenzi = new HashMap<>();
                List<Integer> paramList = null;
                for (Integer id : list) {
                    paramList = new ArrayList<>();
                    for (int j = 1; j <= n; j++) {
                        paramList.add(id + j);
                    }
                    List<CaipiaoDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfos(paramList);
                    String newopenNum = "";
                    for (CaipiaoDTO caipiaoDTO : caipiaoDTOS) {
                        Integer openNumNext = caipiaoDTO.getOpenNum();
                        String openNumStr = "";
                        if (openNumNext < 10000) {
                            openNumStr = "0" + openNumNext;
                        } else {
                            openNumStr = String.valueOf(openNumNext);
                        }
                        newopenNum += openNumStr;
                    }

                    for (int k = 0; k <= 9; k++) {
                        String num = String.valueOf(k);
                        if (newopenNum.contains(num)) {
                            if (fenzi.containsKey(k)) {
                                int value = fenzi.get(k);
                                fenzi.put(k, value + 1);
                            } else {
                                fenzi.put(k, 1);
                            }
                        }
                    }


                }

                Map<Integer, Object> map = new HashMap<>();
                for (int a = 0; a <= 9; a++) {
                    if (fenzi.containsKey(a)) {
                        String value  = new BigDecimal(fenzi.get(a)).divide(new BigDecimal(list.size()),
                                4, BigDecimal.ROUND_HALF_UP).toString();
                        map.put(a, value);
                    }
                 }
                resultMap.put(i, map);

            }
            return resultMap.toString();
        } catch (Exception e) {
            System.out.println("方法异常" + e);
        }
        return null;
    }

    @Override
    public String getDoubleRate(String openNum, Integer m, Integer n, Integer a) {

        try {
          //处理openNum
            System.out.println("openNum====" + openNum);
            StringBuffer openNumSb = new StringBuffer();
            for (int i = 0; i < openNum.length(); i ++) {
                if (openNum.charAt(i) == '0') {
                    openNumSb.append('0');
                    continue;
                }
                openNumSb.append("%");
                openNumSb.append(openNum.charAt(i));

            }
            String openNumNew = openNumSb.append("%").toString();
            //判断是前面几期
            System.out.println("处理openNum" + openNumNew);
            List<Integer> listId = new ArrayList<>();
            if(m == 2) {
                listId = caipaiMapper.queryTwoCount(openNumNew);
            }
            if (m == 3) {
                listId = caipaiMapper.queryThreeCount(openNumNew);
            }
            if (m == 4) {
                listId = caipaiMapper.queryFourCount(openNumNew);
            }
            System.out.println("listId===" + listId.size());
            List<Integer> paramList = null;
            Map<Integer, Integer> fenzi = new HashMap<>();
            for (Integer id : listId) {
                paramList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    paramList.add(id + j + m );
                }
                List<CaipiaoDTO> caipiaoDTOS = caipaiMapper.queryCaipiaoInfos(paramList);
                String newopenNum = "";
                for (CaipiaoDTO caipiaoDTO : caipiaoDTOS) {
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
            }

            System.out.println("分子==" + fenzi);
            Map<Integer, Object> resultMap = new HashMap<>();
            for (int i = 0; i <= 9; i++) {
                if (fenzi.containsKey(i)) {
                    BigDecimal result = new BigDecimal(fenzi.get(i)).divide
                            (new BigDecimal(listId.size()), 4, BigDecimal.ROUND_HALF_UP);
                    resultMap.put(i, result.toString());
                }
            }
            System.out.println("result====" + resultMap);
            return resultMap.toString();

        } catch (Exception e) {
            System.out.println("方法异常" + e);
            return null;
        }

    }

    @Override
    public String getsizeRateNew(String openNun, Integer m) {

        try {
            if (openNun.length() != 10) {
                return "len is letty";
            }
            Integer bigCount = 0;
            Integer smallCount = 0;
            Map<String, Object> map =  new HashMap<>();
            map.put("first", String.valueOf(openNun.charAt(0)));
            map.put("second", String.valueOf(openNun.charAt(1)));
            map.put("three", String.valueOf(openNun.charAt(2)));
            map.put("four", String.valueOf(openNun.charAt(3)));
            map.put("five", String.valueOf(openNun.charAt(4)));
            map.put("six", String.valueOf(openNun.charAt(5)));
            map.put("seven", String.valueOf(openNun.charAt(6)));
            map.put("eigth", String.valueOf(openNun.charAt(7)));
            map.put("night", String.valueOf(openNun.charAt(8)));
            map.put("ten", String.valueOf(openNun.charAt(9)));
            List<Integer> ids = caipaiMapper.querySizeNew(map);
            if (ids == null || ids.size() < 100) {
                return "no data";
            }
            List<Integer> list = new ArrayList<>();
            for (Integer id : ids) {
                list.add(id + 11);
            }
            List<Integer> remarks = caipaiMapper.queryRemark(list);
            for (Integer remark : remarks) {
                if (remark < 23) {
                    smallCount ++;
                } else  {
                    bigCount ++;
                }

            }
            Map<String, Object> resultMap = new HashMap<>();
            BigDecimal result0 = new BigDecimal(smallCount).divide
                    (new BigDecimal(ids.size()), 4, BigDecimal.ROUND_HALF_UP);
            resultMap.put("0", result0.toString());

            BigDecimal result1 = new BigDecimal(bigCount).divide
                    (new BigDecimal(ids.size()), 4, BigDecimal.ROUND_HALF_UP);
            resultMap.put("1", result1.toString());

            return  resultMap.toString();
        } catch (Exception  e) {
            System.out.println("Excetion: " + e);
        }
        return null;
    }

    private void ableCaipiaoDTO(List<CaipiaoDTO> caipiaoDTOS, Map<Integer, List<Integer>> map) throws Exception {
        boolean flag = false;
        for (int i = 0; i <= 9; i++) {
            flag = false;
            for (CaipiaoDTO caipiaoDTO : caipiaoDTOS) {
                String openNum = "";
                if (caipiaoDTO.getOpenNum() < 10000) {
                    openNum = "0" + caipiaoDTO.getOpenNum();
                } else {
                    openNum = "" + caipiaoDTO.getOpenNum();
                }
                if (openNum.contains(String.valueOf(i))) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }

            }
            if (flag) {
                if (map.containsKey(i)) {
                    List<Integer> value = map.get(i);
                    value.add(caipiaoDTOS.get(0).getId());
                    map.put(i, value);
                } else {
                    List<Integer> value = new ArrayList<>();
                    value.add(caipiaoDTOS.get(0).getId());
                    map.put(i, value);
                }
            }
        }

    }

    private void getFenzi(List<Integer> idList, Integer m, Map<Integer, Integer> fenzi, Integer position) throws Exception {
        for (Integer id : idList) {
            List<Integer> postList = new ArrayList<>();
            for (int i = 1; i <= m; i++) {
                postList.add(id + i);
            }
            List<CaipiaoDTO> caipiaosTemp = caipaiMapper.queryCaipiaoInfos(postList);
            String nStr = "";
            for (CaipiaoDTO caipiaoDTO : caipiaosTemp) {
                if (position == 1) {
                    nStr += String.valueOf(caipiaoDTO.getN1());
                }
                if (position == 2) {
                    nStr += String.valueOf(caipiaoDTO.getN2());
                }
                if (position == 3) {
                    nStr += String.valueOf(caipiaoDTO.getN3());
                }
                if (position == 4) {
                    nStr += String.valueOf(caipiaoDTO.getN4());
                }
                if (position == 5) {
                    nStr += String.valueOf(caipiaoDTO.getN5());
                }
            }
            for (int i = 0; i <= 9; i++) {
                String str = String.valueOf(i);
                if (nStr.contains(str)) {
                    if (fenzi.containsKey(i)) {
                        int value = fenzi.get(i);
                        fenzi.put(i, value + 1);
                    } else {
                        fenzi.put(i, 1);
                    }
                }
            }
        }
    }

    private List<Integer> getAvalibale(List<Integer> idList, Integer length, String numStr) {
        Map<String, Object> paramMap = null;
        boolean type = false;
        List<Integer> list = new ArrayList<>();
        for (Integer id : idList) {
            paramMap = new HashMap<>();
            paramMap.put("firstId", id - length);
            paramMap.put("endId", id - 1);
            List<CaipiaoDTO> caipiaoList = caipaiMapper.queryBwteen(paramMap);
            //排序
            Collections.sort(caipiaoList, new Comparator<CaipiaoDTO>() {
                @Override
                public int compare(CaipiaoDTO o1, CaipiaoDTO o2) {
                    return o1.getId() - o2.getId();
                }
            });
            for (int i = 0; i < caipiaoList.size(); i++) {
                String flag = numStr.substring(i, i + 1);
                if ("1".equals(flag)) {
                    if (caipiaoList.get(i).getN1() + caipiaoList.get(i).getN2() +
                            caipiaoList.get(i).getN3() + caipiaoList.get(i).getN4()
                            + caipiaoList.get(i).getN5() <= 22) {
                        System.out.println("22222");
                        type = false;
                        break;
                    } else {
                        type = true;
                        System.out.println("23332");
                        continue;
                    }

                } else {
                    if (caipiaoList.get(i).getN1() + caipiaoList.get(i).getN2() +
                            caipiaoList.get(i).getN3() + caipiaoList.get(i).getN4() +
                            caipiaoList.get(i).getN5() > 22) {
                        System.out.println("66666");
                        type = false;
                        break;
                    } else {
                        type = true;
                        continue;
                    }
                }
            }
            if (type) {
                list.add(id);
            }
            System.out.println("11111");
        }
        return list;
    }

}
