package com.flowsoft.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;

import com.vaadin.shared.communication.MethodInvocation;

public class WandaSecurityExpressionHandler extends
		DefaultMethodSecurityExpressionHandler implements
		MethodSecurityExpressionHandler {

	protected SecurityExpressionRoot createSecurityExpressionRoot(
			Authentication authentication, MethodInvocation invocation) {
		WandaSecurityExpressionRoot root = new WandaSecurityExpressionRoot(
				authentication);
		// root.setThis(invocation.getThis());
		root.setPermissionEvaluator(getPermissionEvaluator());
		return root;
	}
}
