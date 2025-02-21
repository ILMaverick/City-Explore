package com.unicam.City_Explore.autorizzazione;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserService;

@Service
public class AuthorizationService {

	@Autowired
	private UserService userService;
	
	private final HashMap<String, Set<Role>> autorization = new HashMap<String, Set<Role>>();
	
	public AuthorizationService() {
		super();
	}

	public void addAuthorization(String page, Role... roles) {
		if (autorization.containsKey(page)) {
			Set<Role> setRole = autorization.get(page);
			for (Role role : roles) {
				setRole.add(role);
			}
		} else {
			Set<Role> newSetRole = new HashSet<Role>();
			for (Role role : roles) {
				newSetRole.add(role);
			}
			autorization.put(page, newSetRole);
		}
	}

	/**
	 * @return true if autorized
	 */
	public boolean checkAutorization(String page) {
		return autorization.get(page).contains(userService.getCurrentUser().getRole());
	}
	
}
