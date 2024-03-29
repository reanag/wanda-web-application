package com.flowsoft.wanda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.Avatar;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

public class WandaUtil {
	public final static Logger logger = LoggerFactory
			.getLogger(ArticleDaoImpl.class);

	public static List<Tag> convertTagListToDomain(
			List<com.flowsoft.entity.Tag> tagList) {
		List<Tag> domainList = new ArrayList<Tag>();
		for (com.flowsoft.entity.Tag t : tagList) {
			domainList.add(convertTagToDomain(t));
		}
		return domainList;
	}

	private static Tag convertTagToDomain(com.flowsoft.entity.Tag t) {
		Tag newTag = new Tag();

		newTag.setCreatedTS(t.getCreatedTS());
		newTag.setModifiedTS(t.getModifiedTS());
		newTag.setId(t.getId());
		if (t.getRank() == null) {
			newTag.setRank(12);
		} else {
			newTag.setRank(t.getRank());
		}
		newTag.setTagName(t.getTagName());

		return newTag;
	}

	public static List<Article> convertArticleListToDomain(
			List<com.flowsoft.entity.Article> resultList) {
		List<Article> domainList = new ArrayList<Article>();
		for (com.flowsoft.entity.Article a : resultList) {
			domainList.add(convertArticleToDomain2(a));
		}

		return domainList;
	}

	public static List<Comment> convertCommentListToDomain(
			List<com.flowsoft.entity.Comment> resultList) {
		List<Comment> domainList = new ArrayList<Comment>();
		for (com.flowsoft.entity.Comment a : resultList) {
			domainList.add(convertCommentToDomain(a));
		}

		return domainList;
	}

	private static Comment convertCommentToDomain(com.flowsoft.entity.Comment a) {
		Comment c = new Comment();
		c.setCommentContent(a.getCommentContent());

		// c.setCommentedArticle(convertArticleToDomain(a.getCommentedArticle()));
		c.setCreatedTS(a.getCreatedTS());
		c.setId(a.getId());
		c.setModifiedTS(a.getModifiedTS());
		c.setOwner(convertWandaUserToDomain(a.getOwner()));
		return c;
	}

	public static Article convertArticleToDomain(com.flowsoft.entity.Article a) {
		if (a == null) {
			return null;
		}
		Article domainObject = new Article();
		domainObject.setCategory(convertCategoryToDomain(a.getCategory()));
		domainObject.setContent(a.getContent());
		domainObject.setRank(a.getRank());
		domainObject.setCreatedTS(a.getCreatedTS());
		domainObject.setModifiedTS(a.getModifiedTS());
		domainObject.setId(a.getId());
		domainObject.setOwner(convertWandaUserToDomain(a.getOwner()));
		domainObject.setTitle(a.getTitle());
		if (a.getTagList() != null) {
			domainObject.setTagList(convertTagListToDomain(a.getTagList()));
		}

		return domainObject;
	}

	public static Article convertArticleToDomain2(com.flowsoft.entity.Article a) {

		Article domainObject = new Article();
		domainObject.setCategory(convertCategoryToDomain(a.getCategory()));
		domainObject.setContent(a.getContent());
		domainObject.setCreatedTS(a.getCreatedTS());
		domainObject.setModifiedTS(a.getModifiedTS());
		domainObject.setId(a.getId());
		domainObject.setOwner(convertWandaUserToDomain(a.getOwner()));
		domainObject.setTitle(a.getTitle());

		return domainObject;
	}

