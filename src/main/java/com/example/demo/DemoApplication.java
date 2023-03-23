package com.example.demo;

import com.example.beans.PCSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@SpringBootApplication(scanBasePackages = "com", exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.example.database", "com.example.demo", "com.example.beans"})
@EntityScan("com.example.database")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		String path = "/Users/vitaly/Desktop/Java projects/FirstSpringProj/src/main/resources/account-bean-config.xml";

		ApplicationContext context = new FileSystemXmlApplicationContext(path);
		PCSetup setup = context.getBean(PCSetup.class);
		System.out.println(setup);
	}

}
