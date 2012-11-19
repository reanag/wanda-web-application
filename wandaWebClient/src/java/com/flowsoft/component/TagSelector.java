package com.flowsoft.component;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.AbstractSelect.NewItemHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class TagSelector extends CustomField implements Container.Editor {

	private static final long serialVersionUID = -4718188396491718742L;
	Logger logger = LoggerFactory.getLogger(TagSelector.class);

	public enum InsertPosition {
		AFTER, BEFORE
	}

	public static final String STYLE_TOKENFIELD = "tokenfield";
	public static final String STYLE_TOKENTEXTFIELD = "tokentextfield";
	public static final String STYLE_BUTTON_EMPHAZISED = "emphasize";

	protected Layout layout;

	protected InsertPosition insertPosition = InsertPosition.AFTER;

	protected TokenComboBox cb = new TokenComboBox(insertPosition) {

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
	protected HorizontalLayout buttonLayout = new HorizontalLayout();

	public TagSelector(String caption, InsertPosition insertPosition) {
		this();
		this.insertPosition = insertPosition;
		setCaption(caption);
	}

	public TagSelector(String caption) {
		this();
		setCaption(caption);
	}

	public TagSelector() {
		this(new VerticalLayout());
	}

	public TagSelector(String caption, Layout lo) {
		this(lo);
		setCaption(caption);
	}

	public TagSelector(String caption, Layout lo, InsertPosition insertPosition) {
		this(lo);
		setCaption(caption);
		this.insertPosition = insertPosition;
	}

	public TagSelector(Layout lo, InsertPosition insertPosition) {
		this(lo);
		this.insertPosition = insertPosition;
	}

	public TagSelector(Layout lo) {
		setStyleName(STYLE_TOKENFIELD + " " + STYLE_TOKENTEXTFIELD);
		initCBList();
		cb.setImmediate(true);

		cb.setNewItemsAllowed(true);
		cb.setNullSelectionAllowed(false);

		cb.addListener(new ComboBox.ValueChangeListener() {

			private static final long serialVersionUID = 4370326413130922134L;

			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				final Object tokenId = event.getProperty().getValue();

				if (tokenId != null) {
					onTokenInput(tokenId);
					cb.setValue(null);
					cb.focus();
				}
			}

		});

		cb.setNewItemHandler(new NewItemHandler() {

			private static final long serialVersionUID = 1L;

			// This is essentially what the ComboBox.DefaultNewItemHandler does,
			// but we'll first delegate adding token button, then add to
			// container.
			public void addNewItem(String tokenId) {
				if (isReadOnly()) {
					throw new Property.ReadOnlyException();
				}
				onTokenInput(tokenId);

				cb.focus();
			}

		});

		setLayout(lo);

	}

	private void initCBList() {
		cb.addItem("JPA");
		cb.addItem("Java");
		cb.addItem("javax.persistence");
		cb.addItem("Hibernate");
		cb.addItem("CXF 2.x");

	}

	protected boolean existingId(Object tokenId) {

		if (cb.getItem(tokenId.toString()) != null) {
			cb.removeItem((tokenId).toString());
			return true;
		}
		return false;
	}

	protected void rememberToken(String tokenId) {
		if (cb.addItem(getTokenCaption(tokenId)) != null) {
			// Sets the caption property, if used
			if (getTokenCaptionPropertyId() != null) {

				// cb.getContainerProperty(tokenId, getTokenCaptionPropertyId())
				// .setValue(tokenId);

			}
		}
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
		cb.addItem(tokenId);
		newSet.remove(tokenId);

		setValue(newSet);
	}

	private void removeTokenButton(Object tokenId) {
		Button button = buttons.get(tokenId);
		buttonLayout.removeComponent(button);
		buttons.remove(tokenId);

	}

	protected void configureTokenButton(Object tokenId, Button button) {
		button.setCaption(getTokenCaption(tokenId) + " ×");
		button.setIcon(getTokenIcon(tokenId));
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
	public void setContainerDataSource(Container c) {
		cb.setContainerDataSource(c);
	}

	/**
	 * Gets the Container data source currently used for the input box. This
	 * works exactly as {@link ComboBox#getContainerDataSource()}.
	 * 
	 * @see ComboBox#getContainerDataSource()
	 * @return the container used to tokens
	 */
	public Container getContainerDataSource() {
		return cb.getContainerDataSource();
	}

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
	public void setNewTokensAllowed(boolean allowNewTokens) {
		cb.setNewItemsAllowed(allowNewTokens);
	}

	/**
	 * Checks whether or not new tokens are allowed
	 * 
	 * @see #setNewTokensAllowed(boolean)
	 * @return
	 */
	public boolean isNewTokensAllowed() {
		return cb.isNewItemsAllowed();
	}

	public void focus() {
		cb.focus();
	}

	/**
	 * Gets the input prompt; works as {@link ComboBox#getInputPrompt()}.
	 * 
	 * @see ComboBox#getInputPrompt()
	 * @return the current input prompt
	 */
	public String getInputPrompt() {
		return cb.getInputPrompt();
	}

	public String getTokenCaption(Object tokenId) {
		if (cb.containsId(tokenId)) {
			return cb.getItemCaption(tokenId);
		} else {
			return "" + tokenId;
		}
	}

	/**
	 * @see ComboBox#getItemCaptionMode()
	 * @return the current caption mode
	 */
	public ItemCaptionMode getTokenCaptionMode() {
		return cb.getItemCaptionMode();
	}

	/**
	 * @see ComboBox#getItemCaptionPropertyId()
	 * @return the current caption property id
	 */
	public Object getTokenCaptionPropertyId() {
		return cb.getItemCaptionPropertyId();
	}

	/**
	 * @see ComboBox#getItemIcon(Object)
	 * @return the icon for the given resource
	 */

	public Resource getTokenIcon(Object tokenId) {
		return cb.getItemIcon(tokenId);
	}

	/**
	 * @see ComboBox#getItemIconPropertyId()
	 * @return the current item icon property id
	 */

	public Object getTokenIconPropertyId() {
		return cb.getItemIconPropertyId();
	}

	/**
	 * Gets all tokenIds currently in the token container.
	 * 
	 * @return a collection of all tokenIds in the container
	 */
	public Collection getTokenIds() {
		return cb.getItemIds();
	}

	public int getTabIndex() {
		return cb.getTabIndex();
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
	public void setTokenCaption(Object tokenId, String caption) {
		cb.setItemCaption(tokenId, caption);
	}

	/**
	 * @see ComboBox#setItemCaptionMode(int)
	 * @param mode
	 */
	public void setTokenCaptionMode(ItemCaptionMode mode) {
		cb.setItemCaptionMode(mode);
	}

	/**
	 * @see ComboBox#setItemCaptionPropertyId(Object)
	 * @param propertyId
	 */
	public void setTokenCaptionPropertyId(Object propertyId) {
		cb.setItemCaptionPropertyId(propertyId);
	}

	/**
	 * @see ComboBox#setItemIcon(Object, Resource)
	 * @param tokenId
	 * @param icon
	 */
	public void setTokenIcon(Object tokenId, Resource icon) {
		cb.setItemIcon(tokenId, icon);
	}

	public void setTokenIconPropertyId(Object propertyId) {
		cb.setItemIconPropertyId(propertyId);
	}

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

	public void changeVariables(Object source, Map<String, Object> variables) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component initContent() {
		return layout;
	}

}
