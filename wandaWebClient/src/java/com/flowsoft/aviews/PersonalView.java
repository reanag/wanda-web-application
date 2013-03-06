package com.flowsoft.aviews;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.WandaUser;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class PersonalView extends GeneralView implements View {

	private static final long serialVersionUID = 1L;

	private GridLayout cssLayout;
	private String author;

	public PersonalView(Integer id, String author) {
		// logger.debug("ID: " + viewId + " - " + this.getClass() + "/" +
		// author);
		NAME = "auth" + id;
		this.author = author;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);

		WandaUser u = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().findByUsername(author);
		Label l = new Label(u.getFirstName() + " " + u.getLastName());
		l.setStyleName(Reindeer.LABEL_H1);
		Label l2 = new Label("Username: " + u.getUsername());
		Label l3 = new Label("Email address: " + u.getEmailAddress());
		String aboutText = "";
		if (u.getAboutText() != null) {
			aboutText = u.getAboutText();
		} else {
			aboutText = "-";
		}
		Label l35 = new Label("About " + u.getUsername() + ": " + aboutText);
		Label l4 = new Label("Role: " + u.getRole());

		Embedded ee = null;
		if (u.getAvatar() == null) {
			ee = new Embedded("", new ThemeResource("img/default_avatar.png"));
			ee.setWidth("80px");
		} else {

			try {
				OutputStream out = new FileOutputStream(new File("/avatar.png"));
				out.write(u.getAvatar().getImage());
				out.close();

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		cssLayout = new GridLayout(3, 4);
		cssLayout.setWidth("550px");
		cssLayout.setStyleName("mydiv");
		cssLayout.addComponent(l, 0, 0);
		cssLayout.addComponent(ee, 2, 0);

		cssLayout.addComponent(l2, 0, 1);
		cssLayout.addComponent(l3, 0, 2);
		cssLayout.addComponent(l35, 0, 3, 2, 3);
		cssLayout.addComponent(l4, 2, 2);
		cssLayout.setColumnExpandRatio(0, 4);
		cssLayout.setColumnExpandRatio(1, 2);
		cssLayout.setColumnExpandRatio(2, 1);
		mainLayout.addComponent(cssLayout);
	}

	@Override
	public void generateBody() {

	}

}
