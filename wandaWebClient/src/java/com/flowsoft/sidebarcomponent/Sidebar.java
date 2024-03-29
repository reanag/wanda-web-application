package com.flowsoft.sidebarcomponent;

import java.util.List;

import com.flowsoft.domain.Category;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

public class Sidebar extends Panel implements View {

	private static final long serialVersionUID = 1L;
	private SearchTool searchTool;
	private OptionSelectorComponent optionSelector;
	private ArticleRecommenderComponent articleRecommender;
	private TagCloudComponent tagCloud;

	public Sidebar() {
		// this.setWidth("200px");

		searchTool = new SearchTool();
		searchTool.setWidth(this.getWidth(), this.getWidthUnits());
		optionSelector = new OptionSelectorComponent();
		optionSelector.setWidth(this.getWidth(), this.getWidthUnits());
		optionSelector.setStyleName("optionSelStyle");
		articleRecommender = new ArticleRecommenderComponent();
		articleRecommender.setStyleName("recommenderStyle");
		articleRecommender.setWidth(this.getWidth(), this.getWidthUnits());
		articleRecommender.init();

		tagCloud = new TagCloudComponent();
		tagCloud.setStyleName("tagCloudStyle");
		tagCloud.setWidth(this.getWidth(), this.getWidthUnits());
		addComponent(searchTool);
		addComponent(optionSelector);

		addComponent(articleRecommender);
		addComponent(tagCloud);

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
		tagCloud.generateTag();
		articleRecommender.init();

	}

}
