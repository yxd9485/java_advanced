package com.example.multithreading_all;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * Start Class
 * @author yangxiaodong
 */
@SpringBootApplication
@EnableAsync
public class MultithreadingAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultithreadingAllApplication.class, args);
	}

}
