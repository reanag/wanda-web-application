package com.flowsoft.createArticleComponent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.domain.Tag;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.NewItemHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.Reindeer;

public class TagSelectorBox extends CustomField implements Container.Editor,
		Property.ValueChangeListener {
	public static final String STYLE_TOKENFIELD = "tokenfield";
	public static final String STYLE_TOKENTEXTFIELD = "tokentextfield";
	public static final String STYLE_BUTTON_EMPHAZISED = "emphasize";
	Logger logger = LoggerFactory.getLogger(TagSelectorBox.class);
	private static final long serialVersionUID = 1L;
	private TagSelectorComboBox selectorField;

	protected LinkedHashMap<Object, Button> tags = new LinkedHashMap<Object, Button>();
	private HorizontalLayout tagField;
	private CssLayout mainLayout;
	private List<Tag> originalTags;

	public TagSelectorBox() {
		mainLayout = new CssLayout();
		selectorField = new TagSelectorComboBox() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onDelete() {
				if (!tags.isEmpty()) {
					Object[] keys = tags.keySet().toArray();
					onTokenDelete(keys[keys.length - 1]);
					selectorField.focus();
				}

			}
		};
		selectorField.setWidth(120, Unit.PIXELS);
		selectorField.setFilteringMode(FilteringMode.STARTSWITH);
		selectorField.setImmediate(true);
		selectorField.setNewItemsAllowed(true);
		selectorField.addValueChangeListener(this);

		selectorField.setNewItemHandler(new NewItemHandler() {

			private static final long serialVersionUID = 1L;

			public void addNewItem(String tokenId) {
				if (isReadOnly()) {
					throw new Property.ReadOnlyException();
				}
				logger.debug("new item: " + tokenId);
				onTokenInput(tokenId);
				selectorField.focus();
			}
		});

		mainLayout.addComponent(selectorField);

		tagField = new HorizontalLayout();
		tagField.setWidth(10, Unit.PIXELS);
		tagField.setImmediate(true);
		mainLayout.addComponent(tagField);
		mainLayout.setWidth(135, Unit.PIXELS);

		setLayout(mainLayout);
		setStyleName(STYLE_TOKENFIELD + " " + STYLE_TOKENTEXTFIELD);

	}

	public Set<Tag> getSelectedTags() {
		Set<Tag> selected = new HashSet<Tag>();
		for (Entry<Object, Button> b : tags.entrySet()) {
			selected.add(search(b));
		}
		return selected;
	}

	private Tag search(Entry<Object, Button> b) {
		boolean existingTag = false;
		for (Tag t : originalTags) {
			if (t.getTagName().equals(b.getKey())) {
				existingTag = true;
				return t;
			} else {
				existingTag = false;
			}
		}
		if (!existingTag) {
			Tag t = new Tag(b.getKey().toString());
			return t;
		}
		return null;
	}

	protected void onTokenDelete(Object tokenId) {
		onTokenClick(tokenId);
	}

	protected void setLayout(CssLayout newLayout) {
		if (mainLayout != null) {
			mainLayout.removeAllComponents();
		}
		mainLayout = newLayout;
		rebuild();
	}

	private void rebuild() {
		mainLayout.removeAllComponents();
		mainLayout.addComponent(selectorField);

		for (Button b2 : tags.values()) {
			// logger.debug("Add tag: " + b2.getCaption());
			tagField.addComponent(b2);
		}
		mainLayout.addComponent(tagField);
	}

	public void setTagList(List<Tag> tags) {
		if (tags == null || tags.isEmpty()) {
			originalTags = new ArrayList<Tag>();
			return;
		}
		this.originalTags = tags;
		for (Tag s : tags) {
			selectorField.addItem(s.getTagName());
		}
	}

	public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {

		final Object tokenId = event.getProperty().getValue();

		if (tokenId != null) {
			onTokenInput(tokenId);
			selectorField.setValue(null);
			selectorField.focus();
		}

	}

	protected void onTokenInput(Object tokenId) {
		addToken(tokenId);
	}

	public void addToken(Object tokenId) {
		Set<Object> set = (Set<Object>) getValue();
		if (set == null) {
			set = new LinkedHashSet<Object>();
		}
		if (set.contains(tokenId)) {
			return;
		}
		// if (!existingId(tokenId)) {
		//
		// // return;
		// }

		HashSet<Object> newSet = new LinkedHashSet<Object>(set);

		newSet.add(tokenId);
		setValue(newSet);
	}

	protected boolean existingId(Object tokenId) {

		if (selectorField.getItem(tokenId.toString()) != null) {
			selectorField.removeItem((tokenId).toString());
			return true;
		}
		return false;
	}

	private void addTokenButton(final Object val) {
		Button b = new Button();
		configureTokenButton(val, b);
		b.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -1943432188848347317L;

			public void buttonClick(ClickEvent event) {
				onTokenClick(val);
			}
		});

		if (mainLayout.getWidth() < this.getWidth()) {
			tags.put(val, b);
			tagField.addComponent(b);
			tagField.setWidth(
					tagField.getWidth() + b.getCaption().length() * 9,
					Unit.PIXELS);
			mainLayout.setWidth(1 + mainLayout.getWidth()
					+ b.getCaption().length() * 8, Unit.PIXELS);
		}

	}

	protected void setInternalValue(Object newValue) {

		Set<Object> vals = (Set<Object>) newValue;
		Set<Object> old = tags.keySet();

		super.setInternalValue(newValue);

		if (old == null) {
			old = new HashSet<Object>();
		}

		if (vals == null) {
			vals = new HashSet<Object>();
		}

		Set<Object> remove = new HashSet<Object>(old);
		Set<Object> add = new HashSet<Object>(vals);
		remove.removeAll(vals);
		add.removeAll(old);

		for (Object tokenId : remove) {
			removeTokenButton(tokenId);
		}
		for (Object tokenId : add) {
			addTokenButton(tokenId);
		}
	}

	private void removeTokenButton(Object tokenId) {
		Button button = tags.get(tokenId);

		tagField.setWidth(tagField.getWidth() - button.getCaption().length()
				* 9, Unit.PIXELS);
		mainLayout.setWidth(1 + mainLayout.getWidth()
				- button.getCaption().length() * 8, Unit.PIXELS);
		tagField.removeComponent(button);
		tags.remove(tokenId);

	}

	protected void onTokenClick(Object tokenId) {
		removeToken(tokenId);
	}

	public void removeToken(Object tokenId) {
		Set<Object> set = (Set<Object>) getValue();
		LinkedHashSet<Object> newSet = new LinkedHashSet<Object>(set);
		selectorField.addItem(tokenId);
		newSet.remove(tokenId);
		setValue(newSet);
	}

	protected void configureTokenButton(Object tokenId, Button button) {
		button.setCaption(getTokenCaption(tokenId) + " ×");
		button.setIcon(getTokenIcon(tokenId));
		button.setDescription("Click to remove");
		button.setStyleName(Reindeer.BUTTON_LINK);
	}

	public String getTokenCaption(Object tokenId) {
		if (selectorField.containsId(tokenId)) {
			return selectorField.getItemCaption(tokenId);
		} else {
			return "" + tokenId;
		}
	}

	public Resource getTokenIcon(Object tokenId) {
		return selectorField.getItemIcon(tokenId);
	}

	@Override
	public void setContainerDataSource(Container newDataSource) {
		selectorField.setContainerDataSource(newDataSource);

	}

	@Override
	public Container getContainerDataSource() {
		return selectorField.getContainerDataSource();
	}

	@Override
	protected Component initContent() {
		return mainLayout;
	}

	@Override
	public Class<?> getType() {
		return Set.class;
	}

	public void setSelectedTags(Set<Tag> tagList) {

		for (Tag t : tagList) {
			// logger.debug(t.getTagName() + " is added");
			onTokenInput(t.getTagName());
		}

	}

	public void unselectAll() {
		Set<Object> set = (Set<Object>) getValue();
		LinkedHashSet<Object> newSet;
		if (set != null) {
			newSet = new LinkedHashSet<Object>(set);
		} else {
			newSet = new LinkedHashSet<Object>();
		}
		setTagList(originalTags);
		newSet.clear();
		setValue(newSet);
	}
}
