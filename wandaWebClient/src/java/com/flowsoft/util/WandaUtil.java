package com.flowsoft.util;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class WandaUtil {
	public static final Object iso3166_PROPERTY_NAME = "name";
	public static final Object iso3166_PROPERTY_SHORT = "short";
	private static final String[] iso3166 = new String[] { "english", "en_EN",
			"hungarian", "hu_HU" };

	public static IndexedContainer getCountryContainer() {
		IndexedContainer c = new IndexedContainer();
		fillIso3166Container(c);
		return c;
	}

	private static void fillIso3166Container(IndexedContainer container) {
		container.addContainerProperty(iso3166_PROPERTY_NAME, String.class,
				null);
		container.addContainerProperty(iso3166_PROPERTY_SHORT, String.class,
				null);

		for (int i = 0; i < iso3166.length; i++) {
			String name = iso3166[i++];
			String id = iso3166[i];
			Item item = container.addItem(id);
			item.getItemProperty(iso3166_PROPERTY_NAME).setValue(name);
			item.getItemProperty(iso3166_PROPERTY_SHORT).setValue(id);
		}
		container.sort(new Object[] { iso3166_PROPERTY_NAME },
				new boolean[] { true });
	}

}
