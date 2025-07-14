package com.jsatomi.ChallengeLiteratura;

import com.jsatomi.ChallengeLiteratura.principal.Principal;
import com.jsatomi.ChallengeLiteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
	SpringApplication.run(ChallengeLiteraturaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Hola Mundo! con GitHub");
		Principal principal = new Principal(libroRepository);
		principal.mostrarMenu();
	}
}
