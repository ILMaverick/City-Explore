package com.unicam.City_Explore.autorizzazione;

import java.util.HashMap;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.unicam.City_Explore.user.Role;

@Service
public class AutorizzationService {

	private HashMap<String, Set<Role>> autorization;
	
	public AutorizzationService() {
		super();
	}

	private void addAutorization(String page, Role role) {
		this.autorization.put(page, null);
	}
	
}
