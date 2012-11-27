package com.flowsoft.sidebarcomponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map.Entry;

import org.apache.commons.lang.RandomStringUtils;

import com.flowsoft.client.CreateArticleView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;

public class TagCloudComponent extends GridLayout {

	private static final long serialVersionUID = 1L;
	private ArrayList<CssLinkComponent> tags;

	public TagCloudComponent() {
		super(3, 5);
		tags = new ArrayList<CssLinkComponent>();
		generateTag(initTags());
		addTag();
	}

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
		// for (CssLinkComponent c : tags) {
		// c.setStyleName(style);
		// }
	}

	private void addTag() {
		Collections.sort(tags);
		int k = 1;
		for (int i = 2; i >= 0; i--) {
			for (int j = 0; j < 5; j++) {
				CssLinkComponent akt;
				if (k % 2 == 1) {
					akt = tags.get(tags.size() - k);
					k++;
				} else {
					akt = tags.get(k);
					k++;
				}
				addComponent(akt, i, j);
				setComponentAlignment(akt, Alignment.MIDDLE_CENTER);
				if (i == 2 && j % 2 == 0) {
					setComponentAlignment(akt, Alignment.MIDDLE_RIGHT);
				}
				if (i == 1) {
					setComponentAlignment(akt, Alignment.MIDDLE_CENTER);
				}
				if (i == 0) {
					setComponentAlignment(akt, Alignment.MIDDLE_LEFT);
				}
				if (i == 0 && j % 2 == 1) {
					setComponentAlignment(akt, Alignment.MIDDLE_RIGHT);
				}

			}
		}

	}

	private void generateTag(Hashtable<String, Integer> tagText) {
		if (tagText.size() > 16) {

		}
		for (Entry<String, Integer> s : tagText.entrySet()) {
			// TODO: A: View for tags
			CssLinkComponent cssLink = new CssLinkComponent(s.getKey(),
					new ExternalResource("#!" + CreateArticleView.NAME));
			cssLink.setRank(s.getValue());
			tags.add(cssLink);
		}

	}

	// TODO: A: Webservice: getTags() (name, rank)
	private Hashtable<String, Integer> initTags() {
		Hashtable<String, Integer> tagText = new Hashtable<String, Integer>();
		for (int i = 2; i < 18; i++) {
			tagText.put(
					RandomStringUtils.randomAlphabetic(Math.max(
							462 % (Math.abs((i == 0 ? i : 1) - 8)), 5)),
					((int) (Math.random() * 100)) % 3);
		}
		return tagText;
	}
}
