package cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.Article;

@Named
@SessionScoped
public class ShoppingCartBean implements Serializable {


	private static final long serialVersionUID = -2502729164930311564L;
	
	private List<Article> shoppingCart = new ArrayList<>();
	
	public ShoppingCartBean(){
	
	}
	
	public List<Article> getShoppingCart() {
		return shoppingCart;
	}

	// pridat do kosiku
	public void addArticle(Article article) {
		this.shoppingCart.add(article);
		addMessage("Item " + article.getArticleName() + " added to shoping cart");
		System.out.println("Item " + article.getArticleName() + " added to shoping cart");
		
	}
	
	// smazat z kosiku
	public void removeArticle(Article article) {
		this.shoppingCart.remove(article);
		addMessage("Item " + article.getArticleName() + " removed from shoping cart");
		System.out.println("Item " + article.getArticleName() + " removed from shoping cart");
		
	}

	
	// zobraz kosik
	public String showShoppingCart() {
		return "shoppingCart";
	}

	public void addMessage(String summary){
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
	}

	
	// total cena 
	public double getTotalPrice(){
		
		double total=0;
		
		Iterator<Article> itr = shoppingCart.iterator();
		while(itr.hasNext()) {
			total += itr.next().getArticlePrice();
		}
		
		return total;
		
	}
	
	
}
