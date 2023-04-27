package com.info.service;

import java.util.Map;

import com.info.binding.User;

public interface IResgisterService {
	public boolean uniqueEmail(String userEmail);
	public Map<Integer,String> getCountries();
	public Map<Integer,String> getStates(Integer countryId);
	public Map<Integer,String> getCities(Integer stateId);
	public boolean registerUser(User user);

}
