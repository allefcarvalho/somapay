package com.somapay.paywages.services;

import com.somapay.paywages.model.Authentication;

public interface AuthenticationServices {
	String getToken(Authentication authentication);

	Boolean tokenValidation(String token);

}
