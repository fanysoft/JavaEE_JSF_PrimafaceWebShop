package bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.primefaces.event.SelectEvent;

import javax.validation.constraints.NotNull;

import business.ArticleService;
import model.Article;

@Model
public class ArticleBean implements Serializable {

	private static final long serialVersionUID = -4080308091985560927L;
	
	
	private int articlePrice;
	private String articleNumber;
	private String articleName;
	private String articleDescription;
	
	// for Search field
	private Article selectedArticle;
	private String selectedArticleString;
	private List<Article> articles = new ArrayList<Article>();
	
	
	
	// EJB 
	@Inject
	ArticleService articleService;
		
	// Default Constructor
	public ArticleBean() {
		super();
	}
	
	
	@PostConstruct
	public void init(){
		// get all articles
		articles = articleService.getArticles();
	
	}
	
	// search - autocomplete - String 
	 public List<String> completeText(String query) {
	       
		 List<String> suggestions = new ArrayList<String>();
			
			for(Article article : articles) {
				
				System.out.println("Search field - looking for: "+query);
				
				if(article.getArticleName().startsWith(query) || article.getArticleDescription().startsWith(query)) {
					
					suggestions.add(article.getArticleName());
					System.out.println("Search field - adding suggestion: "+ article);
					
				}
			}
			
			return suggestions;
	   }
	 

	

// Getters and Setters
	
	// 1.ArticleNumber 
	
	// format "xx-1234567890"
	@Pattern(regexp="[A-Z]{2}-[0-9]{1,10}$", message="Number : Format AA-1111111")
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	// 2.ArticleName

	@NotNull(message="Name : Must be inserted") 									// nenulove
	@Pattern(regexp="[a-zA-Z0-9-_\\s+]{3,30}$", message="Name : Invalid characters inserted") 	// povolene znaky : male, Velke, cisla a -,_, 	$ = konec Stringu
	@Size(min=3, max=30, message="Name : Invalid lenght (should be 3-30)")			// delka Stringu min 3 a max 30
	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	
	// 3.ArticlePrice
	
	@Min(1)  	// hodnota min 1
	public int getArticlePrice() {
		return articlePrice;
	}


	public void setArticlePrice(int articlePrice) {
		this.articlePrice = articlePrice;
	}


	// 4. ArticleDescription
	
	public String getArticleDescription() {
		return articleDescription;
	}

	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	public String save() {
		
		System.out.println("Saving..." + this.articleName + " " + this.articleDescription);
		
		Article article = new Article();
 		article.setArticleNumber(articleNumber);
		article.setArticlePrice(articlePrice);
		article.setArticleDescription(articleDescription);
		article.setArticleName(articleName);
		
		// EJB
		articleService.save(article);
		
		addMessage("New Product created");
		return("index");
	}	
	
	
	public void addMessage(String summary){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null,message);
	}


	
	// Selected
	
     public void onItemSelect(SelectEvent event) throws IOException {
		   String selected = event.getObject().toString();
		   addMessage("Item Selected " + selected );
	       FacesContext.getCurrentInstance().getExternalContext().redirect("articleSingle.xhtml");
	       System.out.println("Item Selected from method " + selected); 
	   }
	  
     	
	
	public String getSelectedArticleString() {
			return selectedArticleString;
	}


	public void setSelectedArticleString(String selectedArticleString) {
			this.selectedArticleString = selectedArticleString;
	}
		
		

	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	
	

}
