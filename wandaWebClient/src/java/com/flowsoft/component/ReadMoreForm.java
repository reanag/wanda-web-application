package com.flowsoft.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.PersonalView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.ArticleHeader;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.Reindeer;

public class ReadMoreForm extends CssLayout {

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(ReadMoreForm.class);
	private LinkLabel linkLabel;
	private LinkLabel authorLabel;
	private Integer articleId;
	protected static Navigator navigator;

	public ReadMoreForm() {
	}

	public ReadMoreForm(ArticleHeader h, Navigator n) {
		articleId = h.getId();
		navigator = n;
		setCaption(h.getTitle());
		setStyleName("mydiv");
		linkLabel = new LinkLabel(h.getContent(),
				WandaVaadinClient.captions.getString("more.link.text"),
				new ExternalResource("#!articleView."
						+ h.getTitle().replace(' ', '.')));

		authorLabel = new LinkLabel("",
				WandaVaadinClient.captions.getString("author.title")
						+ h.getAuthor(), new ExternalResource("#!auth="
						+ h.getAuthor())); // +

		((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.initView(new PersonalView(h.getAuthor()));

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
