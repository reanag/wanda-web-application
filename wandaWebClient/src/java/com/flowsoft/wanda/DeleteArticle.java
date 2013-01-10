
package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteArticle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteArticle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="articleTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aktUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteArticle", propOrder = {
    "articleTitle",
    "aktUser"
})
public class DeleteArticle {

    protected String articleTitle;
    protected String aktUser;

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
     * Gets the value of the aktUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAktUser() {
        return aktUser;
    }

    /**
     * Sets the value of the aktUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAktUser(String value) {
        this.aktUser = value;
    }

}
