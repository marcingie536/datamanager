package com.zaju.datamanager.utils;

import org.springframework.util.StringUtils;

import com.zaju.datamanager.dto.UserDTO;

public class RequestValidation {

	public static boolean isUserDTOValid(UserDTO userDTO) {
		if ( StringUtils.isEmpty(userDTO.getLogin()) ) 
			return false;
		if ( StringUtils.isEmpty(userDTO.getPassword()) ) 
			return false;
		if ( StringUtils.isEmpty(userDTO.getRole()) ) 
			return false;
		if ( !userDTO.getRole().equals("USER") && !userDTO.getRole().equals("ADMIN"))
			return false;
		
		return true;
	}
	
}
