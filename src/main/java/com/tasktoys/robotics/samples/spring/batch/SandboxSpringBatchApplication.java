package com.tasktoys.robotics.samples.spring.batch;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SandboxSpringBatchApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SandboxSpringBatchApplication.class);
	
	public static void main(String[] args) {
		
		Arrays.stream(args).forEach(el -> LOGGER.info("args" + el));
		
//		SpringApplication app = new SpringApplication(SandboxSpringBatchApplication.class);
//		app.setWebEnvironment(false);// 処理完了後に終了するようにする
//		app.run(args);
		ApplicationContext context = SpringApplication.run(SandboxSpringBatchApplication.class,args);

	}
}
