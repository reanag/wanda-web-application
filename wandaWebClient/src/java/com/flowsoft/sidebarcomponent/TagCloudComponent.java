package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.TagView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Tag;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;

public class TagCloudComponent extends GridLayout {
	static Logger logger = LoggerFactory.getLogger(TagCloudComponent.class);
	private static final long serialVersionUID = 1L;
	private ArrayList<CssLinkComponent> tags;
	private static Navigator navigator;
	private static Boolean firstTime = true;

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

		if (firstTime) {
			for (Tag t : list) {

				// TODO: A: View for tags

				String n = t.getTagName();
				if (n.contains(" ")) {
					n = n.replace(' ', '.');
				}

				TagView v = new TagView(t.getTagName());
				CssLinkComponent cssLink = new CssLinkComponent(t.getTagName(),
						new ExternalResource("#!" + n));
				if (!WandaVaadinClient.viewNames.contains(n)) {
					WandaVaadinClient.viewNames.add(n);
					navigator.addView(n, v);
				}
				cssLink.setRank(t.getRank());
				tags.add(cssLink);

			}
		}
		firstTime = false;
		addTag();
	}

	public static Navigator getNavigator() {
		return navigator;
	}

	public static void setNavigator(Navigator navigator) {
		TagCloudComponent.navigator = navigator;
	}
}
