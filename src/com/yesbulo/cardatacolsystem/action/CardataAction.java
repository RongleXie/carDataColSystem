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
 * <p>@Company: RongleXie</P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-4 下午01:30:18</p>
 */
public class CardataAction {
	private String speed;// 速度
	private String accelerometer;// 加速度
	private String longitude;// 经度
	private String latitude;// 纬度
	private String altitude;// 海拔高度
	private String slope;// 坡度

	private List<String> slopearr;
	private List<String> accelerationarr = null;
	private List<String> latitudearr;
	private List<String> longitudearr;
	private List<String> altitudearr;
	private List<String> speedarr;

	private String code;

	// public static JSONObject json = new JSONObject();
	public JSONArray jsonDay = new JSONArray();
	public JSONArray jsonWeek = new JSONArray();
	public JSONArray jsonMonth = new JSONArray();
	public JSONArray jsonYear = new JSONArray();
	
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

		// System.out.println("收集数据条数："+slopearr.size());
		if (accelerationarr!=null && !accelerationarr.isEmpty()) {
			Date date = null;
			date = new Date();
			if (accelerationarr.size()>0) {
				for (int i = 0; i < accelerationarr.size(); i++) {
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
					System.out.println("第" + (i + 1) + "条" + slopearr.get(i)
							+ accelerationarr.get(i) + speedarr.get(i)
							+ longitudearr.get(i) + latitudearr.get(i)
							+ altitudearr.get(i));
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
				cardata.setCardataSize("0");
				cardata.setCardataTrail("0");
				cardata.setCardataKey1("0");
				cardata.setCardataKey2("0");
				giveDao().save(cardata);
				System.out.println(cardata.toString());
				setCode("1");// 收集成功
			}else {
				setCode("0");//收集失败
			}
		}else {
			setCode("4");//数据不合法，不能正常收集
		}
		return "success";
	}

	// #获取历史数据-天
	public String getCardataOfDay() {
		System.out.println("CardataAction.getCardataOfDay()");
		jsonDay.clear();
		List<?> list = giveDao()
				.getObjectListBycond(
						"Cardata",
						"where cardata_time like concat(curdate(),'%') GROUP BY cardata_time ORDER BY cardata_time DESC");
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			Cardata cardata = new Cardata();
			cardata = (Cardata) list.get(i);
			System.out.println("**********" + cardata.getCardataTime());
			List<?> list2 = giveDao().getObjectListBycond("Cardata",
					"where cardata_time='" + cardata.getCardataTime() + "'");

			List<Cardata> cardataList = new ArrayList<Cardata>();
			StringBuffer data = new StringBuffer();
			for (int j = 0; j < list2.size(); j++) {
				data = data.append(((Cardata) list2.get(j))
						.getCardataLongitude());
				data = data.append(",");
				data = data.append(((Cardata) list2.get(j))
						.getCardataLatitude());
				if (j != list2.size() - 1) {
					data = data.append(";");
				}
			}
			System.out.println("size" + list2.size());
			System.out.println("data" + data);
			cardata.setCardataKey1(data.toString());
			cardataList.add(cardata);
			// 去掉json多余参数
			JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
			jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
			jsonConfig.setExcludes(new String[] { "cardataPhone",
					"cardataSize", "cardataId", "cardataKey2", "cardataTrail",
					"updateTime", "insertTime", "cardataTime", }); // 只要将所需忽略字段加到数组中即可

			// HashMap<String, Cardata> map = new HashMap<String, Cardata>();
			// System.out.println(map);
			// map.put("Cardata", cardata);
			// List<Map<String,Cardata>> list3 = new
			// ArrayList<Map<String,Cardata>>();
			// list3.add(map);
			// HashMap<String, List<Map<String, Cardata>>> map2 = new
			// HashMap<String, List<Map<String, Cardata>>>();
			// map2.put("c", list3);
			// System.out.println(map2);

			JSONObject tempJson = new JSONObject();
			System.out.println("++++" + tempJson);
			tempJson.clear();
			tempJson.put("Cardata", cardata);
			System.out.println("tempJson" + tempJson);

			jsonDay.add(tempJson);

			// json.element("CardataList", jsonArray);

		}

