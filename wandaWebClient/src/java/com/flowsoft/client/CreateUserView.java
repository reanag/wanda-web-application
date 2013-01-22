package com.flowsoft.client;

import javax.annotation.PostConstruct;
import javax.xml.ws.WebServiceRef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import userdetailsserviceimpl.wanda.flowsoft.com.UserDetailsServiceImplService;

import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

@Theme("vaadinclienttheme")
public class CreateUserView extends Panel implements View {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "createUser";
	Logger logger = LoggerFactory.getLogger(CreateUserView.class);

	private Button b;
	private TextField firstname, lastname, username;
	private PasswordField password;
	private Table table;
	@WebServiceRef
	private UserDetailsService controller;

	@PostConstruct
	public void init() {
		UserDetailsServiceImplService service = new UserDetailsServiceImplService();
		controller = service.getUserDetailsServicePort();
	}

	public UserDetailsService getController() {
		return this.controller;
	}

	public void setController(UserDetailsService controller) {
		this.controller = controller;
	}

	public CreateUserView() {
		table = null;
		init();

		firstname = new TextField("First name:");
		lastname = new TextField("Last name:");
		username = new TextField("Username:");
		password = new PasswordField("Password:");
		b = new Button("OK", new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// controller.createUser(firstname.getValue(),
				// lastname.getValue(), username.getValue(),
				// password.getValue());
				addToTable(firstname.getValue(), lastname.getValue(),
						username.getValue(), password.getValue());
				firstname.setValue("");
				lastname.setValue("");
				username.setValue("");
				password.setValue("");

			}
		});
		b.setStyleName(Reindeer.LAYOUT_BLACK);
		Link lnk2 = new Link("Back",
				new ExternalResource("#!" + IntroView.NAME));

		addComponent(firstname);
		addComponent(lastname);
		addComponent(username);
		addComponent(password);
		addComponent(b);
		addComponent(lnk2);

	}

	protected void addToTable(String value, String value2, String value3,
			String value4) {
		if (table == null) {
			table = new Table("Users from database:");
			table.addContainerProperty("Fist Name", String.class, null);
			table.addContainerProperty("Last Name", String.class, null);
			table.addContainerProperty("Username", String.class, null);
			table.addContainerProperty("Password:", String.class, null);
			table.setSizeFull();
			addComponent(table);

		}
		table.addItem(new String[] { value, value2, value3, value4 }, value);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
