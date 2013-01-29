package com.flowsoft.aviews;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.component.ReadMoreForm;
import com.flowsoft.domain.ArticleHeader;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
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
		// logger.debug("ID: " + viewId + " - " + this.getClass());
		layout = new VerticalLayout();
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		if (articles == null || articles.isEmpty()) {
			List<ArticleHeader> w = controller.findAllArticleHeader();
			if (articles == null) {
				articles = new Vector<CssLayout>();
			}
			for (ArticleHeader h : w) {

				ArticleView articleView = new ArticleView(h);
				((WandaVaadinClient) WandaVaadinClient.getCurrent())
						.initView(articleView);

				articles.add(new ReadMoreForm(h, navigator));
			}

		}
		for (CssLayout p : articles) {
			p.setStyleName("mydiv");
			layout.addComponent(p);
			layout.setComponentAlignment(p, Alignment.TOP_CENTER);
		}
		mainLayout.addComponent(layout);
	}

	@Override
	public void generateBody() {
	}

}
