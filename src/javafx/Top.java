package javafx;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author neury-dev
 */
public class Top extends JavaFX  {
    public HBox 
    top() {
        AnchorPane anclar = new AnchorPane();
        anclar.setPrefSize(968, 100);
        
//        Label logo = new Label("Doc. Java");

        MenuBar menuBar = new MenuBar();

        Menu menuView = new Menu("View");
        
        Menu info           = new Menu("Información");
        MenuItem sobreMi    = new MenuItem("Sobre mi");        
        info.getItems().addAll(sobreMi);
        
        menuBar.getMenus().addAll(menuView, info);
//        AnchorPane.setLeftAnchor(logo, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);
//        anclar.getChildren().addAll(logo, menuBar);
        anclar.getChildren().addAll(menuBar);
        top.getChildren().addAll(anclar);
        
        top.getStylesheets().add("/i/css/top.css");
//        logo.getStyleClass().add("logo");
        
        return top;
    }
}
