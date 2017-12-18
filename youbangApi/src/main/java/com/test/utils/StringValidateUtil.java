package com.test.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


import net.sf.json.JSONObject;


public class StringValidateUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return 为空返回false,非空返回true
	 */
	public static boolean isNotEmpty(String str){
		if(null!=str&&!str.equals("")&&!str.equals("null")){
			return true;
		}
		return false;
	}
	
	/**
	 * 过滤null字符串
	 * @param str
	 * @return str
	 */
	public static String changeObjectString(Object obj){
		String str = "";
		if ( null != obj ){
			str = obj.toString();
		}
		return str;
	}
	
	/**
	 * 判断是否为空
	 * @param obj
	 * @return 为空返回true,非空返回false(主要针对json串，获取到的字段为Object情况)
	 */
	public static boolean isEmptyString(Object obj){
		if(null==obj||(obj+"").equals("")||obj.equals("null")){
			return true;
		}
		return false;
	}
	
	/** 
     * 将emoji表情替换成* 
     *  
     * @param source 
     * @return 过滤后的字符串 
     */  
    public static String filterEmoji(String source) {  
        if( isNotEmpty(source) ){  
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");  
        }else{  
            return source;  
        }  
    }  
    
    /**
     * 判断字符串是否是空 是空 返回ture
     * @param number
     * @return
     */
    public static Boolean isNullString(Object number) {
        if(number==null|| StringUtils.isEmpty(number.toString()))
            return  true;
        return  false;
    }
    /**
     * 判断字符串是否是空 如果空 返回0
     * @param number
     * @return
     */
    public static String isNullAndInit0(String number) {
        if(StringUtils.isEmpty(number.toString()))
            return "0";
        return  number;
    }
    /**
     * 判断字符串是否是空 如果空 返回null
     * @param number
     * @return
     */
    public static String isNullAndInitString(Object number) {
        if(number==null||StringUtils.isEmpty(number.toString()))
            return null;
        return  number.toString();
    }
    
    /**  
     * 验证一个字符串是否是Double类型  
     *   
     * @param s 要验证的字符串  
     * @return 如果是Double类型则返回true,否则返回false  
     */   
    public static boolean isDouble(String s) {    
        if (s == null || s.equals(""))    
            return false;    
        String num = "0123456789.";    
        if (s.indexOf('.') >= 0)    
            if (s.indexOf('.', s.indexOf('.') + 1) > 0)    
                return false;    
        for (int i = 0; i < s.length(); i++) {    
            if (num.indexOf(s.charAt(i)) < 0) {    
                return false;    
            } else {    
                try {    
                    Double.parseDouble(s);    
                } catch (NumberFormatException e) {    
                    return false;    
                }    
            }    
        }    
        return true;    
    }
    
    /**  
     * 验证一个字符串是否是Float类型  
     *   
     * @param s 要验证的字符串  
     * @return 如果是Float类型则返回true,否则返回false  
     */   
    public static boolean isFloat(String s) {    
        if (s == null || s.equals(""))    
            return false;    
        String num = "0123456789.";    
        if (s.indexOf('.') >= 0)    
            if (s.indexOf('.', s.indexOf('.') + 1) > 0)    
                return false;    
        for (int i = 0; i < s.length(); i++) {    
            if (num.indexOf(s.charAt(i)) < 0) {    
                return false;    
            } else {    
                try {    
                    Float.parseFloat(s);    
                } catch (NumberFormatException e) {    
                    return false;    
                }    
            }    
        }    
        return true;    
    } 
    
    /**  
     * 验证一个字符串是否是整形  
     *   
     * @param s 要验证的字符串  
     * @return 如果是整形则返回true,否则返回false  
     */   
    public static boolean isInteger(String s) {    
        if (s == null || s.length() == 0) {    
            return false;    
        } else {    
            String str = "0123456789";    
            String num = "-0123456789";    
            if (num.indexOf(s.charAt(0)) < 0)    
                return false;    
            for (int i = 1; i < s.length(); i++) {    
                if (str.indexOf(s.charAt(i)) < 0) {    
                    return false;    
                } else {    
                    try {    
                        Integer.parseInt(s);    
                    } catch (NumberFormatException e) {    
                        return false;    
                    }    
                }    
            }    
        }    
        return true;    
    }
    
    /**  
     * 验证一个字符串是否一个合法日期,日期格式：yyyy-MM-dd  
     *   
     * @param date 要验证的字符串日期  
     * @return 如果字符串是一个合法的日期返回true,否则返回false  
     */   
    public static boolean isDate(String date) {    
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");    
        try {    
            df.setLenient(false);    
            df.parse(date);    
            return true;    
        } catch (ParseException e) {    
            return false;    
        }    
    }
    
    /**  
     * 验证一个字符串是否是数字组成  
     *   
     * @param s 要验证的字符串  
     * @return 如果字符串是数字组成的则返回true,否则返回false  
     */   
    public static boolean isNumber(String s) {    
        if (s == null || s.equals(""))    
            return false;    
        String num = "0123456789";    
        for (int i = 0; i < s.length(); i++) {    
            if (num.indexOf(s.charAt(i)) < 0)    
                return false;    
        }    
        return true;    
    }
    
    /**
     * 生成32位随机字符串（由小写字母和数字组成）
     * @return
     */
    public static String randomString(){
    	String baseString = "abcdefghigklmnopqrstuvwxvz0123456789";
    	Random random = new Random();
    	StringBuffer result = new StringBuffer();
    	for (int i = 0; i < baseString.length(); i++) {
			if(result.length()<32){
				result.append(baseString.charAt(random.nextInt(baseString.length())));
			}
		}
		return result.toString();
    }
    
    /**
     * 去除json字符串中不符合json格式的字符
     * @param json
     * @return
     */
    public static String verifyJson(String json){
    	StringBuffer jsonBf = new StringBuffer(json);
    	for (int i = 0; i < jsonBf.length()-1; i++) {
    		//以后有其他需要去除的字符都在这里做if判断删除就可以，记得加continue;
			if(String.valueOf(jsonBf.charAt(i)).equals("\"")&&String.valueOf(jsonBf.charAt(i+1)).equals("{")){
				jsonBf.deleteCharAt(i);
				continue;
			}
			if(String.valueOf(jsonBf.charAt(i)).equals("}")&&String.valueOf(jsonBf.charAt(i+1)).equals("\"")){
				jsonBf.deleteCharAt(i+1);
				continue;
			}
			if(String.valueOf(jsonBf.charAt(i)).equals("\"")&&String.valueOf(jsonBf.charAt(i+1)).equals("[")){
				jsonBf.deleteCharAt(i);
				continue;
			}
			if(String.valueOf(jsonBf.charAt(i)).equals("]")&&String.valueOf(jsonBf.charAt(i+1)).equals("\"")){
				jsonBf.deleteCharAt(i+1);
				continue;
			}
			if(String.valueOf(jsonBf.charAt(i)).equals("\\")&&String.valueOf(jsonBf.charAt(i+1)).equals("\"")){
				jsonBf.deleteCharAt(i);
				continue;
			}
		}
//    	json = json.replace("\"{", "{");
//        json = json.replace("}\"", "}");
//        json = json.replace("\"[", "[");
//        json = json.replace("]\"", "]");
//        json = json.replace("\\\"", "\"");
		return jsonBf.toString();
    }
    /**
	 * 
	 * description:过滤xml字符串中不合法的字符
	 *
	 * @author jiachenghao	
	 * @param data xml格式的字符串
	 * @return
	 */
	public static String checkXmlChar(String data) {  
        StringBuffer appender = new StringBuffer("");  
          
        if (StringValidateUtil.isNotEmpty(data)) {  
            appender = new StringBuffer(data.length());  
              
            for (int i = 0; i < data.length(); i++) {  
                char ch = data.charAt(i);  
                if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD)  
                        || ((ch >= 0x20) && (ch <= 0xD7FF))  
                        || ((ch >= 0xE000) && (ch <= 0xFFFD))  
                        || ((ch >= 0x10000) && (ch <= 0x10FFFF)))  
                    appender.append(ch);  
            }  
        }  
          
        String result = appender.toString();  
          
        return result.replaceAll("]]>", "");  
    }
	
	/** 
     * 转换一个xml格式的字符串到json格式 
     *  
     * @param xml 
     *            xml格式的字符串 
     * @return 成功返回json 格式的字符串;失败反回null 
     */  
    public static  String xmlToJSON(String xml) {  
        JSONObject obj = new JSONObject();  
        try {  
            InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));  
            SAXBuilder sb = new SAXBuilder();  
            Document doc = sb.build(is);  
            Element root = doc.getRootElement();  
            obj.put(root.getName(), iterateElement(root));  
            return obj.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    } 
    
    /** 
     * 一个迭代方法 
     *  
     * @param element 
     *            : org.jdom.Element 
     * @return java.util.Map 实例 
     */  
    @SuppressWarnings("unchecked")  
    private static Map  iterateElement(Element element) {  
        List jiedian = element.getChildren();  
        Element et = null;  
        Map obj = new HashMap();  
        List list = null;  
        for (int i = 0; i < jiedian.size(); i++) {  
            list = new LinkedList();  
            et = (Element) jiedian.get(i);  
            if (et.getTextTrim().equals("")) {  
                if (et.getChildren().size() == 0)  
                    continue;  
                if (obj.containsKey(et.getName())) {  
                    list = (List) obj.get(et.getName());  
                }
                list.add(iterateElement(et));  
                obj.put(et.getName(), list);
            } else {  
                if (obj.containsKey(et.getName())) {  
                    list = (List) obj.get(et.getName());  
                    list.add(et.getTextTrim());  
                    obj.put(et.getName(), list);  
                } else {
                	obj.put(et.getName(), et.getTextTrim()); 
                } 
            }  
        }  
        return obj;  
    } 
    //java 过滤字符串
    public static String FilterStr(String str) throws PatternSyntaxException
    {
        /**
         * 特殊字符
         */
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？_]";
         
        /**
         * Pattern p = Pattern.compile("a*b");
         * Matcher m = p.matcher("aaaaab");
         * boolean b = m.matches();
         */
        Pattern pat = Pattern.compile(regEx);     
        Matcher mat = pat.matcher(str);
         
        /**
         * 返回替换结果
         */
        return mat.replaceAll("").trim();  
    }
    //测试demo
    public static void main(String[] args) 
    {
        /**
         * 测试字符串
         */
        String totalStr = "~`<>?^&*()you@##%$$#^%^h^&&*&*()<>?ai@#@$~~`_+|dong?><:";
        /**
         * 打印测试字符串
         */
        System.out.println("打印测试字符串:" + totalStr);
         
        /**
         * 调用过滤字符串的方法
         */
        String filterStr = FilterStr(totalStr);
        /**
         * 打印过滤字符串
         */
        System.out.println("打印过滤字符串:" + filterStr);
    }
}

