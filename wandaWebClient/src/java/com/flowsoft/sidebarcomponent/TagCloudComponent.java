package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.TagView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Tag;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;

public class TagCloudComponent extends GridLayout {
	static Logger logger = LoggerFactory.getLogger(TagCloudComponent.class);
	private static final long serialVersionUID = 1L;
	private ArrayList<CssLinkComponent> tags;
	private List<Tag> list;

	private static Boolean firstTime = true;

	public TagCloudComponent() {
		super(3, 5);
		tags = new ArrayList<CssLinkComponent>();
		generateTag();
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

	public void generateTag() {

		list = ((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().getAllTag();
		if (list == null || list.isEmpty()) {
			return;
		}

		if (tags == null || tags.isEmpty()) {
			tags = new ArrayList<CssLinkComponent>();

		}
		int aktRank = 0;
		for (Tag t : list) {

			TagView v = new TagView(t.getTagName());
			((WandaVaadinClient) WandaVaadinClient.getCurrent()).initView(v);
			CssLinkComponent cssLink = new CssLinkComponent(v.getTagName(),
					new ExternalResource("#!" + v.NAME));
			if (t.getRank() == aktRank) {
				cssLink.setRank(t.getRank() + 2);
				aktRank = t.getRank() + 2;
			} else {
				aktRank = t.getRank();
			}
			tags.add(cssLink);

		}

		addTag();
	}

}
