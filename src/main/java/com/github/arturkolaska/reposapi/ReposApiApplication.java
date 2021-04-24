package com.github.arturkolaska.reposapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ReposApiApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ReposApiApplication.class, args);

        var dispatcherServlet = (DispatcherServlet)app.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}
