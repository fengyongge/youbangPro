//package com.test.system.autho.sign;
//
//import java.security.MessageDigest;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//
//public class Access {
//
//
//	//generate signature 签名
//    public static String getSignature( AccessBean accessBean ) {
//
//
//    	String appSecret = accessBean.getAppSecret();
//
//    	Map<String, String> paramMap = accessBean.getParams();
//
//		try {
//			if (paramMap == null) {
//				return "";
//			}
//			StringBuilder combineString = new StringBuilder();
//			combineString.append(appSecret);
//			Set<Entry<String, String>> entrySet = paramMap.entrySet();
//			String [] strArray = new String [entrySet.size()];
//			int i = 0;
//			for(Entry<String, String> entry: entrySet){
//				strArray[i] = entry.getKey();
//				i++;
//			}
////			Collections.sort(list,mc);
//			Arrays.sort(strArray);
//			for(String s: strArray){
//			for (Entry<String, String> entry: entrySet){
//			if(s.equals(entry.getKey()))
//			combineString.append(entry.getKey() + entry.getValue());
//			}
//			}
//			combineString.append(appSecret);
//			byte[] bytesOfMessage = combineString.toString().getBytes("UTF-8");
////			System.out.println( combineString );
//			MessageDigest md = MessageDigest.getInstance("SHA1");
//			byte[] thedigest = md.digest(bytesOfMessage);
//			String signature = bytesToHexString(thedigest);
//			return signature;
//		} catch (Exception e) {
//			return "";
//		}
//    }
//
//    //聚包盛签名  最后需要转成大写
//    public static String signature(String appSecret, Map<String, String> paramMap) {
//		try {
//			if (paramMap == null) {
//				return "";
//			}
//			StringBuilder combineString = new StringBuilder();
//			combineString.append(appSecret);
//			Set<Entry<String, String>> entrySet = paramMap.entrySet();
//			String [] strArray = new String [entrySet.size()];
//			int i = 0;
//			for(Entry<String, String> entry: entrySet){
//				strArray[i] = entry.getKey();
//				i++;
//			}
////			Collections.sort(list,mc);
//			Arrays.sort(strArray);
//			for(String s: strArray){
//			for (Entry<String, String> entry: entrySet){
//			if(s.equals(entry.getKey()))
//			combineString.append(entry.getKey() + entry.getValue());
//			}
//			}
//			combineString.append(appSecret);
//			byte[] bytesOfMessage = combineString.toString().getBytes("UTF-8");
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] thedigest = md.digest(bytesOfMessage);
//			String signature = bytesToHexString(thedigest);
//			return signature.toUpperCase();
//		} catch (Exception e) {
//			return "";
//		}
//    }
//
//    public static String[] sort(String[]strs){
//
//
//	 for (int i = 0; i < strs.length-1; i++) {
//	   for (int j =i+1; j < strs.length; j++) {
//	    int intTemp = strs[i].compareToIgnoreCase(strs[j]);
//	    String strTemp = "";
//	    if(intTemp>0){
//	     strTemp = strs[j];
//	     strs[j] = strs[i];
//	     strs[i] = strTemp;
//	    }
//	   }
//
//    }
//	 return strs;
//    }
//
//    public static String bytesToHexString(byte[] src) {
//		try {
//			StringBuilder stringBuilder = new StringBuilder("");
//			if (src == null || src.length <= 0) {
//				return null;
//			}
//			for (int i = 0; i < src.length; i++) {
//				int v = src[i] & 0xFF;
//				String hv = Integer.toHexString(v);
//				if (hv.length() < 2) {
//					stringBuilder.append(0);
//				}
//				stringBuilder.append(hv);
//			}
//			return stringBuilder.toString();
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//}
