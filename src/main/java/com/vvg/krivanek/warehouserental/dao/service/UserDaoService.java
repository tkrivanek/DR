package com.vvg.krivanek.warehouserental.dao.service;

import com.vvg.krivanek.warehouserental.domain.User;

public interface UserDaoService {

	User getUserByUsername(String username);
	
	User getUserById(String userId);
}
