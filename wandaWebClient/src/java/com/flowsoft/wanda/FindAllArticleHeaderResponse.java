
package com.flowsoft.wanda;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.flowsoft.domain.ArticleHeader;

/**
 * <p>Java class for findAllArticleHeaderResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findAllArticleHeaderResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="articleHeaderList" type="{http://wanda.flowsoft.com/}articleHeader" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAllArticleHeaderResponse", propOrder = {
    "articleHeaderList"
})
public class FindAllArticleHeaderResponse {

	protected List<ArticleHeader> articleHeaderList;

	/**
     * Gets the value of the articleHeaderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the articleHeaderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArticleHeaderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArticleHeader }
     * 
     * 
     */
	public List<ArticleHeader> getArticleHeaderList() {
		if (articleHeaderList == null) {
			articleHeaderList = new ArrayList<ArticleHeader>();
		}
		return this.articleHeaderList;
	}

}
