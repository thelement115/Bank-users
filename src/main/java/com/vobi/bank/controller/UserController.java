package com.vobi.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UserDTO;
import com.vobi.bank.mapper.UserMapper;
import com.vobi.bank.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id)throws Exception{
		userService.deleteById(id);
	}
	
	@PutMapping
	public UserDTO update(@Valid @RequestBody UserDTO userDTO)throws Exception{
		Users user=userMapper.userDTOtoUser(userDTO);
		user=userService.update(user);
		userDTO=userMapper.userToUserDTO(user);
		
		return userDTO;
	}
	
	
	@PostMapping
	public UserDTO save(@Valid @RequestBody UserDTO userDTO)throws Exception{
		Users user=userMapper.userDTOtoUser(userDTO);
		user=userService.save(user);
		userDTO=userMapper.userToUserDTO(user);
		
		return userDTO;
	}
	
	
	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable("id") String id) throws Exception{
		Users user = (userService.findById(id).isPresent() == true) ? userService.findById(id).get(): null;
		return userMapper.userToUserDTO(user);
	}
	
	@GetMapping
	public List<UserDTO> findAll()throws Exception{
		
		List<Users> users = userService.findAll();
		List<UserDTO> userDTOs = userMapper.userListToUserDTOList(users);
		
		return userDTOs;
	}
}
