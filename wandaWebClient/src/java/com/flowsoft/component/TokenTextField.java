package com.flowsoft.component;

import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.TextField;

public abstract class TokenTextField extends TextField {

	private static final long serialVersionUID = 8382983756053298383L;

	protected TagSelector2.InsertPosition insertPosition;

	private TokenFieldServerRpc rpc = new TokenFieldServerRpc() {
		public void deleteToken() {
			onDelete();
		}
	};

	public TokenTextField(TagSelector2.InsertPosition insertPosition) {
		this.insertPosition = insertPosition;
		registerRpc(rpc);
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);
		target.addVariable(this, "del", false);
		if (insertPosition == TagSelector2.InsertPosition.AFTER) {
			target.addAttribute("after", true);
		}
	}

	public void setTokenInsertPosition(
			TagSelector2.InsertPosition insertPosition) {
		this.insertPosition = insertPosition;
		requestRepaint();
	}

	abstract protected void onDelete();

}
