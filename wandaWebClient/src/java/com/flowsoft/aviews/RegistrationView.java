package com.flowsoft.aviews;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.codesnippet.SnippetButton;
import com.flowsoft.codesnippet.SnippetReader;
import com.flowsoft.domain.WandaUser;
import com.flowsoft.forms.RegistrationForm;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

public class RegistrationView extends Panel implements View, Serializable {

	private static final long serialVersionUID = 1L;
	protected final static Logger logger = LoggerFactory
			.getLogger(RegistrationView.class);
	public static final String NAME = "registration";

	private GridLayout mainLayout;
	private Label title, info;
	private RegistrationForm regForm;
	public FieldGroup binder;
	public WandaUser wandaUser;

	public RegistrationView() {

		title = new Label("Sign up");
		title.setStyleName(Reindeer.LABEL_H2);
		info = new Label(
				WandaVaadinClient.captions.getString("registration.label"));
		info.setStyleName(Reindeer.LABEL_SMALL);

		clearFields();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		clearFields();
		addComponent(mainLayout);

	}

	private void clearFields() {
		mainLayout = new GridLayout();
		SnippetReader sr = new SnippetReader();
		SnippetButton snip2 = new SnippetButton(sr.read("validatorUtil.snip"),
				WandaVaadinClient.captions.getString("snip.Validator"));
		wandaUser = new WandaUser();
		wandaUser.setUsername("");
		wandaUser.setAboutText("");
		wandaUser.setEmailAdress("");
		wandaUser.setFirstName("");
		wandaUser.setLastName("");
		wandaUser.setPassword("");
		binder = new FieldGroup();
		BeanItem<WandaUser> item = new BeanItem<WandaUser>(wandaUser);
		binder.setItemDataSource(item);

		regForm = new RegistrationForm(binder, wandaUser);
		binder.bindMemberFields(regForm);
		mainLayout.addComponent(title);
		mainLayout.addComponent(info);
		// mainLayout.addComponent(snip);
		// mainLayout.setComponentAlignment(snip, Alignment.BOTTOM_RIGHT);
		mainLayout.addComponent(regForm);
		mainLayout.addComponent(snip2);
		mainLayout.setComponentAlignment(snip2, Alignment.BOTTOM_RIGHT);
		mainLayout.setSizeFull();
		mainLayout.setComponentAlignment(regForm, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(new Link("What is this?", new ExternalResource(
				"#!AboutPage")));

	}

}