		// List<?> list = giveDao().getObjectListByfield("cardata",
		// "cardata_time", date);
		return "success";
	}

	// #获取历史数据-周
	public String getCardataOfWeek() {
		System.out.println("CardataAction.getCardataOfWeek()");
		jsonWeek.clear();
		List<?> list = giveDao().getObjectListBycondCardataWeek();
		for (int i = 0; i < list.size(); i++) {
			JSONObject tempJson = new JSONObject();
			tempJson.clear();
			tempJson.put("Cardata", list.get(i));
			System.out.println("tempJson" + tempJson.toString());
			jsonWeek.add(tempJson);
		}

		return "success";
	}
	
	// #获取历史数据-月
	public String getCardataOfMonth() {
		System.out.println("CardataAction.getCardataOfMonth()");
		jsonMonth.clear();
		List<?> list = giveDao().getObjectListBycondCardataMonth();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			JSONObject tempJson = new JSONObject();
			tempJson.clear();
			tempJson.put("Cardata", list.get(i));
			System.out.println("tempJson" + tempJson.toString());
			jsonMonth.add(tempJson);
		}
		return "success";
	}
	
	// #获取历史数据-年
	public String getCardataOfYear() {
		System.out.println("CardataAction.getCardataOfYear()");
		jsonYear.clear();
		List<?> list = giveDao().getObjectListBycondCardataYear();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			JSONObject tempJson = new JSONObject();
			tempJson.clear();
			tempJson.put("Cardata", list.get(i));
			System.out.println("tempJson" + tempJson.toString());
			jsonYear.add(tempJson);
		}
		return "success";
	}
	// #测试
	public static void main(String[] args) {
		CardataAction cardataAction = new CardataAction();
		 cardataAction.getCardataOfDay();
		// cardataAction.getCardataOfWeek();
		//cardataAction.getCardataOfMonth();
		//cardataAction.getCardataOfYear();
		// System.out.println("json"+json.toString());
		// System.out.println("——————————————"+jsonArray.get(0));
		// System.out.println(jsonArray.toString());
		// System.out.println("json数组长度："+jsonWeek.size());
		List<?> list = giveDao().getObjectListBycondCardataMonth();
		System.out.println("***************" + list.size());
		for (int i = 0; i < list.size(); i++) {
			//			
			// Cardata cardata = new Cardata();
			// cardata = (Cardata) list.get(i);
			// System.out.println(cardata.getCardataAcceleration());
//			Object object = list.get(i);
//			System.out.println(object.toString());
//			System.out.println(list.get(i));
		}
		// System.out.println(data);
		// json = new JSONObject();

		// List<?> list = giveDao().getObjectListByfield("Cardata",
		// "cardata_time", "'2016-09-18 12:07:48'");
		// System.out.println(list.size());
		// List<?> list2 = giveDao().getObjectListBycond("Cardata",
		// "GROUP BY cardata_time");
		// System.out.println(list2);
		// List<Task> taskList = new ArrayList<Task>();
		// for (Object object : list) {
		// taskList.add((Task) object);
		// }
		//
		// // 去掉json多余参数
		// JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
		// jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
		// jsonConfig.setExcludes(new String[] { "tasApplies", "tasUser" }); //
		// 只要将所需忽略字段加到数组中即可
		//
		// HashMap<String, List<?>> map = new HashMap<String, List<?>>();
		// map.put("TaskList", tas kList);
		// json.putAll(map, jsonConfig);

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
	
	
	public JSONArray getJsonMonth() {
		return jsonMonth;
	}

	public void setJsonMonth(JSONArray jsonMonth) {
		this.jsonMonth = jsonMonth;
	}

	public JSONArray getJsonYear() {
		return jsonYear;
	}

	public void setJsonYear(JSONArray jsonYear) {
		this.jsonYear = jsonYear;
	}
	
	public JSONArray getJsonWeek() {
		return jsonWeek;
	}

	public void setJsonWeek(JSONArray jsonWeek) {
		this.jsonWeek = jsonWeek;
	}

	public JSONArray getJsonDay() {
		return jsonDay;
	}

	public void setJsonDay(JSONArray jsonDay) {
		this.jsonDay = jsonDay;
	}
	
	
}
