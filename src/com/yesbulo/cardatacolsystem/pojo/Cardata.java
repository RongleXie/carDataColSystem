package com.yesbulo.cardatacolsystem.pojo;

import java.util.Date;


/**
 * 数据收集实体类
 * <p>@Title:Cardata</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-4 上午10:58:42
 */
public class Cardata {
	private int cardataId;//数据ID	
	private String cardataPhone;//手机号
	private String cardataSpeed;//行车速度
	private String cardataAcceleration;//行车加速度
	private String cardataLongitude;//经度
	private String cardataLatitude;//纬度
	private String cardataAltitude;//海拔高度
	private String cardataSlope;//行驶坡度
	private String cardataTrail;//行驶轨迹
	private String cardataSize;//数据大小
	private String cardataKey1;//备用KEY1
	private String cardataKey2;//备用KEY2
	private Date cardataTime;//收集时间
	private Date insertTime;//插入时间
	private Date updateTime;//更新时间
	
	public Cardata() {
		// TODO Auto-generated constructor stub
	}

	public int getCardataId() {
		return cardataId;
	}

	public void setCardataId(int cardataId) {
		this.cardataId = cardataId;
	}

	public String getCardataPhone() {
		return cardataPhone;
	}

	public void setCardataPhone(String cardataPhone) {
		this.cardataPhone = cardataPhone;
	}

	public String getCardataSpeed() {
		return cardataSpeed;
	}

	public void setCardataSpeed(String cardataSpeed) {
		this.cardataSpeed = cardataSpeed;
	}

	public String getCardataAcceleration() {
		return cardataAcceleration;
	}

	public void setCardataAcceleration(String cardataAcceleration) {
		this.cardataAcceleration = cardataAcceleration;
	}

	public String getCardataLongitude() {
		return cardataLongitude;
	}

	public void setCardataLongitude(String cardataLongitude) {
		this.cardataLongitude = cardataLongitude;
	}

	public String getCardataLatitude() {
		return cardataLatitude;
	}

	public void setCardataLatitude(String cardataLatitude) {
		this.cardataLatitude = cardataLatitude;
	}

	public String getCardataAltitude() {
		return cardataAltitude;
	}

	public void setCardataAltitude(String cardataAltitude) {
		this.cardataAltitude = cardataAltitude;
	}

	public String getCardataSlope() {
		return cardataSlope;
	}

	public void setCardataSlope(String cardataSlope) {
		this.cardataSlope = cardataSlope;
	}

	public String getCardataTrail() {
		return cardataTrail;
	}

	public void setCardataTrail(String cardataTrail) {
		this.cardataTrail = cardataTrail;
	}

	public String getCardataSize() {
		return cardataSize;
	}

	public void setCardataSize(String cardataSize) {
		this.cardataSize = cardataSize;
	}

	public String getCardataKey1() {
		return cardataKey1;
	}

	public void setCardataKey1(String cardataKey1) {
		this.cardataKey1 = cardataKey1;
	}

	public String getCardataKey2() {
		return cardataKey2;
	}

	public void setCardataKey2(String cardataKey2) {
		this.cardataKey2 = cardataKey2;
	}

	public Date getCardataTime() {
		return cardataTime;
	}

	public void setCardataTime(Date cardataTime) {
		this.cardataTime = cardataTime;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Cardata [cardataAcceleration=" + cardataAcceleration
				+ ", cardataAltitude=" + cardataAltitude + ", cardataId="
				+ cardataId + ", cardataKey1=" + cardataKey1 + ", cardataKey2="
				+ cardataKey2 + ", cardataLatitude=" + cardataLatitude
				+ ", cardataLongitude=" + cardataLongitude + ", cardataPhone="
				+ cardataPhone + ", cardataSize=" + cardataSize
				+ ", cardataSlope=" + cardataSlope + ", cardataSpeed="
				+ cardataSpeed + ", cardataTime=" + cardataTime
				+ ", cardataTrail=" + cardataTrail + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + "]";
	}
	
	
	
	
	
}
