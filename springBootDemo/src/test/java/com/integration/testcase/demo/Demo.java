package com.integration.testcase.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eduoinfo.controller.dataservice.ExpressQuery;
import com.integration.IntegrationBase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by 35815 on 2018/6/21.
 */
@Log4j2
public class Demo extends IntegrationBase {

    @Autowired
    private ExpressQuery expressQuery;

    @Test(dataProvider="num",description = "查询订单")
    public void demoNormal(final String caseId,final String isRun,
                           final String description, final String url,final String parameter){

        //从csv文件设置是否运行用例
        if (isRun.equalsIgnoreCase("N")){
            log.info("[--------  "+"caseId: "+caseId+", description："+description + "，is not run --------]" );
            return;
        }

        //http请求
        String result = expressQuery.queryExpressInfo();

        //返回结果
        JSONObject jsonObject= JSON.parseObject(result);

        //打印结果
        log.info(JSONObject.toJSONString(jsonObject,true));

        //验证结果
        Assert.assertEquals(jsonObject.getString("message"),"ok","验证返回信息是否成功");
        Assert.assertEquals(jsonObject.getString("nu"),"668806588673","验证订单号是否正确");
    }


    @Test
    public void demo(){
        System.out.println("aaa555");

    }
}
