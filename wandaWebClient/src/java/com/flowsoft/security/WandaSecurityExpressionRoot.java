package com.flowsoft.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;

public class WandaSecurityExpressionRoot extends SecurityExpressionRoot {

	public WandaSecurityExpressionRoot(Authentication a) {
		super(a);
	}

	private static Logger logger = LoggerFactory
			.getLogger(WandaSecurityExpressionRoot.class);

	private Object filterObject;
	private Object returnObject;
	private Object target;

	public boolean adminOnly() {
		logger.debug("haha -- check if this function is used by admin role only");
		return false; // this.hasAuthority("ADMIN");
	}

	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	public Object getFilterObject() {
		return filterObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	void setThis(Object target) {
		this.target = target;
	}

	public Object getThis() {
		return target;
	}

}
