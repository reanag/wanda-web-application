package com.flowsoft.sidebarcomponent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.codesnippet.SnippetButton;
import com.flowsoft.codesnippet.SnippetReader;
import com.flowsoft.domain.Article;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.Reindeer;

public class ArticleRecommenderComponent extends GridLayout {

	private static final long serialVersionUID = 1L;
	private TabSheet articleRecommender;

	public static final int ARTICLE_COUNT = 3;

	public ArticleRecommenderComponent() {
		super(2, 1);
		articleRecommender = new TabSheet();
		articleRecommender.setStyleName(Reindeer.TABSHEET_MINIMAL);
	}

	public void init() {
		removeAllComponents();

		List<Article> a = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().getRecentArticle(ARTICLE_COUNT);

		articleRecommender.addTab(initArticles(a),
				WandaVaadinClient.captions.getString("recommend.recent"));

		articleRecommender.addTab(
				initArticles(((WandaVaadinClient) WandaVaadinClient
						.getCurrent()).getController()
						.getMostRecommendedArticle(ARTICLE_COUNT)),
				WandaVaadinClient.captions.getString("recommend.adviced"));

		articleRecommender.addTab(
				initArticles(((WandaVaadinClient) WandaVaadinClient
						.getCurrent()).getController().getMostPopularArticle(
						ARTICLE_COUNT)), WandaVaadinClient.captions
						.getString("recommend.popular"));
		SnippetReader sr = new SnippetReader();
		SnippetButton snip2 = new SnippetButton(sr.read("recommend.snip"),
				WandaVaadinClient.captions.getString("snip.recommend"));

		addComponent(articleRecommender, 0, 0);
		addComponent(snip2, 1, 0);
		setColumnExpandRatio(0, 50);
		setColumnExpandRatio(1, 1);
	}

	private CssLinkListComponent initArticles(List<Article> articleList) {

		LinkedHashMap<String, ExternalResource> recentList = new LinkedHashMap<String, ExternalResource>();

		for (Article a : articleList) {
			String s;
			if (a.getTitle().length() > 30) {
				s = a.getTitle().substring(0, 27) + "...";
			} else {
				s = a.getTitle();
			}
			Date today = new Date();

			if (a.getCreatedTS().getDay() == today.getDay()) {
				recentList.put(
						s
								+ " ("
								+ new SimpleDateFormat("kk:mm").format(a
										.getCreatedTS()) + ")",
						new ExternalResource("#!articleView" + a.getId()));
			} else {
				recentList.put(
						s
								+ " ("
								+ new SimpleDateFormat("dd/MM/yy").format(a
										.getCreatedTS()) + ")",
						new ExternalResource("#!articleView" + a.getId()));
			}
		}
		CssLinkListComponent component = new CssLinkListComponent(null,
				recentList);
		component.setStyleName("list");
		return component;
	}
}
