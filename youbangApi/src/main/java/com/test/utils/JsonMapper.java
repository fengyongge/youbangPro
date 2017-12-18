package com.test.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.github.pagehelper.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * �?��封装Jackson，实现JSON String<->Java Object的Mapper.
 * 
 * 封装不同的输出风�? 使用不同的builder函数创建实例.
 * 
 * @author calvin
 */
public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private static ObjectMapper mapper;
    
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private JsonMapper() {};
	//单例模式
    private static class LazyHolder {    
        private static final JsonMapper INSTANCE = new JsonMapper();   
       
    }    
    public static final JsonMapper getInstance() {   
    	mapper = new ObjectMapper();
        return LazyHolder.INSTANCE;    
    }    
	public JsonMapper(JsonInclude.Include include) {
		mapper = new ObjectMapper();
		// 设置输出时包含属性的风格
		if (include != null) {
			mapper.setSerializationInclusion(include);
		}
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属�?		
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	/**
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
	 */
	public static JsonMapper nonEmptyMapper() {
		return new JsonMapper(JsonInclude.Include.NON_EMPTY);
	}

	/**
	 * 创建只输出初始�?被改变的属�?到Json字符串的Mapper, �?��约的存储方式，建议在内部接口中使用�?
	 */
	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(JsonInclude.Include.NON_DEFAULT);
	}

	/**
	 * Object可以是POJO，也可以是Collection或数组�?
	 * 如果对象为Null, 返回"null".
	 * 如果集合为空集合, 返回"[]".
	 * 空串转换�?""
	 */
	public String toJsonWithEmptyStr(Object object) {
		mapper = new ObjectMapper();
		try {
		mapper.setDateFormat(dateFormat);
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
			@Override
			public void serialize(Object arg0, JsonGenerator arg1,
					SerializerProvider arg2) throws IOException,
					JsonProcessingException {
				arg1.writeString("");
				
			}});
		
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
		
		
	}
	
	/**
	 * Object可以是POJO，也可以是Collection或数组�?
	 * 如果对象为Null, 返回"null".
	 * 如果集合为空集合, 返回"[]".
	 */
	public static String toJson(Object object) {
		mapper = new ObjectMapper();
		try {
			mapper.setDateFormat(dateFormat);
			return mapper.writeValueAsString(object);
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.warn("write to json string error:" + object, e);
			return "";
		}


	}

	public static JSONArray toJsonTimestamp(Object object){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONArray jsonArray = new JSONArray().fromObject(object, config);
		return  jsonArray;
	}
	public static JSONObject toJsonObjectFormatTime(Object object){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject jsonArray = new JSONObject().fromObject(object, config);
		return  jsonArray;
	}
	/**
	 * 	字符串转对象    对其中的timestmap特殊处理
	 */
	public static Object jsonToBean(String json, Class<?> bean){
		String[] formats={"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};
		JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
		JSONObject jsonObject = JsonMapper.toJsonObjectFormatTime(json);
		Object object = JSONObject.toBean(jsonObject,bean);
		return  object;
	}
	public static Object jsonToBean(JSONObject json, Class<?> bean){
		String[] formats={"yyyy-MM-dd HH:mm:ss"};
		JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
		Object object = JSONObject.toBean(json,bean);
		return  object;
	}

    //根据数据库中字段生成json
	public static String concatJson(String fields,List<Map> list){
		// 如果fields为空 则返回所有的列表值 此处进行拼装列 fengnj
	    if(null==fields ||fields.equals("null")||fields.equals("")){
		     StringBuffer sbuff = new StringBuffer();
    	     if(list!=null&&!list.isEmpty()&&list.size()!=0){
    		 Iterator<Map.Entry<Integer, Integer>> entries = list.get(0).entrySet().iterator();  
    		 while (entries.hasNext()) {  
    			Map.Entry<Integer, Integer> entry = entries.next();
    			if(entries.hasNext()){
    				sbuff.append(entry.getKey()+",");
    			}else{
    				sbuff.append(entry.getKey());
    			}
    		}
    	}
    	fields = sbuff.toString();
	  }	
		
	  String[]filed = fields.split(",");
	  StringBuffer sb = new StringBuffer();
      sb.append("[");
      for(int i=0;i<list.size();i++){
    	 sb.append("{");
	     for(int j=0;j<filed.length;j++){
	    	if(null==list.get(i).get(filed[j])){
	    		list.get(i).put(filed[j],"");
	        }
	    	sb.append('"'+filed[j]+'"'+":"+'"'+list.get(i).get(filed[j])+'"'); 
            if(j!=filed.length-1){
	          sb.append(","); 
	        }
	     }
		 sb.append("}");
		 if(i!=list.size()-1){
		 sb.append(","); 	 
		 }
      }
      sb.append("]");
	  return sb.toString();
	}
	
	//根据数据库中字段生成json 一维数组json
	public static String concatArrayJson(String fields,List<Map> list){
	  String[]filed = fields.split(",");
	  StringBuffer sb = new StringBuffer();
      sb.append("{");
      for(int i=0;i<list.size();i++){
	     for(int j=0;j<filed.length;j++){
	     sb.append('"'+filed[j]+'"'+":"+'"'+list.get(i).get(filed[j])+'"');  
	     if(j!=filed.length-1){
	     sb.append(","); 
	     }
	     }
		 if(i!=list.size()-1){
		 sb.append(","); 	 
		 }
      }
      sb.append("}");
	  return sb.toString();
	}
		
	//根据数据库中字段生成json
	public static String mapConcatJson(String fields,Map map){
		// 如果fields为空 则返回所有的列表值 此处进行拼装列 fengnj
	    if(null==fields ||fields.equals("null")||fields.equals("")){
		     StringBuffer sbuff = new StringBuffer();
    	     if(!map.isEmpty()){
    		 Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();  
    		 while (entries.hasNext()) {  
    			Map.Entry<Integer, Integer> entry = entries.next();
    			if(entries.hasNext()){
    				sbuff.append(entry.getKey()+",");
    			}else{
    				sbuff.append(entry.getKey());
    			}
    		}
    	}
    	fields = sbuff.toString();
	  }	
		
	  String[]filed = fields.split(",");
	  StringBuffer sb = new StringBuffer();
	  sb.append("{");
      for(int j=0;j<filed.length;j++){
      sb.append('"'+filed[j]+'"'+":"+'"'+map.get(filed[j])+'"');  
	      if(j!=filed.length-1){
	    	  sb.append(","); 
	      }
      }
	  sb.append("}");
	  return sb.toString();
	}
	/**
	 * 反序列化POJO或简单Collection如List<String>.
	 * 
	 * 如果JSON字符串为Null�?null"字符�? 返回Null.
	 * 如果JSON字符串为"[]", 返回空集�?
	 * 
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
	 * 
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType()或contructMapType()构�?类型, 然后调用本函�?
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	@SuppressWarnings("all")
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 构�?Collection类型.
	 */
	@SuppressWarnings("all")
	public JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
		return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

	/**
	 * 构�?Map类型.
	 */
	@SuppressWarnings("all")
	public JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
		return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}

	/**
	 * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬�?.
	 */
	public void update(String jsonString, Object object) {
		try {
			mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
	}

	/**
	 * 輸出JSONP格式數據.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 設定是否使用Enum的toString函數來讀寫Enum,
	 * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	 * 注意本函數一定要在Mapper創建�? �?��的讀寫動作之前調�?
	 */
	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合�?	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的�?
	 */
	public void enableJaxbAnnotation() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		mapper.registerModule(module);
	}

	/**
	 * 取出Mapper做进�?��的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
	
        /**
         * 
         * 获取相关json fromObject
         * 
         * **/
	   public static Map<String, Object> parseJSON2Map(String jsonStr){  
	        Map<String, Object> map = new HashMap<String, Object>();  
	        //�?��层解�? 
	        JSONObject json = JSONObject.fromObject(jsonStr);  
	        for(Object k : json.keySet()){  
	            Object v = json.get(k);   
	            //如果内层还是数组的话，继续解�? 
	            if(v instanceof JSONArray){  
	                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
	                Iterator<JSONObject> it = ((JSONArray)v).iterator();  
	                while(it.hasNext()){  
	                    JSONObject json2 = it.next();  
	                    list.add(parseJSON2Map(json2.toString()));  
	                }  
	                map.put(k.toString(), list);  
	            } else {  
	                map.put(k.toString(), v);  
	            }  
	        }  
	        return map;  
	    }
	   
	   /**
        * 
        * 获取相关json fromObject
        * 
        * **/
	   public static Map<String, Object> parseJSON2ConMap(String jsonStr){  
	        Map<String, Object> map = new ConcurrentHashMap<String, Object>();  
	        //�?��层解�? 
	        JSONObject json = JSONObject.fromObject(jsonStr);  
	        for(Object k : json.keySet()){  
	            Object v = json.get(k);   
	            //如果内层还是数组的话，继续解�? 
	            if(v instanceof JSONArray){  
	                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
	                Iterator<JSONObject> it = ((JSONArray)v).iterator();  
	                while(it.hasNext()){  
	                    JSONObject json2 = it.next();  
	                    list.add(parseJSON2Map(json2.toString()));  
	                }  
	                map.put(k.toString(), list);  
	            } else {  
	                map.put(k.toString(), v);  
	            }  
	        }  
	        return map;  
	    }
	

	
	/**
	 *   pagelist（分页列表）转换成页面json格式 通用方法   fengnj
	 */
	public static JSONObject pageListToJson(Page page,String values) {
		    long total = page.getTotal(); //得到total值 条数
		    JSONObject jsonObject = new JSONObject(); 
		    jsonObject.put("total", total);
		    jsonObject.put("list", values); 
		return jsonObject;
	}
	/**
	 *   pagelist（分页列表）转换成页面json格式 通用方法   jiachenghao
	 */
	public static Map<String, Object> pageListToJson(Page page) {
		long total = page.getTotal(); //得到total值 条数
		Map<String, Object> jsonObject = new HashMap<String, Object>(); 
		jsonObject.put("total", page.getTotal());
		jsonObject.put("list", page.getResult()); 
		return jsonObject;
	}
	
	//List<Map>不分页转换成页面json格式
	public static JSONObject listToJson(List list,String values){
		long total = list.size(); //得到total值 条数
	    JSONObject jsonObject = new JSONObject(); 
	    jsonObject.put("total", total);
	    jsonObject.put("list", values); 
		return jsonObject;
	}
	
	//空List转换成页面json格式
	public static JSONObject emptyListToJson(){
		List<Object> list = new ArrayList<>();
		long total = list.size(); //得到total值 条数
	    JSONObject jsonObject = new JSONObject(); 
	    jsonObject.put("total", total);
	    jsonObject.put("list", toJson(list)); 
		return jsonObject;
	}
	
	//空List转换成页面json格式
	public static JSONObject listToJson(){
		List<Object> list = new ArrayList<>();
		long total = list.size(); //得到total值 条数
	    JSONObject jsonObject = new JSONObject(); 
	    jsonObject.put("list", toJson(list)); 
		return jsonObject;
	}
	
	/**
	 * 判断jsonArray是否为空
	 * @param jsonArray
	 * @return 为空返回false，不为空返回true
	 */
	public static boolean isNotEmpty(JSONArray jsonArray){
		if(jsonArray!=null&&!jsonArray.isEmpty()&&jsonArray.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 去除jsonObj中不需要的键值
	 * @param json
	 * @param keys 需要保留的键   eg: id,name
	 * @return 若成功则返回过滤后的jsonObj
	 */
	public static String removeKey(String json,String keys){
		//若keys为空，则返回fail
		if(!StringValidateUtil.isNotEmpty(keys)){
			return json;
		}
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONObject resultJson = new JSONObject();
		List<String> keyList = Arrays.asList(keys.split(","));
		for (Object key : jsonObject.keySet()) {
			//若该键需要保留，则将该键值对直接添加到resultJson中
			if(keyList.indexOf(key.toString())>-1){
				resultJson.put(key, jsonObject.get(key));
			}
		}
		return resultJson.toString();
	}
	
	/**
	 * <p>更新jsonObj中的键值<p>
	 * 注：json为空时返回fail，map为空时返回fail
	 * @param json
	 * @param map 
	 * @return 成功返回更新后的jsonObj，失败返回fail
	 */
	public static String updateKey(String json,Map<String, Object> map){
		if(!StringValidateUtil.isNotEmpty(json)){
			return "fail";
		}
		if(!ListUtil.isNotEmpty(map)){
			return "fail";
		}
		JSONObject jsonObject = JSONObject.fromObject(json);
		for(String key : map.keySet()){
			//jsonObj中包含该键，且该键的值非空，则更新jsonObj
			if(jsonObject.containsKey(key)&&StringValidateUtil.isNotEmpty(String.valueOf(map.get(key)))){
				jsonObject.put(key, map.get(key));
			}
		}
		return jsonObject.toString();
	}
	
	/**
	 * 合并两个json串<br>
	 * 注：当两个json串中含有相同的键时，以json1为准
	 * @param json1
	 * @param json2
	 * @return json3   json1或json2为空时返回null
	 */
	public static String mergeJSON(String json1, String json2){
		if(!StringValidateUtil.isNotEmpty(json1)||!StringValidateUtil.isNotEmpty(json2)){
			return null;
		}
		JSONObject jsonObject1 = JSONObject.fromObject(json1);
		JSONObject jsonObject2 = JSONObject.fromObject(json2);
		for (Object key : jsonObject2.keySet()) {
			//当json1中没有该键时，才将json2中的该键值对放入json中
			if(!jsonObject1.containsKey(key)){
				jsonObject1.put(key, jsonObject2.get(key));
			}
		}
		return jsonObject1.toString();
	}
}