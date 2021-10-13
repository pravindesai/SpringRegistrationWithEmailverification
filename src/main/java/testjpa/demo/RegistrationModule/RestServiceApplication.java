package testjpa.demo.RegistrationModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import testjpa.demo.RegistrationModule.AppUser.AppUser;
import testjpa.demo.RegistrationModule.AppUser.AppUserRepository;
import testjpa.demo.RegistrationModule.AppUser.Role;
import testjpa.demo.TestModule.TeacherController;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(RestServiceApplication.class, args);

//		AppUserRepository appUserRepository = applicationContext.getBean(AppUserRepository.class);
//		appUserRepository.save(new AppUser(
//				"11", "11", "111", "!11", "111", Role.ADMIN, false, false
//		));



	}
}
