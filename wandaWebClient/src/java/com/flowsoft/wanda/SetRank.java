
package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setRank complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setRank">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="articleId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="newRank" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setRank", propOrder = {
    "articleId",
    "newRank"
})
public class SetRank {

    protected Integer articleId;
    protected Double newRank;

    /**
     * Gets the value of the articleId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * Sets the value of the articleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setArticleId(Integer value) {
        this.articleId = value;
    }

    /**
     * Gets the value of the newRank property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNewRank() {
        return newRank;
    }

    /**
     * Sets the value of the newRank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNewRank(Double value) {
        this.newRank = value;
    }

}
