package com.wh.kaifa.DTO;

/**
 * Created by wanghong on 2020/5/13.
 */
public class CaipiaoDTO {
    private Integer id;
    private String betEndTime;
    private String turnNum;
    private String openTime;
    private Integer n1;
    private Integer n2;
    private Integer n3;
    private Integer n4;
    private Integer n5;
    private Integer openNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBetEndTime() {
        return betEndTime;
    }

    public void setBetEndTime(String betEndTime) {
        this.betEndTime = betEndTime;
    }

    public String getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(String turnNum) {
        this.turnNum = turnNum;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Integer getN1() {
        return n1;
    }

    public void setN1(Integer n1) {
        this.n1 = n1;
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(Integer n2) {
        this.n2 = n2;
    }

    public Integer getN3() {
        return n3;
    }

    public void setN3(Integer n3) {
        this.n3 = n3;
    }

    public Integer getN4() {
        return n4;
    }

    public void setN4(Integer n4) {
        this.n4 = n4;
    }

    public Integer getN5() {
        return n5;
    }

    public void setN5(Integer n5) {
        this.n5 = n5;
    }

    public Integer getOpenNum() {
        return openNum;
    }

    public void setOpenNum(Integer openNum) {
        this.openNum = openNum;
    }

    @Override
    public String toString() {
        return "CaiPiaoDTO{" +
                "id=" + id +
                ", betEndTime='" + betEndTime + '\'' +
                ", turnNum='" + turnNum + '\'' +
                ", openTime='" + openTime + '\'' +
                ", n1=" + n1 +
                ", n2=" + n2 +
                ", n3=" + n3 +
                ", n4=" + n4 +
                ", n5=" + n5 +
                ", openNum=" + openNum +
                '}';
    }
}
