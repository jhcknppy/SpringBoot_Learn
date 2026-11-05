package com.ppy.springioc;

import ch.qos.logback.core.CoreConstants;
import com.ppy.springioc.bean.Dog;
import com.ppy.springioc.bean.Person;
import com.ppy.springioc.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 这个是主入口类，称为主程序类
 */

@SpringBootApplication
public class Spring01IocApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Spring01IocApplication.class, args);
        System.out.println("===============ioc====================");
        UserController bean = ioc.getBean(UserController.class);
        System.out.println(bean);

        CoreConstants bean1 = ioc.getBean(CoreConstants.class);
        System.out.println(bean1);
    }

    /**
     * 容器启动过程中就会创建组件的对象
     */
    public static void test02(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Spring01IocApplication.class, args);
        System.out.println("===============ioc====================");
        Dog bean = ioc.getBean(Dog.class);
        System.out.println(bean);
        Dog bean1 = ioc.getBean(Dog.class);
        System.out.println(bean1);
        Dog bean2 = ioc.getBean(Dog.class);
        System.out.println(bean2);

        Person zs = ioc.getBean("zhangsan",Person.class);
        System.out.println(zs);

    }


    // Test01
    public static void Test01BeanAnnotation(String[] args) {
        // 1、跑起一个spring的应用，Application:Spring 应用上下文对象：Ioc容器
//        SpringApplication.run(Spring01IocApplication.class, args);
        ConfigurableApplicationContext ioc = SpringApplication.run(Spring01IocApplication.class, args);
        System.out.println("ioc=" + ioc);

        System.out.println("============================");
        //2、获取到容器中所有组件的名字；容器中装了哪些组件；Spring启动会有很多默认组件
        String[] names = ioc.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name=" + name);
        }

        // 4、获取容器中的组件对象
        // 组件的四大特性：（名字，类型），对象，作用域
        // 组件名字，全局唯一；组件名重复了，一定只会给容器中放一个最先声明的


        // 小结：
        // 从容器中获取组件
        // 1)   组件不存在，抛异常：NoSuchBeanDefinitionException
        // 2)   组件不唯一：
        // 按照类型只要一个，抛异常：NoUniqueBeanDefinitionException
        // 按照名字只要一个：可以精确获取
        // 多个：返回所有组件的List集合
        // 3)   组件唯一存在，正确返回

        // 4.1 按照组件的名字获取对象
        Object zhangsan = ioc.getBean("zhangsan");
        System.out.println("对象=" + zhangsan);

        // 4.2 按照组件的类型获取对象

        Map<String, Person> type = ioc.getBeansOfType(Person.class);
        System.out.println("type=" + type);

        // 4.3 类型+名字
        Person lisi = ioc.getBean("lisi", Person.class);
        System.out.println("lisi=" + lisi);


    }

    // 3、给容器中注册一个自己的组件，容器中每个组件都有自己的名字，可以修改；方法名就是组件的名字
}
