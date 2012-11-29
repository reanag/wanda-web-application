package com.flowsoft.wanda;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.flowsoft.domain.WandaUser;

/**
 * <p>
 * Java class for createArticle complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="createArticle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="owner" type="{http://wanda.flowsoft.com/}wandaUser" minOccurs="0"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="articleTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="articleContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taglist" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createArticle", propOrder = { "owner", "categoryName",
		"articleTitle", "articleContent", "taglist" })
public class CreateArticle {

	protected WandaUser owner;
	protected String categoryName;
	protected String articleTitle;
	protected String articleContent;
	protected List<String> taglist;

	/**
	 * Gets the value of the owner property.
	 * 
	 * @return possible object is {@link WandaUser }
	 * 
	 */
	public WandaUser getOwner() {
		return owner;
	}

	/**
	 * Sets the value of the owner property.
	 * 
	 * @param value
	 *            allowed object is {@link WandaUser }
	 * 
	 */
	public void setOwner(WandaUser value) {
		this.owner = value;
	}

	/**
	 * Gets the value of the categoryName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the value of the categoryName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCategoryName(String value) {
		this.categoryName = value;
	}

	/**
	 * Gets the value of the articleTitle property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArticleTitle() {
		return articleTitle;
	}

	/**
	 * Sets the value of the articleTitle property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArticleTitle(String value) {
		this.articleTitle = value;
	}

	/**
	 * Gets the value of the articleContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArticleContent() {
		return articleContent;
	}

	/**
	 * Sets the value of the articleContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArticleContent(String value) {
		this.articleContent = value;
	}

	/**
	 * Gets the value of the taglist property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the taglist property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTaglist().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getTaglist() {
		if (taglist == null) {
			taglist = new ArrayList<String>();
		}
		return this.taglist;
	}

}
