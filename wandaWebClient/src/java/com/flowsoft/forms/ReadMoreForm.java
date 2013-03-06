package com.flowsoft.forms;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.PersonalView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.component.LinkLabel;
import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.Reindeer;

public class ReadMoreForm<E extends Article> extends CssLayout {

	private static final long serialVersionUID = 1L;
	@Transient
	Logger logger = LoggerFactory.getLogger(ReadMoreForm.class);
	private LinkLabel linkLabel;
	private LinkLabel authorLabel;

	public ReadMoreForm() {
	}

	public ReadMoreForm(E e) {

		setCaption(e.getTitle());
		setStyleName("mydiv");
		if (e instanceof ArticleHeader) {
			linkLabel = new LinkLabel(((ArticleHeader) e).getContentSnippet(),
					WandaVaadinClient.captions.getString("more.link.text"),
					new ExternalResource("#!articleView" + e.getId()));
		} else {
			linkLabel = new LinkLabel(e.getContent(),
					WandaVaadinClient.captions.getString("more.link.text"),
					new ExternalResource(("#!articleView" + e.getId())));

		}
		authorLabel = new LinkLabel("",
				WandaVaadinClient.captions.getString("author.title")
						+ ((Article) e).getAuthor(), new ExternalResource(
						"#!auth" + e.getId())); // +

		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.initView(new PersonalView(e.getId(), e.getAuthor()));

		authorLabel.setStyleName(Reindeer.LABEL_SMALL);

		setWidth("570px");
		addComponent(linkLabel);
		addComponent(authorLabel);
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		if (authorLabel != null) {
			authorLabel.setStyleName(style);
		}
		if (linkLabel != null) {
			linkLabel.setStyleName(style);
		}
	}

}
