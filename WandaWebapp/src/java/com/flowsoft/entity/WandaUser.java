package com.flowsoft.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class WandaUser extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	private String firstName;
	private String lastName;
	@Embedded
	private Avatar avatar;
	@Size(max = 1200)
	private String aboutText;

	@OneToMany(orphanRemoval = true, mappedBy = "owner")
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	// TODO: @ManyToMany
	// private Set<Article> favoriteArticles = new HashSet<Article>();
	//
	// public Set<com.flowsoft.entity.Article> getFavoriteArticles() {
	// return favoriteArticles;
	// }
	//
	// public void setFavoriteArticles(Set<Article> favoriteArticles) {
	// this.favoriteArticles = favoriteArticles;
	// }

	// TODO: @ManyToMany
	// private Set<Category> favoriteCategories = new HashSet<Category>();

	// public Set<Category> getFavoriteCategories() {
	// return favoriteCategories;
	// }
	//
	// public void setFavoriteCategories(Set<Category> favoriteCategories) {
	// this.favoriteCategories = favoriteCategories;
	// }

	private String emailAdress;

	// private Date birthDate;

	private Boolean enabled;
	private String role;

	@Transient
	Logger logger = LoggerFactory.getLogger(WandaUser.class);

	public WandaUser() {
	}

	public WandaUser(String username, String password, String firstname,
			String lastname) {
		logger.debug("Create WandaUser with id: " + username);
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	public WandaUser(String username, String password, Boolean isEnabled,
			String role, String email) {
		logger.debug("Create WandaUser with id: " + username);
		this.username = username;
		this.password = password;
		this.enabled = isEnabled;
		this.role = role;
		this.emailAdress = email;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
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

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

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

	public String getAboutText() {
		return aboutText;
	}

	public void setAboutText(String aboutText) {
		this.aboutText = aboutText;
	}

	@Override
	public String toString() {
		return "WandaUser [username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAdress=" + emailAdress + ", birthDate="
				+ ", enabled=" + enabled + ", role=" + role + ", createdTS=";
		// + ", modifiedTS=" + ", articles=" + articles + "]";
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

}
