package com.wh.kaifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class WangzhanApplication {

	public static void main(String[] args) {
		SpringApplication.run(WangzhanApplication.class, args);
		System.out.println("success");
	}

}
