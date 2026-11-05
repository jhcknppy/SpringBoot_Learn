package com.ppy.springioc.config;


import com.ppy.springioc.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DogConfig {
    @Bean("小黑")
    public Dog getDog() {
        return new Dog();
    }
}
