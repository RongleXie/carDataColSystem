package com.yesbulo.cardatacolsystem.action;

import java.util.Date;

import net.sf.json.JSONObject;

import com.yesbulo.cardatacolsystem.pojo.Cardata;
import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.server.ObjectDao;
/**
 * <p>@Title:CardataAction</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-4 下午01:30:18
 */
public class CardataAction {
	private String speed;//速度
	private String accelerometer;//加速度
	private String longitude;//经度
	private String latitude;//纬度
	private String altitude;//海拔高度
	private String slope;//坡度
	
	
	private String code;
	
	public static JSONObject json = new JSONObject();

	private static ObjectDao objectDao = new ObjectDaoImpl();

	/** 获取Dao */
	public ObjectDao giveDao() {
		if (objectDao == null)
			objectDao = new ObjectDaoImpl();
		return objectDao;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	// #用户激活
	public String collect() {
		
		
		
		
		Cardata cardata = new Cardata();
		cardata.setCardataSpeed(speed);
		cardata.setCardataAcceleration(accelerometer);
		cardata.setCardataLongitude(longitude);
		cardata.setCardataLatitude(latitude);
		cardata.setCardataAltitude(altitude);
		cardata.setCardataSlope(slope);
		cardata.setCardataTime(new Date());
		cardata.setInsertTime(new Date());
		cardata.setUpdateTime(new Date());
		
		
//		if (user != null && user.getUseId() > 0) {

//			Object object = ServletActionContext.getRequest().getSession()
//					.getAttribute("phone_yzm");
//			String sysPhoneCode = object != null ? (String) object : null;
//			if (phoneCode.equals(sysPhoneCode)) {
//				user.setUseIscompany(0);
//				user.setUsePhone(usePhone);
//				user.setUseEmei(useEmei);
				giveDao().save(cardata);
				System.out.println(cardata.toString());
				setCode("1");// 注册成功
//			} else
//				setCode("7");// 手机验证码验证不成功
//		} else
//			setCode("4");// 考号/学号不存在

		return "success";
	}
	
	
	
	
	
	
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getAccelerometer() {
		return accelerometer;
	}
	public void setAccelerometer(String accelerometer) {
		this.accelerometer = accelerometer;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getSlope() {
		return slope;
	}
	public void setSlope(String slope) {
		this.slope = slope;
	}
	
	
	
	
}