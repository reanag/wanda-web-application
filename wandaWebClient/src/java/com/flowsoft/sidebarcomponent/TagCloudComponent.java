package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.CreateArticleView;
import com.flowsoft.domain.Tag;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;

public class TagCloudComponent extends GridLayout {
	static Logger logger = LoggerFactory.getLogger(TagCloudComponent.class);
	private static final long serialVersionUID = 1L;
	private ArrayList<CssLinkComponent> tags;

	public TagCloudComponent() {
		super(3, 5);
		tags = new ArrayList<CssLinkComponent>();
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
	}

	private void addTag() {
		for (CssLinkComponent t : tags) {
			addComponent(t);
		}
	}

	public void init(List<Tag> list) {
		generateTag(list);
	}

	private void generateTag(List<Tag> list) {
		if (tags == null) {
			tags = new ArrayList<CssLinkComponent>();
		}

		for (Tag t : list) {

			// TODO: A: View for tags
			CssLinkComponent cssLink = new CssLinkComponent(t.getTagName(),
					new ExternalResource("#!" + CreateArticleView.NAME));
			cssLink.setRank(t.getRank());
			tags.add(cssLink);
		}

		addTag();
	}

}
