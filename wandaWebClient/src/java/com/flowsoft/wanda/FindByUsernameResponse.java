
package com.flowsoft.wanda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.flowsoft.domain.WandaUser;


/**
 * <p>Java class for findByUsernameResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findByUsernameResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wandaUser" type="{http://wanda.flowsoft.com/}wandaUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findByUsernameResponse", propOrder = {
    "wandaUser"
})
public class FindByUsernameResponse {

    protected WandaUser wandaUser;

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

}
