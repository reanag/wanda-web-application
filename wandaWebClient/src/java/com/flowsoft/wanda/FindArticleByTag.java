package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findArticleByContent complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findArticleByContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contentSegment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findArticleByTag", propOrder = { "tagName" })
public class FindArticleByTag {

	protected String tagName;

	/**
	 * Gets the value of the contentSegment property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * Sets the value of the contentSegment property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTagName(String value) {
		this.tagName = value;
	}

}
