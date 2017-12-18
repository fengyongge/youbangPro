package com.test.utils;


import java.sql.Timestamp;
import java.util.*;


public class ListUtil{

	/**
	 * string集合转long集合
	 * @param List<String>
	 * @return List<Long>
	 */
	public static List<Long> StringToLong(List<String> strlist){
		List<Long> longList = new ArrayList<>();
		for (String str : strlist) {
			longList.add(Long.parseLong(str.trim()));
		}
		return longList;
	}
	
	/**
	 * string集合转Integer集合
	 * @param List<String>
	 * @return List<Long>
	 */
	public static List<Integer> StringToInteger(List<String> strlist){
		List<Integer> longList = new ArrayList<>();
		for (String str : strlist) {
			longList.add(Integer.parseInt(str));
		}
		return longList;
	}
	
	/**
	 * List<Map>转成List<Long>
	 * @param list map中有且仅有一个键，值可转为Long类型
	 * @param key  key为map中的键
	 * @return List<Long>
	 */
	public static List<Long> listMapToList(List<Map> list, String key){
		List<Long> ids = new ArrayList<>();
		for (Map map : list) {
			if(isNotEmpty(map)&&map.containsKey(key)&&map.get(key)!=null){
				ids.add(Long.parseLong(String.valueOf(map.get(key))));
			}
		}
		return ids;
	}
	
	/**
	 * 判断List集合是否为空
	 * @param list
	 * @return 不为空返回true,为空返回false
	 */
	public static boolean isNotEmpty(List list){
		if(list!=null&&!list.isEmpty()&&list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断Map集合是否为空
	 * @param map
	 * @return 不为空返回true,为空返回false
	 */
	public static boolean isNotEmpty(Map map){
		if(map!=null&&!map.isEmpty()&&map.size()>0){
			for(Object key : map.keySet()){
				if(map.get(key)!=null){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 将以分隔符隔开的字符串转为list
	 * @param str 字符串   str为空时直接返回null
	 * @param split 分隔符   不传时默认为逗号：,
	 * @return List<String>
	 */
	public static List<String> strToList(String str, String split){
		if(!StringValidateUtil.isNotEmpty(str)){
			return null;
		}
		if(null == split){
			split = ",";
		}
		List<String> list = Arrays.asList(str.split(split));
		return list;
	}
	
	/**
	 * Integer集合转long集合
	 * @param List<String>
	 * @return List<Long>
	 */
	public static List<Long> IntegerToLong(List<Integer> intlist){
		List<Long> longList = new ArrayList<>();
		for (Integer integer : intlist) {
			longList.add(integer.longValue());
		}
		return longList;
	}
	
	/**
	 * 
	 * description:将List<Map>按时间排序
	 *
	 * @author jiachenghao	
	 * @param list
	 * @param key 排序依据的字段
	 * @param type 0正序；1倒序
	 * @return
	 */
	public static List<Map> ListSort(List<Map> list, final String key, final int type) {
		List<Map> list0 = new ArrayList<Map>();
		for (Map map : list) {
			if(StringValidateUtil.isNotEmpty(String.valueOf(map.get("member_id")))){
				list0.add(map);
			}
		}
		Comparator<Map> comparator = new Comparator<Map>() {

			@Override
			public int compare(Map o1, Map o2) {
				Long member_id1 = Long.valueOf(String.valueOf(o1.get("member_id")));
				Long member_id2 = Long.valueOf(String.valueOf(o2.get("member_id")));
				if(member_id1<member_id2){
					return -1;
				}else if(member_id1>member_id2){
					return 1;
				}else{
					Timestamp begin = Timestamp.valueOf(String.valueOf(o1.get(key)));
					Timestamp end = Timestamp.valueOf(String.valueOf(o2.get(key)));
					if(type==1){//倒序
						if (begin.getTime() > end.getTime()) {
							return -1;
						} else if (begin.getTime() < end.getTime()) {
							return 1;
						} else {
							return 0;
						}
					}else{
						if (begin.getTime() < end.getTime()) {
							return -1;
						} else if (begin.getTime() > end.getTime()) {
							return 1;
						} else {
							return 0;
						}
					}
				}
			}
		};
		Collections.sort(list0, comparator);
		return list0;
	}
	
	/**
	 * 
	 * description: 将List<T>转为","隔开的String
	 *
	 * @author jiachenghao	
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> String ObjToString(List<T> list){
		if(!ListUtil.isNotEmpty(list)){
			return "";
		}
		StringBuffer bf = new StringBuffer();
		for (Object object : list) {
			bf.append(object.toString()).append(",");
		}
		bf.deleteCharAt(bf.lastIndexOf(","));
		return bf.toString();
	}
}

