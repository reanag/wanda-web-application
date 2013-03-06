package com.flowsoft.security;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class WandaPermissionEvaluator implements PermissionEvaluator {

	Logger logger = LoggerFactory.getLogger(WandaPermissionEvaluator.class);

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {

		boolean hasPermission = false;
		logger.debug("hasPerm");
		if (authentication != null && permission instanceof String) {

		} else {
			hasPermission = false;
		}
		return hasPermission;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		logger.debug("hasPerm");
		return false;
	}

}