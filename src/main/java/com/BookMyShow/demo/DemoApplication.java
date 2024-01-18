package com.BookMyShow.demo;

import com.BookMyShow.demo.controllers.UserController;
import com.BookMyShow.demo.dtos.SignUpUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.BookMyShow.demo.models.BaseModel ;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private UserController userController ;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SignUpUserRequestDto request = new SignUpUserRequestDto() ;
		request.setEmail("Sanket@gmail.com");
		request.setName("Sanket");
		request.setPassword("Spassword");
		userController.signUp(request) ;

		userController.login("Sanket@gmail.com","Spassword") ;
	}
}
