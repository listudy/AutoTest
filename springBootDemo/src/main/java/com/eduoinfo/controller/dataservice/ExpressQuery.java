package com.eduoinfo.controller.dataservice;

import com.eduoinfo.utils.http.HttpClientManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Api(value = "/",description = "查询快递信息")
@Service
public class ExpressQuery {

    @Autowired
    private HttpClientManager httpClientManager;

    @RequestMapping(value = "/query",method= RequestMethod.GET)
    @ApiOperation(value = "查询申通快读信息",httpMethod = "GET")
    public String queryExpressInfo(){

        String url = "http://www.kuaidi100.com";
        String message = "/query?type=tiantian&postid=668806588673";

        String result =  httpClientManager.doGet(url,message);

        return result;
    }
}
