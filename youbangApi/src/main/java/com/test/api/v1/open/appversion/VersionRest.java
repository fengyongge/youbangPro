package com.test.api.v1.open.appversion;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

import com.sun.jersey.api.spring.Autowire;
import com.test.common.version.model.Version;
import com.test.common.version.service.VersionService;
import com.test.utils.ClientConstant;
import com.test.utils.JsonMapper;
import com.test.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 *
 * @author fengyongge
 * @date 2017年9月14日 上午11:23:40
 */

@Autowire
@Path("appVersionService")
public class VersionRest {

    @Autowired
    VersionService versionService;

    @GET
    @Path("/androidVersion")
    @Produces(MediaType.APPLICATION_JSON)
    public String androidVersion(){

       Version version =  versionService.select();

        if(version==null){
            return Response.info().setCode(ClientConstant.APP_VERSION_CODE).setMsg(ClientConstant.APP_VERSION_FAIL_MSG).setData(ClientConstant.FAIL).toJSON();
        }

        String jsonString = JsonMapper.toJson(version);

        return Response.info().setCode(ClientConstant.QUERY_SUCCESS).setMsg(ClientConstant.APP_VERSION_SUCCESS_MSG).setData(jsonString).toJSON();

    }


//    @GET
//    @Path("/iosVersion")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String iosVersion(){
//        Map<String, String> map = new HashMap<>();
//        map.put("versionCode", versionCode);
//        map.put("updateContent", updateContent);
//        map.put("IOSAddress", IOSAddress);
//        String jsonVersion = JsonMapper.toJson(map);
//        return Response.info().setCode(ClientConstant.QUERY_SUCCESS).setMsg(ClientConstant.APP_VERSION_SUCCESS_MSG).setData(jsonVersion).toJSON();
//    }
}

