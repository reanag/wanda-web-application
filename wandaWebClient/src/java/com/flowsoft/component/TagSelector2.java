package com.flowsoft.component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class TagSelector2 extends CustomField implements Container.Editor {

	private static final long serialVersionUID = -4718188396491718742L;
	Logger logger = LoggerFactory.getLogger(TagSelector2.class);

	public enum InsertPosition {
		AFTER, BEFORE
	}

	public static final String STYLE_TOKENFIELD = "tokenfield";
	public static final String STYLE_TOKENTEXTFIELD = "tokentextfield";
	public static final String STYLE_BUTTON_EMPHAZISED = "emphasize";

	protected Layout layout;

	protected InsertPosition insertPosition = InsertPosition.AFTER;

	protected TokenTextField cb = new TokenTextField(insertPosition) {

		private static final long serialVersionUID = -5550767105896319355L;

		protected void onDelete() {
			if (!buttons.isEmpty()) {
				Object[] keys = buttons.keySet().toArray();
				onTokenDelete(keys[keys.length - 1]);
				cb.focus();
			}
		}
	};

	protected LinkedHashMap<Object, Button> buttons = new LinkedHashMap<Object, Button>();
	protected Hashtable<String, String> values = new Hashtable<String, String>();
	protected HorizontalLayout buttonLayout = new HorizontalLayout();

	public TagSelector2(String caption, InsertPosition insertPosition) {
		this();
		this.insertPosition = insertPosition;
		setCaption(caption);
	}

	public TagSelector2(String caption) {
		this();
		setCaption(caption);
	}

	public TagSelector2() {
		this(new VerticalLayout());
	}

	public TagSelector2(String caption, Layout lo) {
		this(lo);
		setCaption(caption);
	}

	public TagSelector2(String caption, Layout lo, InsertPosition insertPosition) {
		this(lo);
		setCaption(caption);
		this.insertPosition = insertPosition;
	}

	public TagSelector2(Layout lo, InsertPosition insertPosition) {
		this(lo);
		this.insertPosition = insertPosition;
	}

	public TagSelector2(Layout lo) {
		setStyleName(STYLE_TOKENFIELD + " " + STYLE_TOKENTEXTFIELD);
		initCBList();
		cb.setImmediate(true);

		cb.addListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				if (cb.getValue().length() > 2) {
					if (buttons.containsKey(cb.getValue())) {
						cb.setValue("");
					}
					cb.setValue(suggest(cb.getValue()));
				}
			}
		});

		cb.addListener(new ComboBox.ValueChangeListener() {

			private static final long serialVersionUID = 4370326413130922134L;

			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				final Object tokenId = event.getProperty().getValue();

				if (tokenId != null) {
					onTokenInput(tokenId);
					cb.focus();
				}
			}

		});

		setLayout(lo);

	}

	protected String commonChars(String str, String str2) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < Math.min(str.length(), str2.length()); i++) {
			if (str.charAt(i) == str2.charAt(i)) {
				b.append(str.charAt(i));
			}
		}
		return b.toString();
	}

	protected String suggest(String v) {
		logger.error("inp" + v);
		if (v.length() < 2) {
			return v;
		}
		logger.error("Suggest: " + v);
		Vector<String> suggestion = new Vector<String>();
		for (Entry<String, String> s : values.entrySet()) {
			if (s.getKey().startsWith(v)) {
				suggestion.add(s.getKey());
			}
		}

		if (suggestion.size() == 1) {
			logger.error("sug" + suggestion.elementAt(0));
			return suggestion.elementAt(0);
		}
		if (suggestion.size() > 1) {
			Collections.sort(suggestion);
			logger.error("sug" + suggestion.elementAt(0));
			return suggestion.elementAt(0);
		}
		logger.error("sug" + v);
		return v;
	}

	public void addNewItem(String tokenId) {
		if (isReadOnly()) {
			throw new Property.ReadOnlyException();
		}
		onTokenInput(tokenId);

		cb.focus();
	}

	private void initCBList() {
		values.put("JPA", "JPA");
		values.put("Java", "Java");
		values.put("javax.persistence", "javax.persistence");
		values.put("Hibernate", "Hibernate");
		values.put("CXF 2.x", "CXF 2.x");

	}

	protected boolean existingId(Object tokenId) {

		if (values.contains(tokenId.toString())) {
			// values.remove(tokenId.toString());
			return true;
		}
		return false;
	}

	/*
	 * Rebuilds from scratch
	 */
	private void rebuild() {
		layout.removeAllComponents();
		if (!isReadOnly() && insertPosition == InsertPosition.AFTER) {
			layout.addComponent(cb);
		}
		for (Button b2 : buttons.values()) {
			buttonLayout.addComponent(b2);
		}
		layout.addComponent(buttonLayout);
		if (!isReadOnly() && insertPosition == InsertPosition.BEFORE) {
			layout.addComponent(cb);
		}
		if (layout instanceof HorizontalLayout) {
			((HorizontalLayout) layout).setExpandRatio(cb, 1.0f);
		}
	}

	protected void setInternalValue(Object newValue) {

		Set<Object> vals = (Set<Object>) newValue;
		Set<Object> old = buttons.keySet();

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

	protected void onTokenInput(Object tokenId) {
		addToken(tokenId);
	}

	protected void onTokenClick(Object tokenId) {
		removeToken(tokenId);
	}

	protected void onTokenDelete(Object tokenId) {
		onTokenClick(tokenId);
	}

	private void addTokenButton(final Object val) {
		Button b = new Button();
		configureTokenButton(val, b);
		b.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = -1943432188848347317L;

			public void buttonClick(ClickEvent event) {
				onTokenClick(val);
			}
		});
		buttons.put(val, b);

		if (insertPosition == InsertPosition.BEFORE) {
			layout.replaceComponent(cb, b);
			layout.addComponent(cb);
		} else {
			buttonLayout.addComponent(b);
		}
		if (layout instanceof HorizontalLayout) {
			((HorizontalLayout) layout).setExpandRatio(cb, 1.0f);
		}

	}

	public void addToken(Object tokenId) {
		Set<Object> set = (Set<Object>) getValue();
		if (set == null) {
			set = new LinkedHashSet<Object>();
		}
		if (set.contains(tokenId)) {
			return;
		}
		if (!existingId(tokenId)) {
			return;
		}
		HashSet<Object> newSet = new LinkedHashSet<Object>(set);

		newSet.add(tokenId);
		setValue(newSet);
	}

	public void removeToken(Object tokenId) {
		Set<Object> set = (Set<Object>) getValue();
		LinkedHashSet<Object> newSet = new LinkedHashSet<Object>(set);
		values.put(tokenId.toString(), tokenId.toString());
		newSet.remove(tokenId);

		setValue(newSet);
	}

	private void removeTokenButton(Object tokenId) {
		Button button = buttons.get(tokenId);
		buttonLayout.removeComponent(button);
		buttons.remove(tokenId);

	}

	protected void configureTokenButton(Object tokenId, Button button) {
		button.setCaption(tokenId.toString() + " ×");
		button.setDescription("Click to remove");
		button.setStyleName(Reindeer.BUTTON_LINK);
	}

	/**
	 * Gets the layout currently in use.
	 * 
	 * @return the current layout
	 */
	public Layout getLayout() {
		return layout;
	}

	protected void setLayout(Layout newLayout) {
		if (layout != null) {
			layout.removeAllComponents();
		}
		layout = newLayout;
		// TODO
		// setCompositionRoot(layout);
		rebuild();
	}

	/**
	 * Gets the current token {@link InsertPosition}.<br/>
	 * The token buttons are be placed at this position, relative to the input
	 * box.
	 * 
	 * @see #setTokenInsertPosition(InsertPosition)
	 * @see InsertPosition
	 * @return the current token insert position
	 */
	public InsertPosition getTokenInsertPosition() {
		return insertPosition;
	}

	/**
	 * Sets the token {@link InsertPosition}.<br/>
	 * The token buttons will be placed at this position, relative to the input
	 * box.
	 * 
	 * @see #getTokenInsertPosition()
	 * @see InsertPosition
	 */
	public void setTokenInsertPosition(InsertPosition insertPosition) {
		if (this.insertPosition != insertPosition) {
			this.insertPosition = insertPosition;
			cb.setTokenInsertPosition(insertPosition);
			rebuild();
		}
	}

	public void setReadOnly(boolean readOnly) {
		if (readOnly == isReadOnly()) {
			return;
		}
		for (Button b : buttons.values()) {
			b.setReadOnly(readOnly);
		}
		super.setReadOnly(readOnly);
		if (readOnly) {
			layout.removeComponent(cb);
		} else {
			rebuild();
		}
	}

	/**
	 * Sets the Container data source used for the input box. This works exactly
	 * as {@link ComboBox#setContainerDataSource(Container)}.
	 * 
	 * @see ComboBox#setContainerDataSource(Container)
	 * @param c
	 *            the token container data source
	 */

	/**
	 * Sets whether or not tokens entered by the user that not present in the
	 * container are allowed. When true, the token is added, and if
	 * {@link #setRememberNewTokens(boolean)} is true, the new token will be
	 * added to the container as well.
	 * 
	 * @see #setNewTokenHandler(NewItemHandler)
	 * @param allowNewTokens
	 *            true to allow tokens that are not in the container
	 */

	/**
	 * Checks whether or not new tokens are allowed
	 * 
	 * @see #setNewTokensAllowed(boolean)
	 * @return
	 */

	/**
	 * Gets the input prompt; works as {@link ComboBox#getInputPrompt()}.
	 * 
	 * 
	 * 
	 * /**
	 * 
	 * @see ComboBox#getItemIcon(Object)
	 * @return the icon for the given resource
	 * 
	 *         /**
	 * @see ComboBox#getItemIconPropertyId()
	 * @return the current item icon property id
	 */

	public Collection getTokenIds() {
		return values.keySet();
	}

	@Override
	public void setHeight(float height, Unit unit) {
		if (this.layout != null) {
			this.layout.setHeight(height, unit);
		}
		super.setHeight(height, unit);
	}

	@Override
	public void setWidth(float width, Unit unit) {
		if (this.layout != null) {
			this.layout.setWidth(width, unit);
		}
		super.setWidth(width, unit);
	}

	@Override
	public void setSizeFull() {
		if (this.layout != null) {
			this.layout.setSizeFull();
		}
		super.setSizeFull();
	}

	@Override
	public void setSizeUndefined() {
		if (this.layout != null) {
			this.layout.setSizeUndefined();
		}
		super.setSizeUndefined();
	}

	public void setInputHeight(String height) {
		this.cb.setHeight(height);
	}

	public void setInputWidth(String width) {
		this.cb.setWidth(width);
	}

	public void setInputHeight(float height, Unit unit) {
		this.cb.setHeight(height, unit);
	}

	public void setInputWidth(float width, Unit unit) {
		this.cb.setWidth(width, unit);
	}

	public void setInputSizeFull() {
		this.cb.setSizeFull();
	}

	public void setInputSizeUndefined() {
		this.cb.setSizeUndefined();
	}

	/**
	 * Sets the input prompt; works as {@link ComboBox#setInputPrompt(String)}.
	 * 
	 * @see ComboBox#setInputPrompt(String)
	 * @return the current input prompt
	 */
	public void setInputPrompt(String inputPrompt) {
		cb.setInputPrompt(inputPrompt);
	}

	/**
	 * sets the caption for the given token.
	 * 
	 * @see ComboBox#setItemCaption(Object, String)
	 * @param tokenId
	 *            token whose caption to set
	 * @param caption
	 *            the desired caption
	 */

	/**
	 * @see ComboBox#setItemCaptionMode(int)
	 * @param mode
	 */

	public void setTabIndex(int tabIndex) {
		cb.setTabIndex(tabIndex);
	}

	@Override
	public Class<?> getType() {
		return Set.class;
	}

	public void setBuffered(boolean buffered) {
		// TODO Auto-generated method stub

	}

	public boolean isBuffered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Component initContent() {
		return layout;
	}

	@Override
	public void setContainerDataSource(Container newDataSource) {
		// TODO Auto-generated method stub

	}

	@Override
	public Container getContainerDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

}
