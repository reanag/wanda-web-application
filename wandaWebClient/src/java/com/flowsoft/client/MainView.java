package com.flowsoft.client;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.flowsoft.component.ReadMoreForm;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;

public class MainView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	public static final String NAME = "main";
	private Navigator navigator;
	Vector<CssLayout> articles;

	public MainView(Navigator navigator) {
		this.navigator = navigator;
	}

	public void enter(ViewChangeEvent event) {

		logger.debug("User: "
				+ SecurityContextHolder.getContext().getAuthentication()
						.getName());
		logger.debug("isAuth: "
				+ SecurityContextHolder.getContext().getAuthentication()
						.isAuthenticated());
		logger.debug("Auth: "
				+ SecurityContextHolder.getContext().getAuthentication()
						.getAuthorities());
		logger.debug("Princ: "
				+ SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal());

		if (articles == null || articles.isEmpty()) {
			List<ArticleHeader> w = controller.findAllArticleHeader();

			for (ArticleHeader h : w) {
				navigator.addView(ArticleView.NAME + "."
						+ h.getTitle().replace(' ', '.'), new ArticleView(h));
				articles.add(new ReadMoreForm(h, navigator));
			}

			for (CssLayout p : articles) {
				mainLayout.addComponent(p);
				mainLayout.setComponentAlignment(p, Alignment.TOP_CENTER);
			}
		}
		resizeMainLayout();

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
