package pt.bitclinic.javaaccommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JavaAccommerceApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder pwEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JavaAccommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("ENCODE: " + pwEncoder.encode("123456"));
		
		System.out.println("TEST: " + pwEncoder.matches("123456", "$2a$10$cVQ/EvgsGLkinPbL9nrBuuM3BZiOW1YFGoESuVT1O7N2Vik4ac5QW"));
		
	}

}
