package com.example.demo;

import com.example.beans.Employee;
import com.example.beans.PCSetup;
import com.example.beans.SingletonObj;
import com.example.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(scanBasePackages = "com", exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		SingletonObj singletonObj1 = context.getBean(SingletonObj.class);
		SingletonObj singletonObj2 = context.getBean(SingletonObj.class);

		System.out.println(singletonObj1 == singletonObj2);
	}

}
