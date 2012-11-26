package com.flowsoft.component;

import com.flowsoft.client.WandaVaadinClient;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.Filtering;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;

//TODO(Gergo): probald warning-ok nelkul megoldani a dolgokat. Pl. a lenti SupressWarnings
//helyett is generaltathattal volna egy hulye UID-t, aztan kesz

@SuppressWarnings("serial")
public class ComboBoxContains extends VerticalLayout implements
		Property.ValueChangeListener {

	public ComboBoxContains() {
		setSpacing(true);

		ComboBox l = new ComboBox("", WandaUtil.getCountryContainer());
		l.setInputPrompt(WandaVaadinClient.captions.getString("language"));

		// Sets the combobox to show a certain property as the item caption
		l.setItemCaptionPropertyId(WandaUtil.iso3166_PROPERTY_NAME);
		l.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);

		// Set the appropriate filtering mode for this example
		l.setFilteringMode(Filtering.FILTERINGMODE_CONTAINS);
		l.setImmediate(true);
		l.addListener(this);

		// Disallow null selections
		l.setNullSelectionAllowed(false);

		addComponent(l);
	}

	/*
	 * Shows a notification when a selection is made.
	 */
	public void valueChange(ValueChangeEvent event) {
		Property selected = WandaUtil.getCountryContainer()
				.getContainerProperty(event.getProperty().toString(), "short");

		// getWindow().showNotification("Selected country: " + selected);
	}
}