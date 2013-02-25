package com.flowsoft.aviews;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.codesnippet.SnippetButton;
import com.flowsoft.codesnippet.SnippetReader;
import com.flowsoft.security.AuthenticationProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends Panel implements View, Serializable {
	private AuthenticationProvider authenticationProvider;

	private static final long serialVersionUID = 1L;
	public static final String NAME = "login";
	Logger logger = LoggerFactory.getLogger(LoginView.class);
	protected GridLayout mainLayout;
	protected VerticalLayout loginLayout;

	private Button b;
	private TextField tf;
	private PasswordField pf;
	private Embedded image;

	private Link registrationLink;

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

	public LoginView() {
		// navigator = n;
		this.authenticationProvider = new com.flowsoft.security.AuthenticationProvider();

	}

	private void generatePages() {

		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.initView(new MainView());

		((WandaVaadinClient) WandaVaadinClient.getCurrent()).goToMainPage(0);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.setStyleName("login");
		removeAllComponents();
		mainLayout = new GridLayout(1, 2);
		loginLayout = new VerticalLayout();

		image = new Embedded("", new ThemeResource("img/logo.gif"));
		image.setHeight("200");
		image.setWidth("210");
		tf = new TextField(WandaVaadinClient.captions.getString("username"));
		tf.setValue("");
		tf.setStyleName("login");
		pf = new PasswordField(WandaVaadinClient.captions.getString("password"));
		pf.setValue("");
		pf.setStyleName("login");

		addListener(new com.vaadin.ui.Component.Listener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void componentEvent(Event event) {
				if (event instanceof LoginView.LoginEvent) {
					if (logger.isDebugEnabled()) {
						logger.debug("User logged on ["
								+ ((LoginView.LoginEvent) event)
										.getAuthentication() + "]");
					}

					SecurityContextHolder.getContext().setAuthentication(
							((LoginView.LoginEvent) event).getAuthentication());

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

							Authentication returned = authenticationProvider
									.authenticate(auth);

							fireEvent(new LoginEvent(
									((WandaVaadinClient) WandaVaadinClient
											.getCurrent()).getLoginView(),
									returned));

							((WandaVaadinClient) WandaVaadinClient.getCurrent())
									.setAktUser(authenticationProvider
											.getUser());
							logger.debug("Generate pages: ");
							generatePages();

							logger.debug("Authentication for user '"
									+ auth.getName() + "' succeeded");

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

		registrationLink = new Link("Register..", new ExternalResource("#!"
				+ RegistrationView.NAME));

		// Flash flash = new Flash(null, new
		// ThemeResource("img/bannernow.swf"));
		// flash.setHeight("150px");

		SnippetButton snip = new SnippetButton(
				SnippetReader.read("loginSnippet.snip"));

		mainLayout.setStyleName("login");
		// mainLayout.addComponent(flash);
		mainLayout.addComponent(snip);
		loginLayout.addComponent(image);
		loginLayout.setStyleName("login");
		loginLayout.addComponent(tf);
		loginLayout.addComponent(pf);
		loginLayout.addComponent(b);
		loginLayout.addComponent(registrationLink);

		mainLayout.addComponent(loginLayout);
		mainLayout.setRowExpandRatio(0, 30);
		mainLayout.setRowExpandRatio(1, 100);
		mainLayout.setSizeFull();
		mainLayout.setHeight("500px");
		loginLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		loginLayout.setComponentAlignment(tf, Alignment.MIDDLE_CENTER);
		loginLayout.setComponentAlignment(pf, Alignment.MIDDLE_CENTER);
		loginLayout.setComponentAlignment(b, Alignment.MIDDLE_CENTER);
		loginLayout.setComponentAlignment(registrationLink,
				Alignment.MIDDLE_CENTER);
		addComponent(mainLayout);
	}
}
