package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

	// TODO - BACSO - CODE SNIPPET ***
	@NotNull
	@Size(max = 15, min = 5)
	private String username;
	@NotNull
	@Pattern(regexp = "^.*(?=.{6,12})(?=.*[0-9])(?=.*[a-zA-Z]).*$")
	private String password;

	private Avatar avatar;

	private String firstName;

	private String lastName;
	@NotNull
	@Pattern(regexp = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,4}\\b")
	private String emailAddress;

	// TODO - BACSOG - *** CODE SNIPPET
	private String aboutText;

	private Boolean enabled;

	private String role;

	private Set<Article> favoriteArticles = new HashSet<Article>();
	private Set<Category> favoriteCategories = new HashSet<Category>();

	Logger logger = LoggerFactory.getLogger(WandaUser.class);

	public WandaUser() {
	}

	public WandaUser(String username, String password, String firstname,
			String lastname, Integer id) {
		// logger.debug("Create WandaUser with id: " + username);
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
		// logger.debug("Create WandaUser with id: " + username);
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
		return "WandaUser [username=" + username + " id " + id + ", password="
				+ password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailAdress=" + emailAddress + ", birthDate="
				+ ", enabled=" + enabled + ", role=" + role + ", createdTS=";
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

}
