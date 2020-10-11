package is.hi.g.hikersicelands.hikersicelands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HikersicelandsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HikersicelandsApplication.class, args);
	}

}
