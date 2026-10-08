package br.edu.ifsp.prw3.proj_mecanica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ProjMecanicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjMecanicaApplication.class, args);
	}

}
