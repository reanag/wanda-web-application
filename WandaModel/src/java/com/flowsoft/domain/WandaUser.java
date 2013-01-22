package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WandaUser extends AbstractEntity implements Serializable {

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(max = 15, min = 5)
	private String username;
	@NotNull
	@Size(min = 6)
	@Pattern(regexp = "[A-Za-z0-9_]*")
	private String password;
	// private Integer id;
	private Avatar avatar;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	@Email
	private String emailAddress;

	private String aboutText;

	// private Date birthDate;

	private Boolean enabled;
	private String role;

	private Set<Article> favoriteArticles = new HashSet<Article>();
	private Set<Category> favoriteCategories = new HashSet<Category>();
	// private Date createdTS;
	// private Date modifiedTS;

	Logger logger = LoggerFactory.getLogger(WandaUser.class);

	public WandaUser() {
	}

	public WandaUser(String username, String password, String firstname,
			String lastname, Integer id) {
		logger.debug("Create WandaUser with id: " + username);
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
		this.id = id;
	}

	public WandaUser(String username, String password, Boolean isEnabled,
			String role, String email) {
		logger.debug("Create WandaUser with id: " + username);
		this.username = username;
		this.password = password;
		this.enabled = isEnabled;
		this.role = role;
		this.emailAddress = email;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	public WandaUser(String username, String password, String firstName,
			String lastName, String emailAdress, Boolean enabled, String role,
			Date createdTS, Date modifiedTS) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAdress;
		this.enabled = enabled;
		this.role = role;
		this.createdTS = createdTS;
		this.modifiedTS = modifiedTS;
	}

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAddress = emailAdress;
	}

	// // @Temporal(TemporalType.DATE)
	// public Date getBirthDate() {
	// return birthDate;
	// }
	//
	// public void setBirthDate(Date birthDate) {
	// this.birthDate = birthDate;
	// }

	// public Date getCreatedTS() {
	// return createdTS;
	// }
	//
	// public void setCreatedTS(Date createdTS) {
	// this.createdTS = createdTS;
	// }
	//
	// public Date getModifiedTS() {
	// return modifiedTS;
	// }
	//
	// public void setModifiedTS(Date modifiedTS) {
	// this.modifiedTS = modifiedTS;
	// }

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
				+ ", emailAdress=" + emailAddress + ", birthDate="
				+ ", enabled=" + enabled + ", role=" + role + ", createdTS=";
		// + ", modifiedTS=" + ", articles=" + articles + "]";
	}

	public Set<Article> getFavoriteArticles() {
		return favoriteArticles;
	}

	public void setFavoriteArticles(Set<Article> favoriteArticles) {
		this.favoriteArticles = favoriteArticles;
	}

	public Set<Category> getFavoriteCategories() {
		return favoriteCategories;
	}

	public void setFavoriteCategories(Set<Category> favoriteCategories) {
		this.favoriteCategories = favoriteCategories;
	}

	public String getAboutText() {
		return aboutText;
	}

	public void setAboutText(String aboutText) {
		this.aboutText = aboutText;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	// public Integer getId() {
	// return id;
	// }
	//
	// public void setId(Integer id) {
	// this.id = id;
	// }

}
