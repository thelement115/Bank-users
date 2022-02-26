package com.vobi.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.bank.domain.Users;
import com.vobi.bank.dto.UserDTO;

@Mapper
public interface UserMapper {

	@Mapping(source = "userType.ustyId", target = "ustyId")
	public UserDTO userToUserDTO(Users user);
	
	@Mapping(target = "userType.ustyId", source = "ustyId")
	public Users userDTOtoUser(UserDTO userDTO);
	
	public List<UserDTO> userListToUserDTOList(List<Users> users);
	
	public List<Users> UserDTOListToUsers(List<UserDTO> userDTOs);
}
