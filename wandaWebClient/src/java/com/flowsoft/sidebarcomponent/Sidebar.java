package com.flowsoft.sidebarcomponent;

import java.util.List;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Tag;
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

	public Sidebar(Navigator navigator) {
		this.setWidth("100px");
		searchTool = new SearchTool();
		optionSelector = new OptionSelectorComponent(navigator);
		optionSelector.setStyleName("optionSelStyle");
		articleRecommender = new ArticleRecommenderComponent();
		articleRecommender.setStyleName("recommenderStyle");

		tagCloud = new TagCloudComponent();
		tagCloud.setStyleName("tagCloudStyle");
		addComponent(searchTool);
		addComponent(optionSelector);
		addComponent(articleRecommender);
		addComponent(tagCloud);

	}

	public void initTagList(List<Tag> list) {
		tagCloud.init(list);

	}

	public void initUserCategories(List<Category> categoryList) {
		optionSelector.init(categoryList);
		optionSelector.requestRepaint();
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void initArticleBlokk(List<Article> mostRecentArticles) {
		articleRecommender.setMostRencentArticles(mostRecentArticles);
	}

}
