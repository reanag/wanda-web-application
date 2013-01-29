package com.flowsoft.sidebarcomponent;

import java.util.List;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Tag;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

public class Sidebar extends Panel implements View {

	private static final long serialVersionUID = 1L;
	private SearchTool searchTool;
	private OptionSelectorComponent optionSelector;
	private ArticleRecommenderComponent articleRecommender;
	private TagCloudComponent tagCloud;
	private Navigator navigator;
	private UserDetailsService controller;

	public Sidebar(Navigator n, UserDetailsService c) {
		this.setWidth("100px");
		navigator = n;
		controller = c;

		searchTool = new SearchTool(navigator, controller);
		optionSelector = new OptionSelectorComponent(n);
		optionSelector.setStyleName("optionSelStyle");
		articleRecommender = new ArticleRecommenderComponent();
		articleRecommender.setStyleName("recommenderStyle");

		tagCloud = new TagCloudComponent();
		tagCloud.setStyleName("tagCloudStyle");
		TagCloudComponent.setNavigator(navigator);
		addComponent(searchTool);
		addComponent(optionSelector);
		addComponent(articleRecommender);
		addComponent(tagCloud);

	}

	public void initTagList(List<Tag> list) {
		tagCloud.init(list);

	}

	public void initUserCategories(List<Category> categoryList) {
		if (categoryList == null) {
			return;
		}
		optionSelector.initOwnCategoryList(categoryList);
		optionSelector.requestRepaint();
	}

	public void initTopCategories(List<Category> topCategories) {
		if (topCategories == null) {
			return;
		}
		optionSelector.initTopCategoryList(topCategories);
		optionSelector.requestRepaint();
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void initArticleBlokk(List<Article> mostRecentArticles,
			List<Article> mostPopularArticles,
			List<Article> mostRecommendedArticles) {
		articleRecommender.setMostRencentArticles(mostRecentArticles,
				mostPopularArticles, mostRecommendedArticles);
	}

}
