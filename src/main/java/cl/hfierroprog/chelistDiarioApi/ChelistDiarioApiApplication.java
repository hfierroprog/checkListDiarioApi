package cl.hfierroprog.chelistDiarioApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChelistDiarioApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ChelistDiarioApiApplication.class, args);
	}

	@GetMapping("/health")
	public ResponseEntity<Boolean> health() {
			return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
	}
}
