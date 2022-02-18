package com.vobi.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import com.vobi.bank.domain.UserType;
import com.vobi.bank.domain.Users;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UserRepositoryIT {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(userRepository);
		assertNotNull(userTypeRepository);
	}

	@Test
	@Order(2)
	void debeCrearUnUsuario() {
		//arrange
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
		user = userRepository.save(user);
		//Assert
		assertNotNull(user, "El usuario no fue creado");
	}

	@Test
	@Order(3)
	void debeModificarUnUsuario() {
		//arrange
		String user_email = "masternexzuz@gmail.com";
		Users user = null;

		user = userRepository.findById(user_email).get();
		user.setEnable("N");
		//Act
		user = userRepository.save(user);
		//Assert
		assertNotNull(user, "el usuario es nulo no se pudo grabar");
	}

	@Test
	@Order(4)
	void debeBorrarUnUsuario() {
		//arrange
		String user_email = "masternexzuz@gmail.com";
		Users user = null;
		Optional<Users> userOptional = null;
		
		user = userRepository.findById(user_email).get();
		//Act
		userRepository.delete(user);
		userOptional = userRepository.findById(user_email);

		//Assert
		assertFalse(userOptional.isPresent(), "el usuario no fue borrado");
	}
}
