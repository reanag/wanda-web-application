
package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.flowsoft.domain.WandaUser;

/**
 * <p>Java class for createArticle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createArticle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wandaUser" type="{http://wanda.flowsoft.com/}wandaUser" minOccurs="0"/>
 *         &lt;element name="articleTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="articleContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createArticle", propOrder = {
    "wandaUser",
    "articleTitle",
    "articleContent"
})
public class CreateArticle {

	protected WandaUser wandaUser;
	protected String articleTitle;
	protected String articleContent;

	/**
     * Gets the value of the wandaUser property.
     * 
     * @return
     *     possible object is
     *     {@link WandaUser }
     *     
     */
	public WandaUser getWandaUser() {
		return wandaUser;
	}

	/**
     * Sets the value of the wandaUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link WandaUser }
     *     
     */
	public void setWandaUser(WandaUser value) {
		this.wandaUser = value;
	}

	/**
     * Gets the value of the articleTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getArticleTitle() {
		return articleTitle;
	}

	/**
     * Sets the value of the articleTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setArticleTitle(String value) {
		this.articleTitle = value;
	}

	/**
     * Gets the value of the articleContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getArticleContent() {
		return articleContent;
	}

	/**
     * Sets the value of the articleContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setArticleContent(String value) {
		this.articleContent = value;
	}

}
