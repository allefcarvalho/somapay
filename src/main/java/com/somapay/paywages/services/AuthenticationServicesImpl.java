package com.somapay.paywages.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somapay.paywages.dao.AuthenticationDao;
import com.somapay.paywages.model.Authentication;

@Service
@Transactional
public class AuthenticationServicesImpl implements AuthenticationServices {

	@Autowired
	private AuthenticationDao authenticationDao;

	@Override
	public String getToken(Authentication authentication) {
		JSONObject message = new JSONObject();
		message.put("token", authenticationDao.getToken(authentication));
		return message.toString();
	}

	@Override
	public Boolean tokenValidation(String token) {
		return authenticationDao.tokenValidation(token);
	}

}
