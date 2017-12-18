package com.zzti.youbang.encrypt;



import java.util.Iterator;
import java.util.Map;

/**
 * @author fengyongge
 * @Description  通过参数排序加密获得 sign签名
 * @date 2017/6/26 0026 上午 9:55
 */

public class EncryptionRule {

    // 给参数按字母顺序排序(头尾加参数)

//    public final static String sort(Map<String, String> map) {
//        Iterator<String> iter = map.keySet().iterator();
//        String s="";
//        while (iter.hasNext()) {
//            Object key = iter.next();
//            StringBuffer sb = new StringBuffer();
//            s += sb.append(key + map.get(key));
//        }
////        LogUtil.i("签名之前"+s);
//        return s;
//    }
//
//
//
//    public static String toMD5(String or_Sign) {
//        String sign = null;
//        or_Sign = AppConfig.APPSECRET+or_Sign+AppConfig.APPSECRET;
////        LogUtil.i("-加密前-"+or_Sign);
//        sign = Sha1.shaEncrypt(or_Sign);
//        return sign;
//    }

}
