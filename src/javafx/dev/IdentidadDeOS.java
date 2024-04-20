package javafx.dev;

import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.JavaFX;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class IdentidadDeOS extends JavaFX {
    public static TextArea req  = new TextArea("Escoger...");
    private Label res           = new Label("");
    
    public StackPane 
    identidadDeOS() {
        final String[] string = new String[] {
            "linux", "window", "limpiar"
        };
        
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(
            "LINUX", "Window", "Limpiar"
        ));
        
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
        (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            req.clear();
            
            if(string[new_val.intValue()] == "linux") {
                res.setText("LINUX");
                OS.linux();
            } else if(string[new_val.intValue()] == "window") {
                res.setText("Window");
                OS.window();
            } else if(string[new_val.intValue()] == "limpiar") {
                res.setText("Limpiado");
                req.clear();
                req.setPromptText("Limpiado...");
            } 
        });
        
        GridPane grid = new GridPane();
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        GridPane.setConstraints(res, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(req, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(choiceBox, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);

        grid.getStylesheets().add("/i/css/dev/identidad_de_os.css");
        grid.getStyleClass().add("grid-pane");

        grid.getChildren().addAll(
            res, 
            req, 
            choiceBox
        );
        borderPane.setCenter(grid);
        return center;
    }
    
/* Clases internas
--------------------------------------------------------------------------------*/
    static class OS {
        private String nombre;
        private String valor;

        public OS() {
            
        }
        public OS(String nombre, String valor) {
            this.nombre = nombre;
            this.valor = valor;
        }
        
        public static TextArea 
        linux() {
            Map<String, String> env = System.getenv();
            Formatter formatter     = new Formatter();
            
            for (String envName : env.keySet()) {
                formatter.format("%s=%s%n", envName, env.get(envName));
                req.setText(formatter.toString());
                
//                OS res          = new OS(envName, env.get(envName));
//                String nombre   = res.nombre;
//                String valor    = res.valor;
                
//                req.appendText(nombre + " : " + valor + "\n");
//                System.out.format("%s=%s%n", envName, env.get(envName));
//                System.out.println(formatter);
            }
            
            return req;
        }
        public static TextArea
        window() {
            try {
                Process process = Runtime.getRuntime().exec("cmd /C net user %USERNAME%");
                
                try (Scanner scanner = new Scanner(process.getInputStream())) {
                    scanner.useDelimiter("\\A");
                    
                    if (scanner.hasNext()) {
                        req.appendText(scanner.next());
//                        System.out.println(scanner.next());
                    }
                }
            }   catch (IOException ex) {
                Logger.getLogger(OS.class.getName()).log(Level.SEVERE, null, ex);
                req.appendText(ex.toString());
            }
            
            return req;
        }
        @Override
        public String 
        toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("OS{nombre=").append(nombre);
            sb.append(", valor=").append(valor);
            sb.append('}');
            return sb.toString();
        }
    }
}
