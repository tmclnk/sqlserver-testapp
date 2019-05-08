package gov.ne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... strings) throws Exception {
		log.info("Checking username");
		jdbcTemplate.query("SELECT USER_NAME() u", rs -> {
			while (rs.next()) {
				String currentUser = rs.getString("u");
				log.info("USER_NAME is {}", currentUser);
			}
			return null;
		});
	}
}