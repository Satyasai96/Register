package com.info.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.binding.User;
import com.info.constants.AppConstants;
import com.info.entity.CityEntity;
import com.info.entity.CountryEntity;
import com.info.entity.StateEntity;
import com.info.entity.UserEntity;
import com.info.props.AppProperties;
import com.info.repository.CityRepository;
import com.info.repository.CountryRepository;
import com.info.repository.StateRepository;
import com.info.repository.UserRepository;
import com.info.utill.EmailUtil;

@Service
public class RegisterServiceImpl implements IResgisterService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private AppProperties appProps;

	@Override
	public boolean uniqueEmail(String userEmail) {
		UserEntity findByUserEmail = userRepo.findByUserEmail(userEmail);
		if (findByUserEmail != null) {
			return false;
		} else {
			return false;

		}

	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> findAll = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		for (CountryEntity entity : findAll) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());

		}

		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> stateList = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateyMap = new HashMap<>();
		for (StateEntity entity : stateList) {
			stateyMap.put(entity.getStateId(), entity.getStateName());

		}
		return stateyMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> findByStateId = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		for (CityEntity entity : findByStateId) {
			cityMap.put(entity.getCityId(), entity.getCityName());
		}
		return cityMap;
	}

	@Override
	public boolean registerUser(User user) {
		user.setUserPwd(generateTempPassword());
		user.setUserAccountStatus("locked");
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		UserEntity save = userRepo.save(userEntity);
		if (null != save.getUserId()) {
			return sendRegEmail(user);
		}
		return false;
	}

	private String generateTempPassword() {
		// logic to generate tempPassworf
		String tempPwd = null;
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();

		tempPwd = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return tempPwd;
	}

	private boolean sendRegEmail(User user) {
		boolean emailSent = false;
		Map<String, String> messages = appProps.getMessages();
		// logic to send email
		
		//String subject = "User Registeration Success ";
		String subject = messages.get(AppConstants.REG_MAIL_SUBJECT);
		String fileName=messages.get(AppConstants.REG_MAIL_BODY_TEMPLATE);
		String body = readMailBody(fileName, user);
		emailSent=emailUtil.sendEmail(subject, body, user.getUserEmail());
		return emailSent;

	}

	public String readMailBody(String fileName, User user) {
		String finalMailBody = null;
		StringBuffer buffer = new StringBuffer();
		Path path = Paths.get(fileName);

		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(line->{
				buffer.append(line);
			});
			
			String orginalMailBody=buffer.toString();
			finalMailBody = orginalMailBody.replace(AppConstants.FNAME, user.getUserFirstName());
			finalMailBody=finalMailBody.replace(AppConstants.EMAIL, user.getUserEmail());
			finalMailBody=finalMailBody.replace(AppConstants.TEMP_PWD,user.getUserPwd());
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return finalMailBody;

	}

}
