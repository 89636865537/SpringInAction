package ru.bank.organization.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.bank.organization.config.JdbcConfig;
import ru.bank.organization.config.properties.CommonDbProperties;

@SpringBootApplication
@ComponentScan(basePackageClasses = {JdbcConfig.class})
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
//        Arrays.stream(applicationContext.getBeanDefinitionNames()).sorted(String::compareTo).forEach(System.out::println);
    }
}
