package com.flowsoft.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.client.WandaVaadinClient;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

public class LocaleSelectorComboBox extends VerticalLayout implements
		Property.ValueChangeListener {
	private static final long serialVersionUID = 1L;
	static Logger logger = LoggerFactory.getLogger(LocaleSelectorComboBox.class);

	public LocaleSelectorComboBox() {
		setSpacing(true);
		ComboBox l = new ComboBox("", WandaUtil.getCountryContainer());
		l.setInputPrompt(WandaVaadinClient.captions.getString("language"));

		l.setItemCaptionPropertyId(WandaUtil.iso3166_PROPERTY_NAME);
		l.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);

		l.setFilteringMode(Filtering.FILTERINGMODE_CONTAINS);
		l.setImmediate(true);
		l.addListener(this);

		l.setNullSelectionAllowed(false);
		addComponent(l);
	}

	public void valueChange(ValueChangeEvent event) {

		Property selected = WandaUtil.getCountryContainer()
				.getContainerProperty(event.getProperty().toString(), "short");

	}
}