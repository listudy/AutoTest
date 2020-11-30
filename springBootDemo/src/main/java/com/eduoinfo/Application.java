package com.eduoinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;


@ComponentScan("com.eduoinfo.*")
@SpringBootApplication
@EnableScheduling
public class Application {

    private  static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        try{
            Application.context = SpringApplication.run(Application.class,args);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }

}
