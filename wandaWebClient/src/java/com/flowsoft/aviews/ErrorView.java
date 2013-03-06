package com.flowsoft.aviews;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

public class ErrorView extends Panel implements View, Serializable {

	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	public static final String NAME = "error";

	public ErrorView() {
		Embedded e = new Embedded("", new ThemeResource("img/error_owl.png"));
		e.setHeight("150px");
		// Label l = new Label("Page Not Found.");
		// l.setStyleName(Reindeer.LABEL_H1);
		// l.setHeight("200px");
		Label l2 = new Label();
		l2.setContentMode(ContentMode.HTML);
		l2.setValue("<p>Looks like the page you are looking for is not available.</p><p> This may be because it was deleted or it was moved to other location.</p> <p>Don't forget, that you can always login at the  <a href="
				+ new ExternalResource("#!login").getURL()
				+ ">Login page</a></p>");
		l2.setStyleName(Reindeer.LABEL_H2);
		l2.setHeight("300px");

		addComponent(e);
		if (((WandaVaadinClient) WandaVaadinClient.getCurrent()).getAktUser() != null) {
			l2.setValue("<p>Looks like the page you are looking for is not available.</p><p> This may be because it was deleted or it was moved to other location.</p> <p>Don't forget, that you can always go to the  <a href="
					+ new ExternalResource("#!main").getURL()
					+ ">Main page</a></p>");
		}
		addComponent(l2);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
