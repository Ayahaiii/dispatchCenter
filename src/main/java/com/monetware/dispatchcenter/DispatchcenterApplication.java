package com.monetware.dispatchcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.monetware.dispatchcenter.business.dao.*")
public class DispatchcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchcenterApplication.class, args);
	}

}
