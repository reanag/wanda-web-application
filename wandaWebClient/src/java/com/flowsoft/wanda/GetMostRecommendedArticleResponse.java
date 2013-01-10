package com.flowsoft.wanda;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.flowsoft.domain.Article;

/**
 * <p>
 * Java class for getMostRecommendedArticleResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="getMostRecommendedArticleResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recommendedArticles" type="{http://wanda.flowsoft.com/}article" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMostRecommendedArticleResponse", propOrder = { "recommendedArticles" })
public class GetMostRecommendedArticleResponse {

	protected List<Article> recommendedArticles;

	/**
	 * Gets the value of the recommendedArticles property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the recommendedArticles property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRecommendedArticles().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Article }
	 * 
	 * 
	 */
	public List<Article> getRecommendedArticles() {
		if (recommendedArticles == null) {
			recommendedArticles = new ArrayList<Article>();
		}
		return this.recommendedArticles;
	}

}
