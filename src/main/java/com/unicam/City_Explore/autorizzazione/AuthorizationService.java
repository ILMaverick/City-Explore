package com.unicam.City_Explore.autorizzazione;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicam.City_Explore.user.Role;
import com.unicam.City_Explore.user.UserService;

@Service
public class AuthorizationService {

	@Autowired
	private UserService userService;
	
	private final Map<String, Set<Role>> authorization = new HashMap<String, Set<Role>>();
	
	public AuthorizationService() {
		super();
	}

	public void addAuthorization(String page, Role... roles) {
		if (authorization.containsKey(page)) {
			Set<Role> setRole = authorization.get(page);
            Collections.addAll(setRole, roles);
		} else {
			Set<Role> newSetRole = new HashSet<Role>();
            Collections.addAll(newSetRole, roles);
			authorization.put(page, newSetRole);
		}
	}

	/**
	 * @return true if autorized
	 */
	public boolean checkAuthorization(String page) {
		return authorization.get(page).contains(userService.getCurrentUser().getRole());
	}
	
}
