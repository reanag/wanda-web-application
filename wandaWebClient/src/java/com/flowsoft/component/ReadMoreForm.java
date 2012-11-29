package com.flowsoft.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.ArticleView;
import com.flowsoft.client.PersonalView;
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
	protected static Navigator navigator;

	public ReadMoreForm() {
	}

	public ReadMoreForm(ArticleHeader h, Navigator n) {
		navigator = n;
		setCaption(h.getTitle());
		setStyleName("mydiv");
		linkLabel = new LinkLabel(h.getContent(),
				WandaVaadinClient.captions.getString("more.link.text"),
				new ExternalResource("#!" + ArticleView.NAME + "."
						+ h.getTitle().replace(' ', '.')));

		authorLabel = new LinkLabel("",
				WandaVaadinClient.captions.getString("author.title")
						+ h.getAuthor(), new ExternalResource("#!auth="
						+ h.getAuthor())); // +

		PersonalView pv = new PersonalView(h.getAuthor(), navigator);

		logger.debug("Add view to navigator: " + pv.getName());
		navigator.addView(pv.getName(), pv);
		authorLabel.setStyleName(Reindeer.LABEL_SMALL);

		setWidth("550px");
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