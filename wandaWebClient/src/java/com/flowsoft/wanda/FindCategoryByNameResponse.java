package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.flowsoft.domain.Category;

/**
 * <p>
 * Java class for findCategoryByNameResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findCategoryByNameResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://wanda.flowsoft.com/}category" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findCategoryByNameResponse", propOrder = { "_return" })
public class FindCategoryByNameResponse {

	@XmlElement(name = "return")
	protected Category _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link Category }
	 * 
	 */
	public Category getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link Category }
	 * 
	 */
	public void setReturn(Category value) {
		this._return = value;
	}

}
