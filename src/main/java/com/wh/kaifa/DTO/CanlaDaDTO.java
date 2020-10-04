package com.wh.kaifa.DTO;

public class CanlaDaDTO {
	private Integer id;
	private String betEndTime;
	private String turnNum;
	private String openNum;
	private String openTime;
	private Integer gameId;

	private Integer n1;
	private Integer n2;
	private Integer n3;
	private String statDate;
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
	public String getOpenNum() {
		return openNum;
	}
	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
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
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	@Override
	public String toString() {
		return "CanlaDaDTO [id=" + id + ", betEndTime=" + betEndTime + ", turnNum=" + turnNum + ", openNum=" + openNum
				+ ", openTime=" + openTime + ", gameId=" + gameId + ", n1=" + n1 + ", n2=" + n2 + ", n3=" + n3
				+ ", statDate=" + statDate + "]";
	}

}
