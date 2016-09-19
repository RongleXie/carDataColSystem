package com.yesbulo.cardatacolsystem.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import net.sf.json.JSONArray;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yesbulo.cardatacolsystem.pojo.Cardata;
import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.server.HibernateSessionFactory;
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
	
	private List<String> slopearr;
	private List<String> accelerationarr;
	private List<String> latitudearr;
	private List<String> longitudearr;
	private List<String> altitudearr;
	private List<String> speedarr;

	private String code;
	
	public static JSONObject json = new JSONObject();
	public static JSONArray jsonArray = new JSONArray();
	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	private static ObjectDao objectDao = new ObjectDaoImpl();
	
	private SessionFactory sessionFactory = HibernateSessionFactory
	.getSessionFactory();
	private Session session;
	private Transaction tran;

	/** 获取Dao */
	public static ObjectDao giveDao() {
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


	// #收集数据
	public String collect() {
		
		System.out.println("收集数据条数："+slopearr.size());
		Date date = null;
		for (int i = 0; i < slopearr.size(); i++) {
			if (i==0) {
				date = new Date();
			}
			Cardata carCardata = new Cardata();
			carCardata.setCardataSlope(slopearr.get(i));
			carCardata.setCardataAcceleration(accelerationarr.get(i));
			carCardata.setCardataSpeed(speedarr.get(i));
			carCardata.setCardataLongitude(longitudearr.get(i));
			carCardata.setCardataLatitude(latitudearr.get(i));
			carCardata.setCardataAltitude(altitudearr.get(i));
			carCardata.setCardataSize("0");
			carCardata.setCardataTrail("0");
			carCardata.setCardataKey1("0");
			carCardata.setCardataKey2("0");
			carCardata.setCardataTime(date);
			carCardata.setInsertTime(new Date());
			carCardata.setUpdateTime(new Date());
			giveDao().save(carCardata);
			System.out.println(carCardata.toString());
			System.out.println("第"+(i+1)+"条"+slopearr.get(i)+accelerationarr.get(i)+speedarr.get(i)+
					longitudearr.get(i)+latitudearr.get(i)+altitudearr.get(i));
		}
		
		Cardata cardata = new Cardata();
		cardata.setCardataSpeed(speed);
		cardata.setCardataAcceleration(accelerometer);
		cardata.setCardataLongitude(longitude);
		cardata.setCardataLatitude(latitude);
		cardata.setCardataAltitude(altitude);
		cardata.setCardataSlope(slope);
		cardata.setCardataTime(date);
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
	
	// 获取历史数据-天
	public String getCardataOfDay(){
		System.out.println("CardataAction.getCardataOfDay()");
//		json = new JSONObject();
//		List<?> list = giveDao().getObjectListBycond("Cardata", "GROUP BY cardata_time");
//		System.out.println(list);
//		List<Cardata> cardataList = new ArrayList<Cardata>();
//		List time = new ArrayList();
//		for (int i = 0; i < list.size(); i++) {
//			cardataList.add((Cardata) list.get(i));
//			time.add(cardataList.get(i).getCardataTime());
//		}
//		System.out.println("+++"+time.get(0)+"+++");
//		List<?> list2 = giveDao().getObjectListByfield("Cardata", "cardata_time", "'"+time.get(0)+"'");
//		List<Cardata> cardataList2 = new ArrayList<Cardata>();
//		
//		for (Object object : list2) {
//			Cardata cardata = new Cardata();
//			//System.out.println(((Cardata) object).getCardataLongitude());
//			data = data.append(((Cardata) object).getCardataLongitude());
//			data = data.append(",");
//			data = data.append(((Cardata) object).getCardataLatitude()).append(";");
//			cardata.setCardataLongitude(((Cardata) object).getCardataLongitude());
//			cardata.setCardataLatitude(((Cardata) object).getCardataLatitude());
//			cardataList2.add(cardata);
//		}
//		
//		List<?> list3 = giveDao().getObjectListBycond("Cardata", "WHERE cardata_time = '"+time.get(0)+"' ORDER BY id DESC LIMIT 0,1");
//		System.out.println("list3+++++++++++++++++"+list3.size());
//
//		// 去掉json多余参数
//		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
//		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
//		jsonConfig.setExcludes(new String[] { "cardataAcceleration", "cardataPhone","cardataSize", "cardataSlope","cardataId","cardataKey1", "cardataKey2","cardataTrail", "updateTime","insertTime","cardataTime","cardataAltitude","cardataSpeed" }); // 只要将所需忽略字段加到数组中即可
//		
//		HashMap<String, List<?>> map = new HashMap<String, List<?>>();
//		map.put("CardataList", cardataList2);
//		json.putAll(map);
		
		
		json = new JSONObject();
		List<?> list = giveDao().getObjectListBycond("Cardata", "GROUP BY cardata_time");
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			Cardata cardata = new Cardata();
			cardata = (Cardata) list.get(i);

			List<?> list2 = giveDao().getObjectListByfield("Cardata",
					"cardata_time", "'" + cardata.getCardataTime() + "'");

			List<Cardata> cardataList = new ArrayList<Cardata>();
			StringBuffer data = new StringBuffer();
			for (int j = 0; j < list2.size(); j++) {
				data = data.append(((Cardata) list2.get(j)).getCardataLongitude());
				data = data.append(",");
				data = data.append(((Cardata) list2.get(j)).getCardataLatitude());
				if (j!=list2.size()-1) {
					data=data.append(";");
				}
			}
			System.out.println("size"+list2.size());
			System.out.println("data"+data);
			cardata.setCardataKey1(data.toString());
			cardataList.add(cardata);
			// 去掉json多余参数
			JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
			jsonConfig.setExcludes(new String[] { "cardataAcceleration",
					"cardataPhone", "cardataSize", "cardataSlope", "cardataId",
					"cardataKey2",  "cardataTrail", "updateTime",
					"insertTime", "cardataTime", "cardataAltitude",
					"cardataSpeed" }); // 只要将所需忽略字段加到数组中即可
			
			HashMap<String, Cardata> map = new HashMap<String, Cardata>();
			System.out.println(map);
			map.put("Cardata", cardata);
//			List<Map<String,Cardata>> list3 = new ArrayList<Map<String,Cardata>>();
//			list3.add(map);
//			HashMap<String, List<Map<String, Cardata>>>  map2 = new HashMap<String, List<Map<String, Cardata>>>();
//			map2.put("c", list3);
//			System.out.println(map2);
			
			JSONObject tempJson = new JSONObject();
			System.out.println("++++"+tempJson);
			tempJson.clear();
			tempJson.putAll(map,jsonConfig);
			System.out.println("tempJson"+tempJson);
			
			jsonArray.add(tempJson);
			json.element("CardataList", jsonArray);
			
		}
		
//		List<?> list = giveDao().getObjectListByfield("cardata", "cardata_time", date);
		
		return "success";
	}
	
	public static void main(String[] args) {
		CardataAction cardataAction = new CardataAction();
		cardataAction.getCardataOfDay();
		//System.out.println("json"+json.toString());
		System.out.println(jsonArray.toString());
		//System.out.println(data);
//		json = new JSONObject();

//		List<?> list = giveDao().getObjectListByfield("Cardata", "cardata_time", "'2016-09-18 12:07:48'");
//		System.out.println(list.size());
//		List<?> list2 = giveDao().getObjectListBycond("Cardata", "GROUP BY cardata_time");
//		System.out.println(list2);
//		List<Task> taskList = new ArrayList<Task>();
//		for (Object object : list) {
//			taskList.add((Task) object);
//		}
//
//		// 去掉json多余参数
//		JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
//		jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
//		jsonConfig.setExcludes(new String[] { "tasApplies", "tasUser" }); // 只要将所需忽略字段加到数组中即可
//
//		HashMap<String, List<?>> map = new HashMap<String, List<?>>();
//		map.put("TaskList", tas kList);
//		json.putAll(map, jsonConfig);

	}
	
	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		CardataAction.json = json;
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
	public List<String> getLatitudearr() {
		return latitudearr;
	}

	public void setLatitudearr(List<String> latitudearr) {
		this.latitudearr = latitudearr;
	}

	public List<String> getLongitudearr() {
		return longitudearr;
	}

	public void setLongitudearr(List<String> longitudearr) {
		this.longitudearr = longitudearr;
	}

	public List<String> getAltitudearr() {
		return altitudearr;
	}

	public void setAltitudearr(List<String> altitudearr) {
		this.altitudearr = altitudearr;
	}

	public List<String> getSpeedarr() {
		return speedarr;
	}

	public void setSpeedarr(List<String> speedarr) {
		this.speedarr = speedarr;
	}


	
	public static ObjectDao getObjectDao() {
		return objectDao;
	}

	public static void setObjectDao(ObjectDao objectDao) {
		CardataAction.objectDao = objectDao;
	}

	public List<String> getAccelerationarr() {
		return accelerationarr;
	}

	public void setAccelerationarr(List<String> accelerationarr) {
		this.accelerationarr = accelerationarr;
	}

	public List<String> getSlopearr() {
		return slopearr;
	}

	public void setSlopearr(List<String> slopearr) {
		this.slopearr = slopearr;
	}
	
	
	
}
