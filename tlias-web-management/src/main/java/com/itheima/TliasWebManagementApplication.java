package com.itheima;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


//@ServletComponentScan  //要想使用传统的servlet，必须加此注解，开启了Springboot对servlet的支持，就会自动扫描@WebServlet注解
@SpringBootApplication

public class TliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

}
