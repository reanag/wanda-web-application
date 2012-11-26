package com.flowsoft.security;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import com.flowsoft.client.MainView;

public class WandaPermissionEvaluator implements
		org.springframework.security.access.PermissionEvaluator {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		// TODO Auto-generated method stub
		logger.debug("HasPermission: " + authentication.getName());
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// TODO Auto-generated method stub
		logger.debug("HasPermission2: " + authentication.getName());
		return false;
	}

}