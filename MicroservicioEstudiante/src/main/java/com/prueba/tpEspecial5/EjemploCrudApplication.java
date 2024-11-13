package com.prueba.tpEspecial5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.prueba.tpEspecial5.models")
public class EjemploCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjemploCrudApplication.class, args);
	}

}
