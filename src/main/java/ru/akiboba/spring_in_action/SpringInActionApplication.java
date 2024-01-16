package ru.akiboba.spring_in_action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.out;

@SpringBootApplication
public class SpringInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringInActionApplication.class, args);
        out.println("For app go to http://localhost:8080/");
        out.println("For docs go to http://localhost:8080/swagger-ui/index.html");
    }

}
