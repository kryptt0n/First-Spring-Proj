package com.example.config;

import com.example.beans.Keyboard;
import com.example.beans.Monitor;
import com.example.demo.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.beans")
public class AppConfiguration {

    @Bean
    public Keyboard keyboard() {
        return new Keyboard("Razer", 10000);
    }

    @Bean
    public Monitor monitor() {
        return new Monitor("Asus", "1920x1080");
    }
}