	public static com.flowsoft.entity.Article convertArticleToEntity(Article a) {
		com.flowsoft.entity.Article entityObject = new com.flowsoft.entity.Article();
		if (a.getCategory() != null) {
			entityObject.setCategory(convertCategoryToEntity(a.getCategory()));
		}
		entityObject.setContent(a.getContent());
		if (a.getRank() != null) {
			entityObject.setRank(a.getRank());
		}
		if (a.getRankCount() != null) {
			entityObject.setRankCount(a.getRankCount());
		}
		if (a.getCreatedTS() != null) {
			entityObject.setCreatedTS(a.getCreatedTS());
		}
		if (a.getModifiedTS() != null) {
			entityObject.setModifiedTS(a.getModifiedTS());
		}
		if (a.getId() != null) {
			entityObject.setId(a.getId());
		}
		entityObject.setOwner(convertWandaUserToEntity(a.getOwner()));
		entityObject.setTitle(a.getTitle());
		entityObject.setTagList(convertTagListToEntity(a.getTagList()));
		return entityObject;
	}

	private static Map<Integer, com.flowsoft.entity.Tag> convertTagListToEntity(
			Set<Tag> tagList) {
		Map<Integer, com.flowsoft.entity.Tag> entitySet = new HashMap<Integer, com.flowsoft.entity.Tag>();
		for (Tag t : tagList) {
			com.flowsoft.entity.Tag tag = convertTagToEntity(t);
			entitySet.put(tag.getId(), tag);
		}
		return entitySet;
	}

	private static Set<Tag> convertTagListToDomain(
			Map<Integer, com.flowsoft.entity.Tag> tagMap) {
		Set<Tag> domainSet = new HashSet<Tag>();
		if (tagMap == null || tagMap.isEmpty()) {
			return domainSet;
		}
		for (Entry<Integer, com.flowsoft.entity.Tag> t : tagMap.entrySet()) {
			domainSet.add(convertTagToDomain(t.getValue()));
		}
		return domainSet;
	}

	public static com.flowsoft.entity.Category convertCategoryToEntity(
			Category c) {
		com.flowsoft.entity.Category newCat = new com.flowsoft.entity.Category();
		newCat.setCategoryName(c.getCategoryName());
		if (c.getCreatedTS() != null) {
			newCat.setCreatedTS(c.getCreatedTS());
		}

		newCat.setDescription(c.getDescription());
		if (c.getId() != null) {
			newCat.setId(c.getId());
		}
		if (c.getModifiedTS() != null) {
			newCat.setModifiedTS(c.getModifiedTS());
		}

		newCat.setOwner(convertWandaUserToEntity(c.getOwner()));
		return newCat;
	}

	public static com.flowsoft.entity.Tag convertTagToEntity(Tag c) {
		com.flowsoft.entity.Tag newTag = new com.flowsoft.entity.Tag();

		if (c.getCreatedTS() != null) {
			newTag.setCreatedTS(c.getCreatedTS());
		}
		if (c.getId() != null) {
			newTag.setId(c.getId());
		}
		if (c.getModifiedTS() != null) {
			newTag.setModifiedTS(c.getModifiedTS());
		}
		if (c.getRank() != null) {
			newTag.setRank(c.getRank());
		}
		newTag.setTagName(c.getTagName());

		return newTag;
	}

	public static com.flowsoft.entity.Comment convertCommentToEntity(Comment a) {
		com.flowsoft.entity.Comment c = new com.flowsoft.entity.Comment();
		c.setCommentContent(a.getCommentContent());
		c.setCommentedArticle(convertArticleToEntity(a.getCommentedArticle()));
		if (a.getCreatedTS() != null) {
			c.setCreatedTS(a.getCreatedTS());
		}
		if (a.getId() != null) {
			c.setId(a.getId());
		}
		if (c.getModifiedTS() != null) {
			c.setModifiedTS(a.getModifiedTS());
		}
		c.setOwner(convertWandaUserToEntity(a.getOwner()));
		return c;
	}

