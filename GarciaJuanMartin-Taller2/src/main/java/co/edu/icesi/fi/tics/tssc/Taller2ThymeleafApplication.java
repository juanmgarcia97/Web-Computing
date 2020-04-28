package co.edu.icesi.fi.tics.tssc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminService;

@SpringBootApplication
public class Taller2ThymeleafApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(Taller2ThymeleafApplication.class, args);
	}

	@Bean
	public CommandLineRunner command(TsscAdminRepository adminRepository) {
		return (args) -> {
			TsscAdmin admin1 = new TsscAdmin();
			admin1.setUsername("user1");
			admin1.setPassword("{noop}123");
			admin1.setSuperAdmin("superAdmin");  
			adminRepository.save(admin1); 
//			TsscAdmin finded = adminRepository.findByUsername(admin1.getUsername());
//			System.out.println(finded.getUsername() + " " + finded.getPassword());
			TsscAdmin admin2 = new TsscAdmin();
			admin2.setUsername("user2");
			admin2.setPassword("{noop}123");
			admin2.setSuperAdmin("admin");
			adminRepository.save(admin2);
		};
	}

}
