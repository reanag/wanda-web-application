package com.flowsoft.client;

import java.util.List;
import java.util.Vector;

import javax.xml.ws.WebServiceRef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import userdetailsserviceimpl.wanda.flowsoft.com.UserDetailsServiceImplService;

import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class MainView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	public static final String NAME = "main";
	private Navigator navigator;
	Vector<CssLayout> articles;

	@WebServiceRef
	private UserDetailsService controller;

	public void init() {
		UserDetailsServiceImplService ss = new UserDetailsServiceImplService();
		controller = ss.getUserDetailsServicePort();
	}

	public MainView(Navigator navigator) {
		this.navigator = navigator;
		init();
		generateArticles();
	}

	public void enter(ViewChangeEvent event) {

	}

	public void generateArticles() {
		List<ArticleHeader> w = controller.findAllArticleHeader();

		for (ArticleHeader h : w) {
			final CssLayout aForm = new CssLayout();
			aForm.setCaption(h.getTitle());
			Label l = new Label(h.getContent());
			l.setContentMode(ContentMode.HTML);

			l.setValue(l.getValue()
					+ "<a href=\""
					+ new ExternalResource("#!" + ArticleView.NAME + "."
							+ h.getTitle().replace(' ', '.')).getURL()
					+ " \"> Read the whole article</a> ");
			aForm.setStyleName("mydiv");
			navigator.addView(
					ArticleView.NAME + "." + h.getTitle().replace(' ', '.'),
					new ArticleView(h));
			Label l2 = new Label("Author: " + h.getAuthor());
			l2.setStyleName(Reindeer.LABEL_SMALL);

			aForm.setWidth("550px");
			aForm.addComponent(l);
			aForm.addComponent(l2);
			articles.add(aForm);
		}

		for (CssLayout p : articles) {
			mainLayout.addComponent(p);
			mainLayout.setComponentAlignment(p, Alignment.TOP_CENTER);
		}
	}

	@Override
	public void generateBody() {
		articles = new Vector<CssLayout>();
	}

	public UserDetailsService getController() {
		return this.controller;
	}

	public void setController(UserDetailsService controller) {
		this.controller = controller;
	}
}
