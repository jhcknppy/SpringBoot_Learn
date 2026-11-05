package com.ppy.springioc.config;

import com.ppy.springioc.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//告诉Spring，这是一个配置类

@Configuration
public class PersonConfig {

    @Bean("zhangsan")
    public Person getPerson() {
        Person person = new Person();
        person.setName("张三");
        person.setGender("男");
        person.setAge(20);
        return person;
    }

    @Bean("lisi")
    public Person getPerson1() {
        Person person = new Person();
        person.setName("李四");
        person.setGender("男");
        person.setAge(21);
        return person;
    }

}
