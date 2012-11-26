package com.flowsoft.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.flowsoft.client.WandaVaadinClient;

public class WandaAccessDeniedHandler implements AccessDeniedHandler {

	private String accessDeniedUrl;

	public WandaAccessDeniedHandler() {
	}

	public WandaAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		response.sendRedirect(accessDeniedUrl);
		request.getSession().setAttribute("message",
				WandaVaadinClient.captions.getString("permisson.denied"));

	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
}
