
package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findArticleByTitle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findArticleByTitle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="articleTitleSegment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAccurateSearch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findArticleByTitle", propOrder = {
    "articleTitleSegment",
    "isAccurateSearch"
})
public class FindArticleByTitle {

    protected String articleTitleSegment;
    protected Boolean isAccurateSearch;

    /**
     * Gets the value of the articleTitleSegment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleTitleSegment() {
        return articleTitleSegment;
    }

    /**
     * Sets the value of the articleTitleSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleTitleSegment(String value) {
        this.articleTitleSegment = value;
    }

    /**
     * Gets the value of the isAccurateSearch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAccurateSearch() {
        return isAccurateSearch;
    }

    /**
     * Sets the value of the isAccurateSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAccurateSearch(Boolean value) {
        this.isAccurateSearch = value;
    }

}
