package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries(
		{
			@NamedQuery(name=Article.GET_ARTICLE_BY_ID, query="Select a From Article a where a.id = :id"),
			@NamedQuery(name=Article.GET_ALL_ARTICLE, query="Select a From Article a")
		}
)
@Entity
@Table(name="article")
public class Article {

	public static final String GET_ALL_ARTICLE 		= "Article.get_all_articles";
	public static final String GET_ARTICLE_BY_ID	= "Article.get_article_by_id";
	
	private int id;
	private String articleNumber;  
	private String articleName;
	private String articleDescription;
	private double articlePrice;
	
	public Article() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	
	public String getArticleName() {
		return articleName;
	}
	
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	
	public String getArticleDescription() {
		return articleDescription;
	}
	
	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}


	public double getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(double articlePrice) {
		this.articlePrice = articlePrice;
	}
}
