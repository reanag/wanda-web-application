package com.flowsoft.aviews;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.component.ArticlePanel;
import com.flowsoft.domain.ArticleHeader;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
public class MainView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;

	private static Vector<CssLayout> articles;
	private static VerticalLayout layout;

	public MainView() {
		super();
		NAME = "main";
		articles = null;

		layout = new VerticalLayout();
		setSizeFull();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);

		List<ArticleHeader> w = ((WandaVaadinClient) WandaVaadinClient
				.getCurrent()).getController().findAllArticleHeader();

		ArticlePanel panel = new ArticlePanel(w);

		mainLayout.addComponent(panel);
		mainLayout.addComponent(layout);

		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.initView(new AboutMeView());
		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.initView(new AboutSiteView());
		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.initView(new SearchView());
	}

	@Override
	public void generateBody() {
	}

}
