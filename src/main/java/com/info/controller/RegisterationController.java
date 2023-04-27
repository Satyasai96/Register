package com.info.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.binding.User;
import com.info.constants.AppConstants;
import com.info.service.RegisterServiceImpl;

@RestController
public class RegisterationController {
	@Autowired
	private RegisterServiceImpl service;

	@GetMapping("/emailcheck/{email}")
	public String checkEmail(@PathVariable String email) {
		boolean uniqueEmail = service.uniqueEmail(email);
		if (uniqueEmail) {
			return AppConstants.UNIQUE;
		} else {
			return AppConstants.DUPLICATE;

		}

	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		Map<Integer, String> countries = service.getCountries();
		return countries;

	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable Integer stateId) {
		Map<Integer, String> states = service.getStates(stateId);
		return states;

	}

	@GetMapping("/citi/{stateId}")
	public Map<Integer, String> getCities(@PathVariable Integer stateId) {
		Map<Integer, String> cities = service.getCities(stateId);
		return cities;

	}
	@PostMapping("/saveuser")
	public String saveUser(@RequestBody User user) {
		boolean registerUser = service.registerUser(user);
		if(registerUser) {
			return AppConstants.SUCCESS;
		}
		else {
			return AppConstants.FAIL;
		}
		
	}

}
