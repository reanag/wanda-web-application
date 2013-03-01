package com.flowsoft.component;

import java.util.List;

import com.flowsoft.aviews.ArticleView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.flowsoft.forms.ReadMoreForm;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.Reindeer;

public class ArticlePanel<E extends Article> extends TabSheet {

	public static final int ARTICLE_PER_PAGE = 5;
	private static final long serialVersionUID = 1L;

	public ArticlePanel(List<E> w) {
		this.setStyleName(Reindeer.TABSHEET_MINIMAL);
		if (w == null) {
			return;
		}

		int pageNumber = w.size() / ARTICLE_PER_PAGE;
		if (w.size() % ARTICLE_PER_PAGE > 0) {
			pageNumber = pageNumber + 1;
		}

		for (int i = 0; i < pageNumber; i++) {
			Panel p = new Panel();
			for (int j = ARTICLE_PER_PAGE * i; j < ARTICLE_PER_PAGE * (i + 1); j++) {
				try {

					p.addComponent(new ReadMoreForm(w.get(j)));

				} catch (IndexOutOfBoundsException e) {
				}
			}

			addTab(p, (i + 1) + ". Page");
		}

		for (E h : w) {

			ArticleView articleView = new ArticleView(h);
			((WandaVaadinClient) WandaVaadinClient.getCurrent())
					.initView(articleView);

			// ((Panel) getTab(pageNum)).addComponent());
		}

	}
}
