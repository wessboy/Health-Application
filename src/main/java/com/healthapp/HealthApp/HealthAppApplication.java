package com.healthapp.HealthApp;

import com.healthapp.HealthApp.security.entities.AppRole;
import com.healthapp.HealthApp.security.entities.AppUser;
import com.healthapp.HealthApp.security.service.IServiceAuthentication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HealthAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthAppApplication.class, args);
	}
  //@Bean
	CommandLineRunner commandLineRunner(IServiceAuthentication iServiceAuthentication){
		return args -> {
			AppRole role1 = new AppRole(0,"ADMIN",null);

			AppRole role2 = new AppRole(0,"USER",null);

			iServiceAuthentication.createAppRole(role1);
			iServiceAuthentication.createAppRole(role2);

			AppUser user1 = new AppUser(0,"adem","adem@gmail.com","Adm@2023",role1);
			AppUser user2 = new AppUser(0,"wess","wess@gmail.com","Adm@2023",role2);

			iServiceAuthentication.createAppUser(user1);
			iServiceAuthentication.createAppUser(user2);


		};
  }
}
