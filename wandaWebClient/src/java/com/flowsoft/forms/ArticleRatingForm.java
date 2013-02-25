package com.flowsoft.forms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.flowsoft.client.WandaVaadinClient;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

public class ArticleRatingForm extends GridLayout {

	private static final long serialVersionUID = 1L;
	private CheckBox animatedCheckBox;
	private Set<RatingStars> allRatingStars = new HashSet<RatingStars>();
	protected Integer id;
	private Table table;
	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
			5);
	final RatingStars avgRs = new RatingStars();
	final RatingStars yourRs = new RatingStars();

	static {
		valueCaptions.put(1, "Horrible");
		valueCaptions.put(2, "Poor");
		valueCaptions.put(3, "OK");
		valueCaptions.put(4, "Good");
		valueCaptions.put(5, "Excellent");
	}

	public ArticleRatingForm(Integer i) {
		this.setStyleName("rate");
		id = i;
		table = new Table();
		table.addContainerProperty("Average rating", RatingStars.class, null);
		table.addContainerProperty("Rate the article", RatingStars.class, null);
		table.setHeight("60px");

		populateTable(4.2);
		addComponent(table);

		animatedCheckBox = new CheckBox("Animated?");
		animatedCheckBox.setValue(true);
		animatedCheckBox.setImmediate(true);
		animatedCheckBox.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 6001160591512323325L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				for (RatingStars rs : allRatingStars) {
					rs.setAnimated((Boolean) event.getProperty().getValue());
				}
			}
		});

	}

	private void populateTable(Double rate) {

		avgRs.setHeight("30px");
		avgRs.setMaxValue(5);
		// avgRs.setValue(((WandaVaadinClient) WandaVaadinClient.getCurrent())
		// .getController().getRank(id));
		avgRs.setReadOnly(true);

		allRatingStars.add(avgRs);

		yourRs.setHeight("30px");
		yourRs.setMaxValue(5);
		yourRs.setImmediate(true);
		yourRs.setReadOnly(false);
		yourRs.setValueCaption(valueCaptions.values().toArray(new String[5]));
		yourRs.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 3978380217446180197L;

			public void valueChange(ValueChangeEvent event) {
				Double value = (Double) event.getProperty().getValue();

				Notification.show("You voted " + value
						+ " stars for this article.",
						Notification.Type.TRAY_NOTIFICATION);

				RatingStars changedRs = (RatingStars) event.getProperty();
				// reset value captions
				changedRs.setValueCaption(valueCaptions.values().toArray(
						new String[5]));
				// set "Your Rating" caption
				changedRs.setValueCaption((int) Math.round(value), "");

				// dummy logic to calculate "average" value
				avgRs.setReadOnly(false);
				((WandaVaadinClient) WandaVaadinClient.getCurrent())
						.getController().setRank(id, value);
				avgRs.setValue(((WandaVaadinClient) WandaVaadinClient
						.getCurrent()).getController().getRank(id));
				avgRs.setReadOnly(true);
				yourRs.setReadOnly(true);
			}
		});
		allRatingStars.add(yourRs);
		Object itemId = table.addItem();
		Item i = table.getItem(itemId);

		i.getItemProperty("Rate the article").setValue(yourRs);
		i.getItemProperty("Average rating").setValue(avgRs);

	}

	public void refreshAverage() {
		avgRs.setReadOnly(false);
		avgRs.setValue(((WandaVaadinClient) WandaVaadinClient.getCurrent())
				.getController().getRank(id));
		avgRs.setReadOnly(true);
	}
}
