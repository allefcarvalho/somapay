package com.somapay.paywages.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.somapay.paywages.model.Authentication;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {

	private static final Logger logger = LogManager.getLogger("AuthenticationDaoImpl");

	@PersistenceContext
	private EntityManager em;

	@Override
	public String getToken(Authentication authentication) {
		Object token = "";
		try {
			Query query = em
					.createNativeQuery("SELECT token FROM public.authentication WHERE login = ? AND password = ?");
			query.setParameter(1, authentication.getLogin());
			query.setParameter(2, authentication.getPassword());
			token = query.getSingleResult();

		} catch (Exception e) {
			logger.error(e);
		}

		return token.toString();

	}

	@Override
	public Boolean tokenValidation(String token) {
		Boolean active_status = false;
		try {
			Query query = em.createNativeQuery("SELECT CASE WHEN token = ? THEN 'TRUE' ELSE 'FALSE' END as active_status FROM public.authentication");
			query.setParameter(1, token);
			active_status = Boolean.parseBoolean(query.getSingleResult().toString());
		} catch (Exception e) {
			logger.error(e);
		}

		return active_status;
	}

}
