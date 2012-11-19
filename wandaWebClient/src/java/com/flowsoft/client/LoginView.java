package com.flowsoft.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@Theme("logintheme")
public class LoginView extends Panel implements View {

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

	public LoginView(Navigator n) {
		navigator = n;
		generateBody();

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void generateBody() {
		mainLayout = new VerticalLayout();
		loginLayout = new VerticalLayout();

		image = new Embedded("", new ThemeResource("img/logo.gif"));
		image.setHeight("150px");
		image.setWidth("156px");
		tf = new TextField("Username: ");
		pf = new PasswordField("Password:");
		b = new Button("Login", new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				if (tf.getValue().equals(pf.getValue())
						&& tf.getValue() != null) {
					navigator.navigateTo(MainView.NAME);
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
