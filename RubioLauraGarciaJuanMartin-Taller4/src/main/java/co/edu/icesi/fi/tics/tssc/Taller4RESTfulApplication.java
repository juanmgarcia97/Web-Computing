package co.edu.icesi.fi.tics.tssc;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminService;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@SpringBootApplication
public class Taller4RESTfulApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(Taller4RESTfulApplication.class, args);
	}

	@Bean
	public CommandLineRunner command(TsscAdminRepository adminRepository, TsscTopicService topicService, TsscGameService gameService) {
		return (args) -> {
			TsscAdmin admin1 = new TsscAdmin();
			admin1.setUsername("user1");
			admin1.setPassword("{noop}123");
			admin1.setSuperAdmin("superAdmin");  
			adminRepository.save(admin1); 
			
			TsscAdmin admin2 = new TsscAdmin();
			admin2.setUsername("user2");
			admin2.setPassword("{noop}123");
			admin2.setSuperAdmin("admin");
			adminRepository.save(admin2);
			
			TsscTopic t1 = new TsscTopic();
			t1.setDefaultGroups(1);
			t1.setDefaultSprints(1);
			t1.setDescription("sdcs");
			t1.setGroupPrefix("dcscd");
			topicService.saveTopic(t1);
			
			TsscGame g1 = new TsscGame();
			g1.setAdminPassword("123");
			g1.setGuestPassword("123");
			g1.setName("jueguito1");
			g1.setNGroups(4);
			g1.setNSprints(4);
			g1.setScheduledDate(LocalDate.now());
			g1.setScheduledTime(LocalTime.now());
			g1.setTsscTopic(t1);
			gameService.saveGame(g1);
		};
	}

}
