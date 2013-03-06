package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.aviews.TagView;
import com.flowsoft.client.WandaVaadinClient;
import com.flowsoft.domain.Tag;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class TagCloudComponent extends VerticalLayout {
	static Logger logger = LoggerFactory.getLogger(TagCloudComponent.class);
	private static final long serialVersionUID = 1L;
	private ArrayList<CssLinkComponent> tags;
	private List<Tag> list;

	// private static Boolean firstTime = true;

	public TagCloudComponent() {
		// super(3, 5);
		this.setWidth("250px");
		// this.setHeight("400px");
		tags = new ArrayList<CssLinkComponent>();
		generateTag();
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
	}

	private void addTag() {
		int count = 0;
		HorizontalLayout l = new HorizontalLayout();
		// l.setWidth("250px");
		for (CssLinkComponent t : tags) {
			if (count > 20) {
				this.addComponent(l);
				count = 0;
				l = new HorizontalLayout();
				// l.setWidth("250px");
			}
			t.setStyleName("tagCloud");
			l.addComponent(t);
			count += (t.getLinkText().length());

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

		for (Tag t : list) {

			TagView v = new TagView(t.getTagName());
			((WandaVaadinClient) WandaVaadinClient.getCurrent()).initView(v);
			CssLinkComponent cssLink = new CssLinkComponent(v.getTagName(),
					new ExternalResource("#!" + v.NAME));
			if (t.getRank() == null || t.getRank() == 0) {
				cssLink.setRank(12);
			} else {

				cssLink.setRank(t.getRank());
			}

			tags.add(cssLink);

		}

		addTag();
	}
}
