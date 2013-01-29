package com.flowsoft.aviews;

import java.sql.Timestamp;

import javax.xml.ws.WebServiceRef;

import userdetailsserviceimpl.wanda.flowsoft.com.UserDetailsServiceImplService;

import com.flowsoft.component.PictureUpload;
import com.flowsoft.domain.Avatar;
import com.flowsoft.domain.WandaUser;
import com.flowsoft.util.ValidatorUtils;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class RegistrationForm extends GridLayout implements BlurListener {

	private static final long serialVersionUID = 1L;
	@WebServiceRef
	protected UserDetailsService controller;
	private Button submit;
	@PropertyId("firstName")
	private TextField firstName;
	@PropertyId("lastName")
	private TextField lastName;
	@PropertyId("username")
	private TextField username;
	@PropertyId("emailAddress")
	private TextField emailAddress;
	private TextArea aboutMeText;
	@PropertyId("password")
	private PasswordField password;
	// @PropertyId("password")
	private PasswordField passwordAgain;
	private Label firstNameL, lastNameL, usernameL, emailAddressL,
			aboutMeTextL, passwordL, passwordAgainL;
	private FieldGroup binder;
	private WandaUser wandaUser;
	private PictureUpload pictureUpload;
	private Navigator navigator;

	public RegistrationForm(Navigator n, FieldGroup binder, WandaUser w) {
		super(2, 9);
		UserDetailsServiceImplService ss = new UserDetailsServiceImplService();
		controller = ss.getUserDetailsServicePort();
		this.binder = binder;
		this.wandaUser = w;
		this.navigator = n;
		initFields();
	}

	private void initFields() {
		usernameL = new Label("<h3>Username: </h3>", ContentMode.HTML);
		username = new TextField("");
		username.setImmediate(true);
		username.addBlurListener(this);
		passwordL = new Label("<h3>Password: </h3>", ContentMode.HTML);

		password = new PasswordField("");
		password.setImmediate(true);
		password.addBlurListener(this);
		passwordAgainL = new Label("<h3>Password again: </h3>",
				ContentMode.HTML);

		passwordAgain = new PasswordField("");
		passwordAgain.setImmediate(true);
		// passwordAgain.addBlurListener(this);

		firstName = new TextField("");
		firstName.addBlurListener(this);
		firstName.setImmediate(true);
		firstNameL = new Label("<h3>First name: </h3>", ContentMode.HTML);
		lastName = new TextField("");
		lastName.setImmediate(true);
		lastName.addBlurListener(this);
		lastNameL = new Label("<h3>Last name: </h3>", ContentMode.HTML);

		emailAddress = new TextField("");
		emailAddress.setImmediate(true);
		emailAddress.addBlurListener(this);
		emailAddressL = new Label("<h3>Email: </h3>", ContentMode.HTML);
		aboutMeText = new TextArea("");
		aboutMeTextL = new Label("<h3>About me: </h3>", ContentMode.HTML);
		submit = new Button("Submit");
		submit.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				RegistrationForm.this.commit();

			}
		});

		pictureUpload = new PictureUpload();
		addComponent(usernameL, 0, 0);
		addComponent(username, 1, 0);
		username.setWidth("300px");
		addComponent(passwordL, 0, 1);
		addComponent(password, 1, 1);
		password.setWidth("300px");
		addComponent(passwordAgainL, 0, 2);
		addComponent(passwordAgain, 1, 2);
		passwordAgain.setWidth("300px");
		addComponent(firstNameL, 0, 3);
		addComponent(firstName, 1, 3);
		firstName.setWidth("300px");
		addComponent(lastNameL, 0, 4);
		addComponent(lastName, 1, 4);
		lastName.setWidth("300px");
		addComponent(emailAddressL, 0, 5);
		addComponent(emailAddress, 1, 5);
		emailAddress.setWidth("300px");
		addComponent(aboutMeTextL, 0, 6);
		addComponent(aboutMeText, 1, 6);
		aboutMeText.setWidth("300px");
		addComponent(pictureUpload, 0, 7, 1, 7);
		addComponent(submit, 0, 8, 1, 8);
		setWidth("600px");

		ValidatorUtils.table.put(username, "username");
		ValidatorUtils.table.put(password, "password");

		ValidatorUtils.table.put(firstName, "firstName");
		ValidatorUtils.table.put(lastName, "lastName");
		ValidatorUtils.table.put(emailAddress, "emailAddress");

		setComponentAlignment(usernameL, Alignment.BOTTOM_CENTER);
		setComponentAlignment(username, Alignment.BOTTOM_LEFT);
		setComponentAlignment(passwordL, Alignment.BOTTOM_CENTER);
		setComponentAlignment(password, Alignment.BOTTOM_LEFT);
		setComponentAlignment(passwordAgainL, Alignment.BOTTOM_CENTER);
		setComponentAlignment(passwordAgain, Alignment.BOTTOM_LEFT);
		setComponentAlignment(firstNameL, Alignment.BOTTOM_CENTER);
		setComponentAlignment(firstName, Alignment.BOTTOM_LEFT);
		setComponentAlignment(lastNameL, Alignment.BOTTOM_CENTER);
		setComponentAlignment(lastName, Alignment.BOTTOM_LEFT);
		setComponentAlignment(emailAddressL, Alignment.BOTTOM_CENTER);
		setComponentAlignment(emailAddress, Alignment.BOTTOM_LEFT);
		setComponentAlignment(aboutMeTextL, Alignment.TOP_CENTER);
		setComponentAlignment(aboutMeText, Alignment.BOTTOM_LEFT);

		setComponentAlignment(submit, Alignment.BOTTOM_RIGHT);
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);

	}

	protected void commit() {
		try {
			java.util.Date date = new java.util.Date();
			wandaUser.setCreatedTS(new Timestamp(date.getTime()));
			wandaUser.setModifiedTS(new Timestamp(date.getTime()));
			wandaUser.setEnabled(true);
			wandaUser.setRole("ROLE_USER");
			wandaUser.setAboutText(getAboutText());
			Avatar a = new Avatar();
			a.setImage(pictureUpload.getPicture());
			wandaUser.setAvatar(a);

			binder.commit();
			controller.createUser(wandaUser);
			navigator.navigateTo(LoginView.NAME);
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getAboutText() {
		return aboutMeText.getValue();
	}

	@Override
	public void blur(BlurEvent event) {

		ValidatorUtils.installSingleValidator(event.getComponent(),
				WandaUser.class);
		if (!binder.isValid()) {
			submit.setEnabled(false);
		} else {
			submit.setEnabled(true);
		}

	}
}
