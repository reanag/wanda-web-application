package com.flowsoft.forms;

import java.sql.Timestamp;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.RegistrationView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.codesnippet.SnippetButton;
import com.flowsoft.codesnippet.SnippetReader;
import com.flowsoft.domain.WandaUser;
import com.flowsoft.util.ValidatorUtils;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.ErrorMessage;
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
	@Transient
	protected final static Logger logger = LoggerFactory
			.getLogger(RegistrationView.class);
	private static final long serialVersionUID = 1L;
	public static final String FIELD_WIDHT = "300px";
	private String triedName;

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

	private PasswordField passwordAgain;
	private Label firstNameL, lastNameL, usernameL, emailAddressL,
			aboutMeTextL, passwordL, passwordAgainL;
	private FieldGroup binder;
	private WandaUser wandaUser;

	// private PictureUpload pictureUpload;

	public RegistrationForm(FieldGroup binder, WandaUser w) {
		super(2, 9);
		triedName = "";
		this.binder = binder;
		this.wandaUser = w;

		initFields();
	}

	private void initFields() {
		usernameL = new Label("<h3>Username: (*) </h3>", ContentMode.HTML);
		username = new TextField("");
		username.setImmediate(true);
		username.addValidator(new Validator() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {

				if (!triedName.equals(username.getValue())) {
					if (username.getValue() == null
							|| username.getValue().equals("")) {
						username.setComponentError(new ErrorMessage() {

							private static final long serialVersionUID = 1L;

							@Override
							public String getFormattedHtmlMessage() {
								return "Username cannot be null!";
							}

							@Override
							public ErrorLevel getErrorLevel() {
								return ErrorLevel.ERROR;
							}
						});
						return;
					}
					if (username.getValue().length() < 6
							|| username.getValue().length() > 15) {
						username.setComponentError(new ErrorMessage() {

							private static final long serialVersionUID = 1L;

							@Override
							public String getFormattedHtmlMessage() {
								return "The size of the username must be between 6 and 15!";
							}

							@Override
							public ErrorLevel getErrorLevel() {
								return ErrorLevel.ERROR;
							}
						});
						return;
					}
					WandaUser s = ((WandaVaadinClient) WandaVaadinClient
							.getCurrent()).getController().findByUsername(
							username.getValue());
					triedName = username.getValue();

					if (s != null) {

						submit.setEnabled(false);
						username.setComponentError(new ErrorMessage() {

							private static final long serialVersionUID = 1L;

							@Override
							public String getFormattedHtmlMessage() {
								return "Username already exist!";
							}

							@Override
							public ErrorLevel getErrorLevel() {
								return ErrorLevel.ERROR;
							}
						});
					} else {
						username.setComponentError(null);
						submit.setEnabled(true);
					}
				}

			}
		});
		username.addBlurListener(this);
		passwordL = new Label("<h3>Password: (*)</h3>", ContentMode.HTML);

		password = new PasswordField("");
		password.setImmediate(true);
		password.addBlurListener(this);
		passwordAgainL = new Label("<h3>Repeat password: (*)</h3>",
				ContentMode.HTML);

		passwordAgain = new PasswordField("");
		passwordAgain.setImmediate(true);
		passwordAgain.addBlurListener(new BlurListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {
				passwordAgain.validate();

			}
		});
		passwordAgain.addValidator(new Validator() {

			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				logger.debug("validate " + value);
				if (!passwordAgain.getValue().equals(password.getValue())) {
					submit.setEnabled(false);
					passwordAgain.setComponentError(new ErrorMessage() {

						private static final long serialVersionUID = 1L;

						@Override
						public String getFormattedHtmlMessage() {
							// TODO Auto-generated method stub
							return "Password not match";
						}

						@Override
						public ErrorLevel getErrorLevel() {
							// TODO Auto-generated method stub
							return ErrorLevel.ERROR;
						}
					});
				} else {
					passwordAgain.setComponentError(null);
					submit.setEnabled(true);
				}
			}
		});
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
		emailAddressL = new Label("<h3>Email: (*)</h3>", ContentMode.HTML);
		aboutMeText = new TextArea("");
		aboutMeTextL = new Label("<h3>About me: </h3>", ContentMode.HTML);
		submit = new Button("Submit");

		submit.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				RegistrationForm.this.commit();

			}
		});
		submit.setVisible(false);
		addComponent(usernameL, 0, 0);
		SnippetReader sr = new SnippetReader();
		SnippetButton snip2 = new SnippetButton(sr.read("fieldBind.snip"),
				WandaVaadinClient.captions.getString("snip.Binding"));
		SnippetButton snip3 = new SnippetButton(sr.read("fieldBind2.snip"),
				WandaVaadinClient.captions.getString("snip.Binding2"));
		SnippetButton snip4 = new SnippetButton(sr.read("fieldBind3.snip"),
				WandaVaadinClient.captions.getString("snip.Binding3"));
		SnippetButton snip5 = new SnippetButton(sr.read("fieldBind4.snip"),
				WandaVaadinClient.captions.getString("snip.Binding4"));
		SnippetButton snip6 = new SnippetButton(sr.read("passwordValid.snip"),
				WandaVaadinClient.captions.getString("snip.passwordValid"));
		GridLayout l = new GridLayout(2, 1);
		l.addComponent(username, 0, 0);
		l.addComponent(snip2, 1, 0);
		addComponent(l, 1, 0);
		username.setWidth(FIELD_WIDHT);

		GridLayout l3 = new GridLayout(2, 1);
		l3.addComponent(password, 0, 0);
		l3.addComponent(snip3, 1, 0);
		addComponent(passwordL, 0, 1);
		addComponent(l3, 1, 1);
		password.setWidth(FIELD_WIDHT);
		GridLayout l2 = new GridLayout(2, 1);
		l2.addComponent(passwordAgain, 0, 0);
		l2.addComponent(snip6, 1, 0);
		addComponent(passwordAgainL, 0, 2);
		addComponent(l2, 1, 2);
		passwordAgain.setWidth(FIELD_WIDHT);
		GridLayout l4 = new GridLayout(2, 1);
		l4.addComponent(firstName, 0, 0);
		l4.addComponent(snip4, 1, 0);
		addComponent(firstNameL, 0, 3);
		addComponent(l4, 1, 3);
		firstName.setWidth(FIELD_WIDHT);
		GridLayout l5 = new GridLayout(2, 1);
		l5.addComponent(lastName, 0, 0);
		l5.addComponent(snip5, 1, 0);
		addComponent(lastNameL, 0, 4);
		addComponent(l5, 1, 4);

		lastName.setWidth(FIELD_WIDHT);
		addComponent(emailAddressL, 0, 5);
		addComponent(emailAddress, 1, 5);
		emailAddress.setWidth(FIELD_WIDHT);
		addComponent(aboutMeTextL, 0, 6);
		addComponent(aboutMeText, 1, 6);
		aboutMeText.setWidth(FIELD_WIDHT);
		// addComponent(pictureUpload, 0, 7, 1, 7);
		addComponent(submit, 0, 8, 1, 8);
		setWidth("700px");
		setColumnExpandRatio(0, 100);
		setColumnExpandRatio(1, 100);
		setColumnExpandRatio(2, 1);
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
			// Avatar a = new Avatar();
			// a.setImage(pictureUpload.getPicture());
			// wandaUser.setAvatar(a);

			binder.commit();
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.getController().createUser(wandaUser);
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.goToLoginPage();
		} catch (CommitException e) {

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
		binder.isValid();
		submit.setVisible(true);
		if (username.getValue().equals("") || password.getValue().equals("")
				|| emailAddress.getValue().equals("")) {
			submit.setVisible(false);
		}

	}
}
