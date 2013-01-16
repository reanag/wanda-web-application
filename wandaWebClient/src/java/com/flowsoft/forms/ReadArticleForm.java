package com.flowsoft.forms;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.ArticleView;
import com.flowsoft.aviews.MainView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.Tag;
import com.flowsoft.wanda.UserDetailsService;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.themes.Reindeer;

public class ReadArticleForm extends GridLayout {

	private static final long serialVersionUID = 1L;
	protected org.slf4j.Logger logger = LoggerFactory
			.getLogger(ReadArticleForm.class);
	private ArrayList<Link> tags;
	private Article article;
	private Button editButton;
	private ArticleRatingForm ratingForm;

	public ReadArticleForm(Article a, UserDetailsService u) {
		super(3, 5);
		this.article = a;
		ratingForm = new ArticleRatingForm(u, article.getId());
	}

	public void enter() {
		removeAllComponents();
		tags = new ArrayList<Link>();
		Label content = new Label(article.getContent());

		Label title = new Label(article.getTitle());
		title.setStyleName(Reindeer.LABEL_H1);
		Label auth = new Label(article.getOwner().getFirstName() + " "
				+ article.getOwner().getLastName());
		auth.setStyleName(Reindeer.LABEL_H2);

		ratingForm.setSizeUndefined();

		addComponent(title, 0, 0, 1, 0);

		if (article
				.getOwner()
				.getUsername()
				.equals(WandaVaadinClient.getHttpSession().getAttribute(
						"username"))) {
			editButton = new Button();
			editButton.setCaption("Edit / Delete");
			editButton.addClickListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					logger.debug("button click");
					ArticleView.edit(article);

				}
			});
			this.setColumnExpandRatio(1, 1);
			this.setColumnExpandRatio(0, 15);
			addComponent(editButton, 2, 0);

		}
		addComponent(auth, 0, 1, 2, 1);
		addComponent(content, 0, 2, 2, 2);
		if (article.getTagList() != null) {

			for (Tag s : article.getTagList()) {

				Link l = new Link(s.getTagName(), new ExternalResource("#!"
						+ MainView.NAME));
				tags.add(l);
			}
		}

		if (!tags.isEmpty()) {
			CssLayout tagsForm = new CssLayout();
			for (Link l : tags) {
				tagsForm.addComponent(l);
			}
			addComponent(tagsForm, 0, 3, 1, 3);

		}
		addComponent(ratingForm, 1, 4, 2, 4);

	}
}
