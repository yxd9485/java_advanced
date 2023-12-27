package com.example.multithreading_all;



import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * Start Class
 * @author yangxiaodong
 */
@SpringBootApplication
@EnableAsync
@ServletComponentScan
@NacosPropertySource(dataId = "LEARN_DEV",groupId = "DEFAULT_GROUP",autoRefreshed = true)
public class MultithreadingAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadingAllApplication.class, args);
	}

}
