package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class WandaUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	private String firstName;
	private String lastName;

	private String emailAdress;

	// private Date birthDate;

	private Boolean enabled;
	private String role;

	private Date createdTS;
	private Date modifiedTS;

	public WandaUser() {
	}

	public WandaUser(String username, String password, String firstname,
			String lastname) {
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	public WandaUser(String username, String password, Boolean isEnabled,
			String role, String email) {
		this.username = username;
		this.password = password;
		this.enabled = isEnabled;
		this.role = role;
		this.emailAdress = email;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	@Id
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	// // @Temporal(TemporalType.DATE)
	// public Date getBirthDate() {
	// return birthDate;
	// }
	//
	// public void setBirthDate(Date birthDate) {
	// this.birthDate = birthDate;
	// }
	//
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTS() {
		return createdTS;
	}

	public void setCreatedTS(Date createdTS) {
		this.createdTS = createdTS;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifiedTS() {
		return modifiedTS;
	}

	public void setModifiedTS(Date modifiedTS) {
		this.modifiedTS = modifiedTS;
	}

	// public Hashtable<String, Article> getArticles() {
	// return articles;
	// }
	//
	// public void setArticles(Hashtable<String, Article> articles) {
	// this.articles = articles;
	// }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "WandaUser [username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAdress=" + emailAdress + ", birthDate="
				+ ", enabled=" + enabled + ", role=" + role + ", createdTS=";
		// + ", modifiedTS=" + ", articles=" + articles + "]";
	}

	// public void addArticle(Article a) {
	// if (articles == null) {
	// articles = new Hashtable<String, Article>();
	// }
	// articles.put(a.getTitle(), a);
	//
	// }

}
