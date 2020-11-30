package com.integration;


import com.eduoinfo.Application;
import com.eduoinfo.utils.csvutil.CSVData;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by 35815 on 2018/6/21.
 */

@SpringBootTest(classes = Application.class)
@Log4j2
public class IntegrationBase extends AbstractTestNGSpringContextTests   {

    @DataProvider(name="num")
    public Iterator<Object[]> numbers(Method method) throws IOException {
        if (method==null){
            return null;
        }else {
            return numbers(method.getDeclaringClass(),method);
        }
    }

    public Iterator<Object[]> numbers(Class<?> cls,Method method) throws IOException {
        String filName =cls.getSimpleName()+"."+method.getName()+".csv";
        log.info("运行 CSV 文件: " + filName);

        String pack = cls.getPackage().toString().split("com.integration.testcase.")[1].trim();
        String packPath = pack.replace(".","/");

        log.info("运行 CSV 文件路劲: " + packPath+filName);
        return (Iterator<Object[]>)new CSVData(packPath+"/"+filName,".src.test.resources.");

    }

}
