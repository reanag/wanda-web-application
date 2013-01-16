package com.flowsoft.aviews;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.component.ReadMoreForm;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

public class MainView extends GeneralView {
	Logger logger = LoggerFactory.getLogger(MainView.class);

	private static final long serialVersionUID = 1L;
	public static final String NAME = "main";
	private static Vector<CssLayout> articles;
	private static VerticalLayout layout;

	public MainView() {
		super();
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
				navigator.addView(ArticleView.NAME + "."
						+ h.getTitle().replace(' ', '.'), new ArticleView(h));
				articles.add(new ReadMoreForm(h, navigator));
			}

			for (CssLayout p : articles) {
				p.setStyleName("mydiv");
				layout.addComponent(p);
				layout.setComponentAlignment(p, Alignment.TOP_CENTER);
			}
		}
		mainLayout.removeAllComponents();
		if (mainLayout.getComponentCount() == 0) {
			mainLayout.addComponent(layout);
		}
		resizeMainLayout();
	}

	@Override
	public void generateBody() {
	}

	public static UserDetailsService getController() {
		return controller;
	}

	public static void setController(UserDetailsService c) {
		controller = c;
	}
}
