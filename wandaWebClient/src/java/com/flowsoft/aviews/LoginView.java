package com.flowsoft.aviews;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.flowsoft.client.WandaVaadinClient;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@Theme("logintheme")
public class LoginView extends Panel implements View, Serializable {
	private final AuthenticationProvider authenticationProvider;

	private static final long serialVersionUID = 1L;
	public static final String NAME = "login";
	Logger logger = LoggerFactory.getLogger(LoginView.class);
	protected VerticalLayout mainLayout;
	protected VerticalLayout loginLayout;

	private Button b;
	private TextField tf;
	private PasswordField pf;
	private Embedded image;
	private Navigator navigator;

	public static class LoginEvent extends Event {

		private static final long serialVersionUID = -8875211687130316896L;
		private final Authentication authentication;
		Logger logger = LoggerFactory.getLogger(LoginEvent.class);

		public LoginEvent(LoginView source, Authentication authentication) {
			super(source);
			this.authentication = authentication;
		}

		public Authentication getAuthentication() {
			return authentication;
		}
	}

	public LoginView(Navigator n) {
		navigator = n;
		this.authenticationProvider = new com.flowsoft.security.AuthenticationProvider();
		init();

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void init() {
		mainLayout = new VerticalLayout();
		loginLayout = new VerticalLayout();

		image = new Embedded("", new ThemeResource("img/logo.gif"));
		image.setHeight("150px");
		image.setWidth("156px");
		tf = new TextField(WandaVaadinClient.captions.getString("username"));
		tf.setValue("erwin");
		pf = new PasswordField(WandaVaadinClient.captions.getString("password"));
		pf.setValue("12345");
		addListener(new com.vaadin.ui.Component.Listener() {
			@Override
			public void componentEvent(Event event) {
				if (event instanceof LoginView.LoginEvent) {
					if (logger.isDebugEnabled()) {
						logger.debug("User logged on ["
								+ ((LoginView.LoginEvent) event)
										.getAuthentication() + "]");
					}

					// setUser(((LoginView.LoginEvent)
					// event).getAuthentication());
					SecurityContextHolder.getContext().setAuthentication(
							((LoginView.LoginEvent) event).getAuthentication());

					navigator.addView(MainView.NAME, new MainView());
					navigator.navigateTo(MainView.NAME);
				}
			}

		});

		b = new Button(WandaVaadinClient.captions.getString("loginButtonText"),
				new Button.ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {

						Authentication auth = new UsernamePasswordAuthenticationToken(
								tf.getValue(), pf.getValue());

						try {

							logger.debug("Attempting authentication for user '"
									+ auth.getName() + "' "
									+ auth.getCredentials());

							if (WandaVaadinClient.getHttpSession() == null) {
								logger.debug("HTTP SESSION NULL");
								// WandaVaadinClient.fetchSession();
							}
							WandaVaadinClient.getHttpSession().setAttribute(
									"username", auth.getName());
							// VaadinServiceSession.getCurrent().setAttribute("username",
							// auth.getName());
							navigator.addView(MainView.NAME, new MainView());
							logger.debug("Set session variable username: "
									+ auth.getName());

							Authentication returned = authenticationProvider
									.authenticate(auth);

							logger.debug("Authentication for user '"
									+ auth.getName() + "' succeeded");
							WandaVaadinClient.fetchSession();
							fireEvent(new LoginEvent(LoginView.this, returned));

						} catch (BadCredentialsException e) {
							logger.debug(
									"Bad credentials for user '"
											+ auth.getName() + "'", e);
							new Notification(WandaVaadinClient.captions
									.getString("badCredentialsMessage"))
									.show(Page.getCurrent());

						} catch (DisabledException e) {

							logger.debug(
									"Account disabled for user '"
											+ auth.getName() + "'", e);
							new Notification(WandaVaadinClient.captions
									.getString("loginDisabledTitle")).show(Page
									.getCurrent());

						} catch (LockedException e) {

							logger.debug(
									"Account locked for user '"
											+ auth.getName() + "'", e);
							new Notification(WandaVaadinClient.captions
									.getString("loginLockedTitle")).show(Page
									.getCurrent());

						} catch (Exception e) {

							logger.error("Error while attempting authentication for user '"
									+ auth.getName()
									+ "' - Maybe server is stopped..");
							e.printStackTrace();
							new Notification(WandaVaadinClient.captions
									.getString("noSuchUserTitle")).show(Page
									.getCurrent());
						}
					}

				});

		mainLayout.setHeight("500px");
		mainLayout.addComponent(image);
		loginLayout.addComponent(tf);
		loginLayout.addComponent(pf);
		loginLayout.addComponent(b);
		loginLayout.setHeight("150px");
		mainLayout.addComponent(loginLayout);

		mainLayout.setComponentAlignment(image, Alignment.TOP_CENTER);
		mainLayout.setComponentAlignment(loginLayout, Alignment.TOP_CENTER);
		loginLayout.setComponentAlignment(tf, Alignment.MIDDLE_CENTER);
		loginLayout.setComponentAlignment(pf, Alignment.MIDDLE_CENTER);
		loginLayout.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
		addComponent(mainLayout);
	}

}
