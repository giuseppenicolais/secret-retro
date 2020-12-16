package com.example.secret_retro;

import com.example.secret_retro.model.MainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MainConfig.class)
@SpringBootApplication
public class SecretRetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretRetroApplication.class, args);
	}
}
