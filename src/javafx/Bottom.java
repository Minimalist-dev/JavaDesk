package javafx;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author neury-dev
 */
public class Bottom extends JavaFX {
    public HBox 
    bottom() {
        VBox vbox = new VBox();
        
        Label desarrollo    = new Label("Desarrollo de Neury");
        Label correo        = new Label("neury.developer@gmail.com");

        vbox.getChildren().addAll(desarrollo, correo);
        bottom.getChildren().add(vbox);
        
        bottom.getStylesheets().add("/i/css/bottom.css");
        vbox.getStyleClass().add("v-box");
        
        return bottom;
    }
}
