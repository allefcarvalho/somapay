package com.somapay.paywages.dao;

import com.somapay.paywages.model.Authentication;

public interface AuthenticationDao {
	String getToken(Authentication authentication);

	Boolean tokenValidation(String token);
}
