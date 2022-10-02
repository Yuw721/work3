package com.gientech.project;

import com.sun.xml.internal.ws.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig //启动apollo配置注解
public class ProjectApplication {

    @ApolloConfig("appnamespace") //指定具体的namespace
    private static Config configSwitch;

    public static void main(String[] args) {
        //获取方式一
        Config appConfig = ConfigService.getAppConfig(); //默认的namespace是application
        String password = appConfig.getProperty("password", "");
        log.info("{}==：" + password, "密码");
        //获取方式二  直接指定namespace
        Config appnamespace = ConfigService.getConfig("appnamespace");
        String unionpayflag1 = appnamespace.getProperty("unionpayflag", "");
        log.info("{}==：" + unionpayflag1, "开关1");
        //获取方式三
        String unionpayflag = getPassword("unionpayflag", "开关");
        log.info("{}==：" + unionpayflag, "开关2");
        SpringApplication.run(ProjectApplication.class, args);
    }

    public static String getPassword(String key, String param){
        //获取指定namespace下的key对应的值value
        String property = configSwitch.getProperty(key, "");
        //为空默认值
        if (!StringUtils.hasLength(property)){
            property = "0";
        }
//        log.info("{}====：" + property, param);
        return property;
    }

}
