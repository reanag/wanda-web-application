package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.flowsoft.domain.Article;

/**
 * <p>
 * Java class for commitArticle complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="commitArticle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="article" type="{http://wanda.flowsoft.com/}article" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "commitArticle", propOrder = { "article" })
public class CommitArticle {

	protected Article article;

	/**
	 * Gets the value of the article property.
	 * 
	 * @return possible object is {@link Article }
	 * 
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Sets the value of the article property.
	 * 
	 * @param value
	 *            allowed object is {@link Article }
	 * 
	 */
	public void setArticle(Article value) {
		this.article = value;
	}

}
