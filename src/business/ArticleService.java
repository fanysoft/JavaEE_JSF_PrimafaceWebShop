package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Article;

@Stateless
public class ArticleService {

	public ArticleService() {
		super();
	}
	
	@PersistenceContext
	EntityManager em;
	
	public void save(Article article) {
		em.merge(article);
	}
	
	public List<Article> getArticles() {
		TypedQuery<Article> query = em.createNamedQuery(Article.GET_ALL_ARTICLE, Article.class);
		return query.getResultList();
	}
	
	public Article getArticleById(int id) {
		TypedQuery<Article> query = em.createNamedQuery(Article.GET_ARTICLE_BY_ID, Article.class);
		return query.setParameter("id", id).getSingleResult();
	}
}
