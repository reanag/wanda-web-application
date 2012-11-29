package com.flowsoft.client;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.themes.Reindeer;

public class ArticleView extends GeneralView implements View, Serializable {

	private static final long serialVersionUID = -8116691770439511775L;
	public static final String NAME = "articleView";
	public Logger logger = LoggerFactory.getLogger(ArticleView.class);
	private CssLayout cssLayout;

	private Article article;

	public ArticleView(ArticleHeader a) {
		generateArticle();
		initArticle(a);
	}

	private void initArticle(ArticleHeader a) {
		article = controller.findArticleByTitle(a.getTitle());
	}

	public ArticleView(Article a) {
		article = a;
		generateArticle();
	}

	public void generateArticle() {

		Link lnk2 = new Link("Back", new ExternalResource("#!" + MainView.NAME));

		mainLayout.addComponent(cssLayout);
		mainLayout.setComponentAlignment(cssLayout, Alignment.TOP_CENTER);
		cssLayout.addComponent(lnk2);

	}

	@Override
	public void generateBody() {
		cssLayout = new CssLayout();
		cssLayout.setWidth("550px");
		cssLayout.setStyleName("mydiv");
	}

	public void enter(ViewChangeEvent event) {

		Label content = new Label(article.getContent());
		cssLayout.setStyleName("mydiv");

		Label title = new Label(article.getTitle());
		title.setStyleName(Reindeer.LABEL_H1);
		Label auth = new Label(article.getOwner().getFirstName() + " "
				+ article.getOwner().getLastName());
		auth.setStyleName(Reindeer.LABEL_H2);

		cssLayout.addComponent(title);
		cssLayout.addComponent(auth);
		cssLayout.addComponent(content);
		resizeMainLayout();

	}

}