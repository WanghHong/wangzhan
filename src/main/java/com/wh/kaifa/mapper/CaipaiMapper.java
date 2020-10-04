package com.wh.kaifa.mapper;

import com.wh.kaifa.DTO.CaipiaoDTO;
import com.wh.kaifa.DTO.CanlaDaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghong on 2020/5/13.
 */
@Mapper
@Repository
public interface CaipaiMapper {


    List<Integer> queryCaipiaoInfo(Integer openNum);

    List<CaipiaoDTO> queryCaipiaoInfos(@Param("list") List<Integer> list);

    Integer queryOpenNumById(Integer id);

    Integer queryId(Map<String, Object> param);

    List<Integer> between0(Map<String, Object> param);

    List<Integer> queryPages(Map<String, Object> paramMap);

    List<Integer> queryDiffPages(Map<String, Object> paramMap);

    List<Integer> queryDiffPagesOfOX(Map<String, Object> paramMap);

    List<CaipiaoDTO> queryDouble(Map<String, Object> paramMap);

    List<Integer> queryFive(Map<String, Object> paramMap);

    List<Integer> queryFive1(Map<String, Object> paramMap);

    List<Integer> queryFive2(Map<String, Object> paramMap);

    List<Integer> queryFive3(Map<String, Object> paramMap);

    List<Integer> queryFive4(Map<String, Object> paramMap);

    List<Integer> queryPosition(String openNum);

    List<Integer> queryCanladaPosition(String openNum);

    List<CanlaDaDTO> queryCaipiaoInfo1(@Param("list") List<Integer> list);

    List<Integer> queryCanladaDouble(Map<String, Object> map);

    List<Integer> queryCanladaDoubleNew(Map<String, Object> map);

    List<CaipiaoDTO> queryBwteen(Map<String, Object> map);


    List<Integer> queryTwoCount(String openNumNew);

    List<Integer> queryThreeCount(String openNumNew);

    List<Integer> queryFourCount(String openNumNew);

    List<Integer> querySizeNew(Map<String, Object> map);

    List<Integer> queryRemark(@Param("list") List<Integer> list);

}
