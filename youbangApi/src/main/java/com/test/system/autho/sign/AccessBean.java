//package com.test.system.autho.sign;
//
//import com.sdc.common.application.service.CoreApplicationService;
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
//* description:
//*
//*
//* @author today zhaojintian@ediankai.com
//* @date 2017年3月11日
//* @Company: ediankai
//*/
//@Component
//public class AccessBean {
//
//	//签名
//	private String sign ;
//	//公钥
//	private String publicKey ;
//	//参数
//	private Map<String, String> params;
//	//时间戳
//	private Long timestamp;
//	//时间戳
//	private Long suppliers;
//	//秘钥
//	private String appSecret ;
//	//允许的请求类型
//	@Value("${const.JerseyRequestTypeAllow}")
//	private String jerseyRequestTypeAllow;
//
//	@Autowired
//    CoreApplicationService coreApplicationService;
//
//	/**
//	 * @return the sign
//	 */
//	public String getSign() {
//		return sign;
//	}
//	/**
//	 * @param sign the sign to set
//	 */
//	public void setSign(String sign) {
//		this.sign = sign;
//	}
//	/**
//	 * @return the publicKey
//	 */
//	public String getPublicKey() {
//		return publicKey;
//	}
//	/**
//	 * @param publicKey the publicKey to set
//	 */
//	public void setPublicKey(String publicKey) {
//		this.publicKey = publicKey;
//	}
//
//	/**
//	 * @param appSecret the appSecret to set
//	 */
//	public void setAppSecret( ) {
//
////		System.out.println(this.getPublicKey() + "*****************" + this.getSuppliers());
//
//		String appSecret = "";
//		String appSecretJson = "";
//		//判断正常调用
//		if( this.getPublicKey() != null && !this.getPublicKey().trim().equals("") ){
//
//			appSecretJson = coreApplicationService.getSecret( this.getPublicKey(),this.getSuppliers() );
////			System.out.println( appSecretJson );
//			//判断是否admin端调用
//			if( appSecretJson == null || appSecretJson.length() <= 0 )
//				appSecretJson = coreApplicationService.getSecret( this.getPublicKey(),0l );
//
//		}
//
//		if( appSecretJson != null && appSecretJson.length() > 0 ){
//			JSONObject json = JSONObject.fromObject( appSecretJson );
//			appSecret = json.getString("secret");
//		}
//
//		this.appSecret = appSecret;
//	}
//	/**
//	 * @return the appSecret
//	 */
//	public String getAppSecret() {
//
//		return appSecret;
//	}
//
//	/**
//	 * @return the jerseyRequestTypeAllow
//	 */
//	public String[] getJerseyRequestTypeAllow() {
//		String[] arr = jerseyRequestTypeAllow.split(",");
//		return arr;
//	}
//	/**
//	 * @return the params
//	 */
//	public Map<String, String> getParams() {
//		return params;
//	}
//	/**
//	 * @param params the params to set
//	 */
//	public void setParams(Map<String, String> params) {
//		this.params = params;
//	}
//	/**
//	 * @return the timestamp
//	 */
//	public Long getTimestamp() {
//		return timestamp;
//	}
//	/**
//	 * @param timestamp the timestamp to set
//	 */
//	public void setTimestamp(Long timestamp) {
//		this.timestamp = timestamp;
//	}
//	/**
//	 * @return the suppliers
//	 */
//	public Long getSuppliers() {
//		return suppliers;
//	}
//	/**
//	 * @param suppliers the suppliers to set
//	 */
//	public void setSuppliers(Long suppliers) {
//		this.suppliers = suppliers;
//	}
//
//
//
//}
