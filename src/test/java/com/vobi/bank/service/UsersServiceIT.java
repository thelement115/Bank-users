package com.vobi.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.domain.DocumentType;
import com.vobi.bank.domain.UserType;
import com.vobi.bank.domain.Users;
import com.vobi.bank.repository.UserTypeRepository;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UsersServiceIT {

	@Autowired
	UserService usersService;
	
	@Autowired
	UserTypeRepository userTypeRepository;


	@Test
	@Order(1)
	void debeValidarLasDepedencias() {
		assertNotNull(usersService);
		assertNotNull(userTypeRepository);
	}

	
	@Test
	@Order(2)
	void debeCrearUnUser() throws Exception {
	 //Arrange
		Users user = null;
		UserType userType= userTypeRepository.findById(1).get();
		String enable = "Y";
		String name = "Edwin chester";
		String user_email = "masternexzuz@gmail.com";
		user = new Users();
		user.setUserEmail(user_email);
		user.setName(name);
		user.setEnable(enable);
		user.setUserType(userType);

		//Act
		user =usersService.save(user);
		//Assert
		assertNotNull(user,"El usuario es nulo no se pudo grabar");
	}
	
	@Test
	@Order(3)
	void debeModificarUnUsuario() throws Exception{
		//arrange
		String user_email = "masternexzuz@gmail.com";
		Users user = null;

		user = usersService.findById(user_email).get();
		user.setEnable("N");
		//Act
		user = usersService.update(user);
		//Assert
		assertNotNull(user, "el usuario es nulo no se pudo grabar");
	}
	
	@Test
	@Order(4)
	void debeBorrarUnUsuario() throws Exception{
		//arrange
		String user_email = "masternexzuz@gmail.com";
		Users user = null;
		Optional<Users> userOptional = null;
		
		user = usersService.findById(user_email).get();
		//Act
		usersService.delete(user);
		userOptional = usersService.findById(user_email);

		//Assert
		assertFalse(userOptional.isPresent(), "el usuario no fue borrado");
	}

	@Test
	@Order(5)
	void debeConsultarTodosLosUsuarios() {
	 //Arrange	
	 List<Users> users = null;
	 //Act
	 
	 users=usersService.findAll();
	 for (Users user : users) {
		 log.info(user.getName());
	 }
	 
	 //Assert
	 
	 assertFalse(users.isEmpty(), "No consulto usuario");
	}

}
