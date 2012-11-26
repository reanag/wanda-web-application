
package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for comment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="comment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commentContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commentedArticle" type="{http://wanda.flowsoft.com/}article" minOccurs="0"/>
 *         &lt;element name="createdTS" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modifiedTS" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://wanda.flowsoft.com/}wandaUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comment", propOrder = {
    "commentContent",
    "commentedArticle",
    "createdTS",
    "id",
    "modifiedTS",
    "owner"
})
public class Comment {

    protected String commentContent;
    protected Article commentedArticle;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTS;
    protected Integer id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifiedTS;
    protected WandaUser owner;

    /**
     * Gets the value of the commentContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * Sets the value of the commentContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentContent(String value) {
        this.commentContent = value;
    }

    /**
     * Gets the value of the commentedArticle property.
     * 
     * @return
     *     possible object is
     *     {@link Article }
     *     
     */
    public Article getCommentedArticle() {
        return commentedArticle;
    }

    /**
     * Sets the value of the commentedArticle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Article }
     *     
     */
    public void setCommentedArticle(Article value) {
        this.commentedArticle = value;
    }

    /**
     * Gets the value of the createdTS property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTS() {
        return createdTS;
    }

    /**
     * Sets the value of the createdTS property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTS(XMLGregorianCalendar value) {
        this.createdTS = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the modifiedTS property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifiedTS() {
        return modifiedTS;
    }

    /**
     * Sets the value of the modifiedTS property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifiedTS(XMLGregorianCalendar value) {
        this.modifiedTS = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link WandaUser }
     *     
     */
    public WandaUser getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link WandaUser }
     *     
     */
    public void setOwner(WandaUser value) {
        this.owner = value;
    }

}
