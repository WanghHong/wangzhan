package com.wh.kaifa.controller;

import com.wh.kaifa.DTO.CanlaDaDTO;
import com.wh.kaifa.service.CaipiaoService;
import com.wh.kaifa.service.CanlanDaService;
import com.wh.kaifa.service.GetCaipiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class CaipiaoController {
    @Autowired
    private GetCaipiaoService getCaipiaoService;

    @Autowired
    private CaipiaoService caipiaoService;

    @Autowired
    private CanlanDaService canlanDaService;

    @GetMapping("/test")
    public String test() {
        System.out.println("111");
        return "111";
    }

    @GetMapping("/getInfo/{openNum}/{id}")
    public String getCaipiaoInfo(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable(name = "openNum") Integer openNum,
                                 @PathVariable(name = "id") Integer id) {
        System.out.println("====" + id + openNum);
        return getCaipiaoService.getCaipiaoInfo(id, openNum);
    }

    @GetMapping("/getInfoNew/{openNum}/{id}")
    public String getInfoNew(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable(name = "openNum") Integer openNum,
                             @PathVariable(name = "id") Integer id) {
        System.out.println("====" + id + openNum);
        return getCaipiaoService.getCaipiaoInfoNew(id, openNum);
    }

    @GetMapping("/getInfoNext/{openNum}")
    public String getInfoNext(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable(name = "openNum") Integer openNum) {
        System.out.println("====" + openNum);
        return getCaipiaoService.getCaipiaoNext(1, openNum);
    }

    @GetMapping("/getOpenNum/{openNum}/{m}/{n}/{k}")
    public String getOpenNum(@PathVariable(name = "openNum") Integer openNum,
                             @PathVariable(name = "m") Integer m,
                             @PathVariable(name = "n") Integer n,
                             @PathVariable(name = "k") Integer k) {
        return getCaipiaoService.getNM(m, n, openNum, k);
    }

    @GetMapping("/getDiff/{m}/{openNum}")
    public String getDiff(@PathVariable(name = "m") Integer m,
                          @PathVariable(name = "openNum") Integer openNum) {
        return caipiaoService.getDiffMN(m, openNum);
    }

    @GetMapping("/getBigData/{openNum}/{m}")
    public String getBigData(@PathVariable(name = "m") Integer m,
                             @PathVariable(name = "openNum") Integer openNum) {
        return caipiaoService.getDoubleNum(m, openNum);
    }

    @GetMapping("/getPlanb/{m}/{n}/{openNum}")
    public String getDiff(@PathVariable(name = "m") Integer m,
                          @PathVariable(name = "n") Integer n,
                          @PathVariable(name = "openNum") Integer openNum) {
        return caipiaoService.getPlanb(m, n, openNum);
    }

    @GetMapping("/getPlan0x/{openNum}/{m}")
    public String getPlan0x(@PathVariable(name = "m") Integer m,
                            @PathVariable(name = "openNum") Integer openNum) {
        return caipiaoService.getPlan0X(openNum, m);
    }

    @GetMapping("/getPlanDoule/{openNum}/{preOpenNum}/{m}")
    public String getPlanDoule(@PathVariable(name = "m") Integer m,
                               @PathVariable(name = "openNum") Integer openNum,
                               @PathVariable(name = "preOpenNum") Integer preOpenNum) {
        return caipiaoService.getPlanDoule(openNum, preOpenNum, m);
    }


    @GetMapping("/getPlanFive/{openNum1}/{openNum2}/{openNum3}/{m}")
    public String getPlanDoule(@PathVariable(name = "m") Integer m,
                               @PathVariable(name = "openNum1") String openNum1,
                               @PathVariable(name = "openNum2") String openNum2,
                               @PathVariable(name = "openNum3") String openNum3) {
        return caipiaoService.getPlanFive(openNum1, openNum2, openNum3, m);
    }

    @GetMapping("/getPlanFiveNew/{openNum1}/{openNum2}/{openNum3}/{m}/{position}")
    public String getPlanDoule(@PathVariable(name = "m") Integer m,
                               @PathVariable(name = "openNum1") Integer openNum1,
                               @PathVariable(name = "openNum2") Integer openNum2,
                               @PathVariable(name = "openNum3") Integer openNum3,
                               @PathVariable(name = "position") Integer position) {
        return caipiaoService.getPlanFiveNew(openNum1, openNum2, openNum3, m, position);
    }

    @GetMapping("/getPlanPosition/{openNum}/{m}")
    public String getPlanDoule(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "m") Integer m) {
        return caipiaoService.getPanPosition(openNum, m);
    }

    @GetMapping("/getCanladaPosition/{openNum}/{m}")
    public String getCanladaPosition(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "m") Integer m) {
        return canlanDaService.getCanladaPosition(openNum, m);
    }

    @GetMapping("/getCanladaDouble/{openNum}/{preOpenNum}/{m}")
    public String getCanladaDouble(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "openNum") String preOpenNum,
            @PathVariable(name = "m") Integer m) {
        return canlanDaService.getCanladaDouble(openNum, preOpenNum, m);
    }

    @GetMapping("/getCanladaDoubleNew/{openNum}/{preOpenNum}/{m}")
    public String getCanladaDoubleNew(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "openNum") String preOpenNum,
            @PathVariable(name = "m") Integer m) {
        return canlanDaService.getCanladaDoubleNew(openNum, preOpenNum, m);
    }

    @GetMapping("/getRateNew/{preNumber}/{openNum}/{m}")
    public String getRateNew(
            @PathVariable(name = "preNumber") String preNumber,
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "m") Integer m) {
        return caipiaoService.getSizeRate(preNumber, openNum, m);
    }

    @GetMapping("/getDoubleNew/{openNum}/{m}/{n}")
    public String getDoubleNew(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "m") Integer m,
            @PathVariable(name = "n") Integer n) {
        return caipiaoService.getDoubleNew(openNum, m, n);
    }


    @GetMapping("/getDoubleRate/{openNum}/{m}/{n}")
    public String getDoubleRate(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "m") Integer m,
            @PathVariable(name = "n") Integer n) {
        return caipiaoService.getDoubleRate(openNum, m, n, 1);
    }


    @GetMapping("/getPlanPosition1/{m}/{openNum}")
    public String getPlanDoule1(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "m") Integer m) {
        return caipiaoService.getPanPosition(openNum, m);
    }


    @GetMapping("/getSizeRateNew/{m}/{openNum}")
    public String getSizeRateNew(
            @PathVariable(name = "openNum") String openNum,
            @PathVariable(name = "m") Integer m) {
        return caipiaoService.getsizeRateNew(openNum, m);
    }
}