	public static com.flowsoft.entity.WandaUser convertWandaUserToEntity(
			WandaUser owner) {
		com.flowsoft.entity.WandaUser w = new com.flowsoft.entity.WandaUser();
		if (owner.getCreatedTS() != null) {
			w.setCreatedTS(owner.getCreatedTS());
		}
		if (owner.getModifiedTS() != null) {
			w.setModifiedTS(owner.getModifiedTS());
		}
		w.setEmailAdress(owner.getEmailAddress());
		w.setEnabled(owner.isEnabled());
		// w.setFavoriteArticles(convertArticleSetToEntity(owner
		// .getFavoriteArticles()));
		//
		// w.setFavoriteCategories(convertCategorySetToEntity(owner
		// .getFavoriteCategories()));
		w.setFirstName(owner.getFirstName());
		if (owner.getId() != null) {
			w.setId(owner.getId());
		}
		w.setLastName(owner.getLastName());
		w.setPassword(owner.getPassword());
		w.setRole(owner.getRole());
		w.setUsername(owner.getUsername());
		if (owner.getAvatar() != null) {
			w.setAvatar(convertAvatarToEntity(owner.getAvatar()));
		}

		return w;
	}

	public static WandaUser convertWandaUserToDomain(
			com.flowsoft.entity.WandaUser entityObject) {
		WandaUser w = new WandaUser();
		w.setCreatedTS(entityObject.getCreatedTS());
		w.setModifiedTS(entityObject.getModifiedTS());
		w.setEmailAdress(entityObject.getEmailAdress());
		w.setEnabled(entityObject.isEnabled());
		// w.setFavoriteArticles(convertArticleSetToDomain(entityObject
		// .getFavoriteArticles()));
		//
		// w.setFavoriteCategories(convertCategorySetToDomain(entityObject
		// .getFavoriteCategories()));
		w.setFirstName(entityObject.getFirstName());
		w.setId(entityObject.getId());
		w.setLastName(entityObject.getLastName());
		w.setPassword(entityObject.getPassword());
		w.setRole(entityObject.getRole());
		w.setUsername(entityObject.getUsername());
		w.setAboutText(entityObject.getAboutText());
		if (entityObject.getAvatar() != null) {
			w.setAvatar(convertAvatarToDomain(entityObject.getAvatar()));
		}
		return w;
	}

	public static Avatar convertAvatarToDomain(com.flowsoft.entity.Avatar avatar) {
		Avatar a = new Avatar();
		a.setImage(avatar.getImage());
		return a;
	}

	public static com.flowsoft.entity.Avatar convertAvatarToEntity(Avatar avatar) {
		com.flowsoft.entity.Avatar a = new com.flowsoft.entity.Avatar();

		a.setImage(avatar.getImage());
		return a;
	}

	// private static Set<Category> convertCategorySetToDomain(
	// Set<com.flowsoft.entity.Category> set) {
	// Set<Category> newSet = new HashSet<Category>();
	// for (com.flowsoft.entity.Category c : set) {
	// newSet.add(convertCategoryToDomain(c));
	// }
	// return newSet;
	// }

	public static List<WandaUser> convertWandaUserListToDomain(
			List<com.flowsoft.entity.WandaUser> entityList) {

		List<WandaUser> userList = new ArrayList<WandaUser>();
		for (com.flowsoft.entity.WandaUser u : entityList) {
			userList.add(convertWandaUserToDomain(u));
		}

		return userList;
	}

	public static List<Category> convertCategoryListToDomain(
			List<com.flowsoft.entity.Category> categoryList) {

		// logger.debug("size1: " + categoryList.size());
		List<Category> domainList = new ArrayList<Category>();

		for (com.flowsoft.entity.Category c : categoryList) {
			domainList.add(convertCategoryToDomain(c));
		}
		// logger.debug("size2: " + domainList.size());
		return domainList;
	}

	public static Category convertCategoryToDomain(
			com.flowsoft.entity.Category c) {
		Category newCat = new Category();
		newCat.setCategoryName(c.getCategoryName());
		newCat.setCreatedTS(c.getCreatedTS());
		newCat.setModifiedTS(c.getModifiedTS());
		newCat.setDescription(c.getDescription());
		newCat.setId(c.getId());
		newCat.setOwner(convertWandaUserToDomain(c.getOwner()));
		return newCat;
	}
}
