package com.somapay.paywages.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ResponseServicesImpl implements ResponseServices{
	
	JSONObject response = new JSONObject();

	@Override
	public String messageInfo(String content) {
		response.put("title", "Information");
		response.put("content", content);
		return response.toString();
	}

	@Override
	public String messageAlert(String content) {
		response.put("title", "Alert");
		response.put("content", content);
		return response.toString();
	}

	@Override
	public String messageError(String content) {
		response.put("title", "Error");
		response.put("content", content);
		return response.toString();
	}

}
