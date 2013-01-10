package com.flowsoft.createArticleComponent;

import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.ComboBox;

public abstract class TagSelectorComboBox extends ComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TokenFieldServerRpc rpc = new TokenFieldServerRpc() {
		public void deleteToken() {
			onDelete();
		}
	};

	public TagSelectorComboBox() {
		registerRpc(rpc);
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);
		target.addVariable(this, "del", false);

	}

	abstract protected void onDelete();

}
