package javafx.dev;

import javafx.JavaFX;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class Formulario extends JavaFX {
    private Button enviar     = new Button ("Enviar");
    private Label res         = new Label ("");
    private TextField asunto  = new TextField("");
    private TextArea mensaje  = new TextArea ("");
    
    public String email = " ";
    
    public StackPane 
    formulario() {
        final ComboBox comboBoxEmail = new ComboBox();
        
        comboBoxEmail.getItems().addAll(
            "jacob.smith@example.com",
            "isabella.johnson@example.com",
            "ethan.williams@example.com",
            "emma.jones@example.com",
            "michael.brown@example.com"  
        );
        
        comboBoxEmail.setPromptText("Email address");
        comboBoxEmail.setEditable(true);        
        comboBoxEmail.setOnAction(new EventHandler() {
            @Override public void 
            handle(Event ev) {
                email = comboBoxEmail.getSelectionModel().getSelectedItem().toString();
            }
        });
        
        final ComboBox comboBox = new ComboBox();
        
        comboBox.getItems().addAll(
            "Mas Alto",
            "Alto",
            "Normal",
            "Bajo",
            "Mas Bajo" 
        );   

        comboBox.setValue("Normal");
        comboBox.setCellFactory(
            new Callback<ListView<String>, ListCell<String>>() {
                @Override public ListCell<String> call(ListView<String> param) {
                    final ListCell<String> cell = new ListCell<String>() {
                        {
                            super.setPrefWidth(100);
                        }    
                        @Override public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                
                                if (item != null) {
                                    setText(item);    
                                    if (item.contains("Alto")) {
                                        setTextFill(Color.RED);
                                    } else if (item.contains("Bajo")){
                                        setTextFill(Color.GREEN);
                                    } else {
                                        setTextFill(Color.BLACK);
                                    }
                                } else {
                                    setText(null);
                                }
                            }
                };
                    
                return cell;
            }
        });
        
        enviar.setOnAction((ActionEvent e) -> {
            if (comboBoxEmail.getValue() != null && !comboBoxEmail.getValue().toString().isEmpty()) {
                res.setText("Su mensaje fue enviado con éxito" + " a " + email);
                comboBoxEmail.setValue(null);
                
                if (comboBox.getValue() != null && !comboBox.getValue().toString().isEmpty()) {
                    comboBox.setValue(null);
                }
                
                asunto.clear();
                mensaje.clear();
            } else {
                res.setText("No has seleccionado un destinatario!");
            }
        });
        
        GridPane grid = new GridPane();
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Para: "), 0, 0);
        grid.add(comboBoxEmail, 1, 0);
        grid.add(new Label("Prioridad: "), 2, 0);
        grid.add(comboBox, 3, 0);
        grid.add(new Label("Asunto: "), 0, 1);
        grid.add(asunto, 1, 1, 3, 1);            
        grid.add(mensaje, 0, 2, 4, 1);
        grid.add(enviar, 0, 3);
        grid.add (res, 1, 3, 3, 1);

        grid.getStylesheets().add("/i/css/dev/formulario.css");
        grid.getStyleClass().add("grid-pane");
        
        borderPane.setCenter(grid);
        
        return center;
    } 
}
