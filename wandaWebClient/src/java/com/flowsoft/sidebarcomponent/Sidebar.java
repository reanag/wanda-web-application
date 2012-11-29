package com.flowsoft.sidebarcomponent;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

public class Sidebar extends Panel implements View {

	private static final long serialVersionUID = 1L;
	private OptionSelectorComponent optionSelector;
	private ArticleRecommenderComponent articleRecommender;
	private TagCloudComponent tagCloud;

	public Sidebar(Navigator navigator) {
		optionSelector = new OptionSelectorComponent(navigator);
		optionSelector.setStyleName("optionSelStyle");
		optionSelector.setSizeFull();
		articleRecommender = new ArticleRecommenderComponent();
		articleRecommender.setStyleName("recommenderStyle");

		tagCloud = new TagCloudComponent();
		tagCloud.setStyleName("tagCloudStyle");
		addComponent(optionSelector);
		addComponent(articleRecommender);
		addComponent(tagCloud);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
