package menu;
 
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
 
@ManagedBean
public class NavigationBean {
     
    private MenuModel model;
 
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
         
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Main");
         
        DefaultMenuItem item = new DefaultMenuItem("Home");
        item.setIcon("ui-icon-home");
        item.setOutcome("index");
        firstSubmenu.addElement(item);
         
        model.addElement(firstSubmenu);
         
        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Article");
 
        item = new DefaultMenuItem("Create Article ..");
        item.setIcon("ui-icon-plusthick");
        item.setOutcome("articleCreate");
	    secondSubmenu.addElement(item);
         
        item = new DefaultMenuItem("List Articles");
        item.setIcon("ui-icon-carat-2-n-s");
        item.setOutcome("articleList");
        secondSubmenu.addElement(item);
         
        model.addElement(secondSubmenu);
        
        //Third submenu
        DefaultSubMenu thirdSubmenu = new DefaultSubMenu("Shopping Cart");
 
        item = new DefaultMenuItem("Shopping Cart");
        item.setIcon("ui-icon-suitcase");
        item.setOutcome("shoppingCart");
        thirdSubmenu.addElement(item);
         
        model.addElement(thirdSubmenu);
        
    }
 
    public MenuModel getModel() {
        return model;
    }   
     
   
}