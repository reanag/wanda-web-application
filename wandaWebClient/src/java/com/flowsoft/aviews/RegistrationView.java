package com.flowsoft.aviews;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.domain.WandaUser;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

public class RegistrationView extends Panel implements View, Serializable {

	private static final long serialVersionUID = 1L;
	protected static Logger logger = LoggerFactory
			.getLogger(RegistrationView.class);
	public static final String NAME = "registration";
	public Navigator navigator;
	private GridLayout mainLayout;
	private Label title, info;
	private RegistrationForm regForm;
	public FieldGroup binder;
	public WandaUser wandaUser;

	public RegistrationView() {

		mainLayout = new GridLayout();
		title = new Label("Sign up");
		title.setStyleName(Reindeer.LABEL_H2);
		info = new Label(
				"This is a simple registration page. Please fill the following form to registrate.");
		info.setStyleName(Reindeer.LABEL_SMALL);
		wandaUser = new WandaUser();
		wandaUser.setUsername("sanyika");
		wandaUser.setAboutText("");
		wandaUser.setEmailAdress("sanyi@sanyi.sb");
		wandaUser.setFirstName("Sanyi");
		wandaUser.setLastName("Sandor");
		wandaUser.setPassword("123456");
		binder = new FieldGroup();
		BeanItem<WandaUser> item = new BeanItem<WandaUser>(wandaUser);
		binder.setItemDataSource(item);

		regForm = new RegistrationForm(navigator, binder, wandaUser);

		binder.bindMemberFields(regForm);
		mainLayout.addComponent(title);
		mainLayout.addComponent(info);
		mainLayout.addComponent(regForm);
		mainLayout.setSizeFull();
		mainLayout.setComponentAlignment(regForm, Alignment.MIDDLE_CENTER);
	}

	public Navigator getNavigator() {
		return this.navigator;
	}

	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		addComponent(mainLayout);

	}

}
