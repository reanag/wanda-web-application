package com.flowsoft.client;

import com.flowsoft.aviews.GeneralView;
import com.flowsoft.domain.WandaUser;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class PersonalView extends GeneralView implements View {

	private static final long serialVersionUID = 1L;
	public final String NAME;
	private CssLayout cssLayout;
	private String author;

	public PersonalView(String author, Navigator navigator) {
		NAME = "auth=" + author;
		this.author = author;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);

		try {
			WandaUser u = controller.findByUsername(author);
			Label l = new Label(u.getFirstName() + " " + u.getLastName());
			Label l2 = new Label(u.getEmailAdress());
			Label l3 = new Label(u.getRole());
			cssLayout.addComponent(l);
			cssLayout.addComponent(l2);
			cssLayout.addComponent(l3);

			resizeMainLayout();
		} catch (NullPointerException e) {

		}

	}

	@Override
	public void generateBody() {
		cssLayout = new CssLayout();
		cssLayout.setWidth("550px");
		cssLayout.setStyleName("mydiv");

		mainLayout.addComponent(cssLayout);
	}

	public String getName() {
		return NAME;
	}

}
