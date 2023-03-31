package com.example.config;

import com.example.beans.*;
import com.example.database.Food;
import com.example.demo.TaskRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@ComponentScan({"com.example.beans", "com.example.database"})
public class AppConfiguration {

    @Bean
    public Keyboard keyboard() {
        return new Keyboard("Razer", 10000);
    }

    @Bean
    public Monitor monitor() {
        return new Monitor("Asus", "1920x1080");
    }

    @Bean
    public Address address() {
        return new Address("Pushkina street", 22);
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure()
                .addPackage("com.example.database")
                .buildSessionFactory();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SingletonObj singletonObj() {
        return new SingletonObj("name", 100);
    }
    @Bean
    @RequestScope
    public MessageGeneratorRequest requestScope() {
        return new MessageGeneratorRequest("hello world!");
    }

    @Bean
    @SessionScope
    public MessageGeneratorSession sessionScope() {
        return new MessageGeneratorSession("hello world!");
    }
}
