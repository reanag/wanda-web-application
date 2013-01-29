package com.flowsoft.wanda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.flowsoft.domain.Article;
import com.flowsoft.domain.ArticleHeader;
import com.flowsoft.domain.Category;
import com.flowsoft.domain.Comment;
import com.flowsoft.domain.Tag;
import com.flowsoft.domain.WandaUser;

@Service("articleDao")
public class ArticleDaoImpl implements ArticleDao {
	private EntityManager em;

	Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Article> findAllArticle(String username) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where username = :username")
				.setParameter("username", username);
		return WandaUtil.convertArticleListToDomain(query.getResultList());
	}

	@Override
	public List<Comment> findAllCommentFor(Integer articleID) {
		com.flowsoft.entity.Article actArticle = em.find(
				com.flowsoft.entity.Article.class, articleID);
		Query query = em.createQuery(
				"SELECT e FROM Comment e where commentedArticle = :article")
				.setParameter("article", actArticle);

		return WandaUtil.convertCommentListToDomain(query.getResultList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Article persistArticle(Article a) {

		// if (em.find(com.flowsoft.entity.Category.class,
		// a.getCategory().getId()) != null) {
		//
		// em.persist(WandaUtil.convertCategoryToEntity(a.getCategory()));
		// em.flush();
		// }
		com.flowsoft.entity.Article ent = WandaUtil.convertArticleToEntity(a);
		if (a.getId() == null) {
			em.persist(ent);
		} else {
			em.merge(ent);
		}
		em.flush();
		return WandaUtil.convertArticleToDomain(ent);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistCategory(Category c) {
		em.persist(WandaUtil.convertCategoryToEntity(c));
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistTag(Tag c) {
		em.persist(WandaUtil.convertTagToEntity(c));
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistComment(Comment c) {
		if (em.find(com.flowsoft.entity.Article.class, c.getCommentedArticle()
				.getId()) == null) {
			em.persist(WandaUtil.convertArticleToEntity(c.getCommentedArticle()));
			em.flush();
		}
		em.persist(WandaUtil.convertCommentToEntity(c));
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String getArticleContentByTitle(String title) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where title = :title").setParameter(
				"title", title);
		return ((Article) query.getSingleResult()).getContent();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createCategory(WandaUser owner, String name) {
		com.flowsoft.entity.Category c = new com.flowsoft.entity.Category(
				WandaUtil.convertWandaUserToEntity(owner), name);
		em.persist(c);
		em.flush();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCategory(String name, String aktuser) {
		com.flowsoft.entity.Category c = (com.flowsoft.entity.Category) em
				.createQuery(
						"SELECT c FROM Category c where categoryName = :name")
				.setParameter("name", name).getSingleResult();
		List<com.flowsoft.entity.Article> arc = em
				.createQuery(
						"SELECT a FROM Article a where category = :category")
				.setParameter("category", c).getResultList();

		for (com.flowsoft.entity.Article a : arc) {
			a.setCategory(null);
			em.merge(a);
		}
		em.flush();
		if (c.getOwner().getUsername().equals(aktuser)) {
			c.setOwner(null);
			em.remove(em.merge(c));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createArticle(WandaUser owner, String title, String content) {
		com.flowsoft.entity.Article a = new com.flowsoft.entity.Article(
				WandaUtil.convertWandaUserToEntity(owner), title, content);
		em.persist(a);
		em.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String deleteArticle(Integer id) {
		List<Comment> list = findAllCommentFor(id);
		for (Comment c : list) {
			deleteComment(c.getId());
		}
		em.flush();
		com.flowsoft.entity.Article article = em.find(
				com.flowsoft.entity.Article.class, id);

		em.remove(article);
		em.flush();
		return "OK";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void editArticle(Integer id, String newContent) {
		// Article a = findArticleByTitle(title);
		// a.setContent(newContent);
		// em.merge(WandaUtil.convertArticleToEntity(a));
		// em.flush();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Tag> getListTags() {
		List<com.flowsoft.entity.Tag> tagList = em.createQuery(
				"SELECT t FROM Tag t").getResultList();

		return WandaUtil.convertTagListToDomain(tagList);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Category> findAllCategory() {
		// List<com.flowsoft.entity.Category> categoryList = new
		// ArrayList<com.flowsoft.entity.Category>();
		// List<com.flowsoft.entity.WandaUser> userList = em.createQuery(
		// "SELECT w FROM WandaUser w").getResultList();
		//
		// for (com.flowsoft.entity.WandaUser w : userList) {
		// categoryList.addAll(w.getCategories());
		// }

		List<com.flowsoft.entity.Category> categoryList = em.createQuery(
				"SELECT c FROM Category c").getResultList();
		return WandaUtil.convertCategoryListToDomain(categoryList);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Category> findCategoryBy(Integer userid) {
		com.flowsoft.entity.WandaUser w = em.find(
				com.flowsoft.entity.WandaUser.class, userid);
		if (w != null) {
			List<com.flowsoft.entity.Category> categoryList = em
					.createQuery(
							"SELECT c FROM Category c where c.owner = :owner")
					.setParameter("owner", w).getResultList();
			return WandaUtil.convertCategoryListToDomain(categoryList);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	@Fetch(FetchMode.JOIN)
	public Article findArticleById(Integer headerId) {
		return WandaUtil.convertArticleToDomain(em.find(
				com.flowsoft.entity.Article.class, headerId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ArticleHeader> findAllArticle() {
		List<Article> articleList = WandaUtil.convertArticleListToDomain(em
				.createQuery("select a from Article a").getResultList());

		List<ArticleHeader> headerList = new ArrayList<ArticleHeader>();
		for (Article a : articleList) {
			headerList.add(new ArticleHeader(a));
		}
		return headerList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteComment(Integer id) {
		com.flowsoft.entity.Comment c = em.find(
				com.flowsoft.entity.Comment.class, id);
		logger.debug("Remove: " + c.getId());
		em.remove(c);
		em.flush();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> getMostRecentArticle(Integer numberOfArticles) {

		List<com.flowsoft.entity.Article> result = em.createQuery(

		"select a from Article a order by a.createdTS desc",
				com.flowsoft.entity.Article.class).getResultList();
		if (numberOfArticles > result.size()) {
			return WandaUtil.convertArticleListToDomain(result);
		} else {
			return WandaUtil.convertArticleListToDomain(result.subList(0,
					numberOfArticles));
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> getMostPopularArticle(Integer numberOfArticles) {
		List<com.flowsoft.entity.Article> result = em.createQuery(
				"select a from Article a order by a.rank asc",
				com.flowsoft.entity.Article.class).getResultList();
		if (numberOfArticles > result.size()) {
			return WandaUtil.convertArticleListToDomain(result);
		} else {
			return WandaUtil.convertArticleListToDomain(result.subList(0,
					numberOfArticles));
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> getMostRecommendedArticle(Integer numberOfArticles) {
		List<com.flowsoft.entity.Article> result = em.createQuery(
				"select a from Article a order by a.rank asc",
				com.flowsoft.entity.Article.class).getResultList();
		if (numberOfArticles > result.size()) {
			return WandaUtil.convertArticleListToDomain(result);
		} else {
			return WandaUtil.convertArticleListToDomain(result.subList(0,
					numberOfArticles));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> findArticleByTitle(String title, Boolean isAccurate) {
		if (!isAccurate) {
			Query query = em.createQuery(
					"SELECT e FROM Article e where title like :title")
					.setParameter("title", "%" + title + "%");
			return WandaUtil.convertArticleListToDomain(query.getResultList());
		} else {
			Query query = em.createQuery(
					"SELECT e FROM Article e where title = :title")
					.setParameter("title", title);
			return WandaUtil.convertArticleListToDomain(query.getResultList());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> findArticleByAuthor(String username, Boolean isAccurate) {
		if (!isAccurate) {
			Query query = em.createQuery(
					"SELECT e FROM WandaUser e where username like :username")
					.setParameter("username", "%" + username + "%");
			List<com.flowsoft.entity.WandaUser> userList = query
					.getResultList();

			List<com.flowsoft.entity.Article> aList = new ArrayList<com.flowsoft.entity.Article>();
			for (com.flowsoft.entity.WandaUser u : userList) {
				aList.addAll(em
						.createQuery(
								"SELECT e FROM Article e where owner = :owner")
						.setParameter("owner", u).getResultList());
			}
			return WandaUtil.convertArticleListToDomain(aList);
		} else {
			Query query = em.createQuery(
					"SELECT e FROM WandaUser e where username = :username")
					.setParameter("username", username);
			com.flowsoft.entity.WandaUser user = (com.flowsoft.entity.WandaUser) query
					.getSingleResult();

			return WandaUtil
					.convertArticleListToDomain(em
							.createQuery(
									"SELECT e FROM Article e where owner = :owner")
							.setParameter("owner", user).getResultList());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> findArticleByCategory(String categoryName,
			Boolean isAccurate) {
		if (isAccurate) {
			Query query = em.createQuery(
					"SELECT C FROM Category C where categoryName = :name")
					.setParameter("name", categoryName);
			com.flowsoft.entity.Category category = (com.flowsoft.entity.Category) query
					.getSingleResult();

			return WandaUtil
					.convertArticleListToDomain(em
							.createQuery(
									"SELECT e FROM Article e where category = :category")
							.setParameter("category", category).getResultList());
		} else {
			// TODO:
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> findArticleByContent(String contentSegment) {
		Query query = em.createQuery(
				"SELECT e FROM Article e where content like :content")
				.setParameter("content", "%" + contentSegment + "%");
		return WandaUtil.convertArticleListToDomain(query.getResultList());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Double getRank(Integer articleId) {
		logger.debug("Try to getRank with id: " + articleId);
		com.flowsoft.entity.Article a = em.find(
				com.flowsoft.entity.Article.class, articleId);
		return a.getRank();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Double setRank(Integer articleId, Double newRank) {
		com.flowsoft.entity.Article a = em.find(
				com.flowsoft.entity.Article.class, articleId);
		a.calculateRank(newRank);
		em.persist(a);
		return a.getRank();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Article> findArticleByTag(String tagname) {
		logger.debug(tagname);

		com.flowsoft.entity.Tag tag = (com.flowsoft.entity.Tag) em
				.createQuery("Select t from Tag t where t.tagName =:tagname")
				.setParameter("tagname", tagname).getSingleResult();
		logger.debug(tag.getTagName());

		List<com.flowsoft.entity.Article> arc = em
				.createQuery(
						"SELECT a FROM Article a where :tag in elements(a.tagList)")
				.setParameter("tag", tag).getResultList();
		logger.debug("Article list size: " + arc.size());
		return WandaUtil.convertArticleListToDomain(arc);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persistUser(WandaUser w) {

		em.persist(WandaUtil.convertWandaUserToEntity(w));
		em.flush();

	}

	@Override
	public String findCategoryByName(String categoryName) {
		com.flowsoft.entity.Category c = (com.flowsoft.entity.Category) em
				.createQuery(
						"SELECT c FROM Category c where categoryName = :name")
				.setParameter("name", categoryName).getSingleResult();
		return c.getDescription();
	}

	@Override
	public List<Category> findTopCategories(Integer count) {
		List<com.flowsoft.entity.Category> categoryList = em.createQuery(
				"SELECT c FROM Category c").getResultList();
		return WandaUtil.convertCategoryListToDomain(categoryList);
	}
}
