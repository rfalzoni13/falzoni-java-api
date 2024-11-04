package br.com.falzoni.falzoni_java_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("br.com.falzoni.falzoni_java_api.repositories")
@EntityScan("br.com.falzoni.falzoni_java_api.domain.entities")
@SpringBootApplication
public class FalzoniJavaApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FalzoniJavaApiApplication.class, args);
	}

}
