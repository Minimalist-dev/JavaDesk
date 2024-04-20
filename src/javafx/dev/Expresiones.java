package javafx.dev;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.JavaFX;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Expresiones extends JavaFX {
    protected Expresion expresion;
    
    private TextField req   = new TextField("");
    private Label res       = new Label("");
    
    
    public StackPane 
    expresiones() {
        final String[] string = new String[] {
            "texto", "numero"
        };
        
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(
            "Texto", "Numero"
        ));
        
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                if(string[new_val.intValue()] == "texto") {
                    res.setText("");
                    
                    if(expresion.texto(req.getText())) {
                        res.setText("Coincide");
                    } else {
                        res.setText("No coincide");
                    }
                } else if(string[new_val.intValue()] == "numero") {
                    res.setText("");
                    
                    if(expresion.numero(req.getText())) {
                        res.setText("Coincide");
                    } else {
                        res.setText("No coincide");
                    }
                } 
        });
        
        GridPane grid = new GridPane();
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        GridPane.setConstraints(res, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(req, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(choiceBox, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);

        grid.getStylesheets().add("/i/css/dev/expresiones.css");
        grid.getStyleClass().add("grid-pane");
        res.setAlignment(Pos.CENTER);

        grid.getChildren().addAll(
            res, 
            req, 
            choiceBox
        );
        borderPane.setCenter(grid);
        
        return center;
    }
    
    static class Expresion {
        public static boolean 
        texto(String expresionObject) {
            Pattern pattern = Pattern.compile("^[a-zA-Z]{1,}$");
            Matcher matcher = pattern.matcher(expresionObject);

            return matcher.find();
        }
        public static boolean 
        numero(String expresionObject) {
            Pattern pattern = Pattern.compile("^[0-9]{1,}$");
            Matcher matcher = pattern.matcher(expresionObject);

            return matcher.find();
        }
    }
}
