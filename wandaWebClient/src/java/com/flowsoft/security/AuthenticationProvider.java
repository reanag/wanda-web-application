package com.flowsoft.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import userdetailsserviceimpl.wanda.flowsoft.com.WandaServiceImplService;

import com.flowsoft.domain.WandaUser;
import com.flowsoft.wanda.WandaService;

public class AuthenticationProvider implements
		org.springframework.security.authentication.AuthenticationProvider,
		Serializable {

	private static final long serialVersionUID = 1L;
	private WandaUser user;

	static Logger logger = LoggerFactory
			.getLogger(AuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		logger.debug("authenticate start:");

		// WandaServiceImplService ss = new WandaServiceImplService();
		WandaService controller = new WandaServiceImplService()
				.getWandaServicePort();
		user = controller.findByUsername(authentication.getName());
		logger.debug("authenticate ws call done:");
		// logger.debug(user.getUsername() + " " + user.getPassword());

		if (authentication.getCredentials().equals(user.getPassword())) {
			final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl(user.getRole()));
			UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword(), authorities);
			logger.debug("authenticate check done:");
			// logger.debug(t.toString());
			return t;
		} else {
			throw new BadCredentialsException("Try again");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));

	}

	public WandaUser getUser() {
		return user;
	}

}
