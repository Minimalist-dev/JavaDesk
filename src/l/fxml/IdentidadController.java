package l.fxml;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import l.fx.Identidad;

/**
 *
 * @author neury
 */
public class IdentidadController implements Initializable {
   
    @FXML private ListView<String> listView;

    /** Initializes the controller class...........................................*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList observableList = FXCollections.observableArrayList();
        
        Label label = new Label("PC");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//        label.setStyle("-fx-text-fill: red;-fx-font-weight: bold;");
        observableList.addAll(
            label.getText() + ": " + Identidad.pc(),
            "Usuario: "     + Identidad.usuario(),
            "IP local: "    + Identidad.ipLocal(),
            "Idioma: "      + Identidad.idioma(),
            "Origen: "      + Identidad.origen(),
            "Zona: "        + ZoneId.systemDefault(),
            "IPv4: "        + Identidad.ipv4(),
            "IPv6: "        + Identidad.ipv6(),
            "MACName: "     + Identidad.macName()
        );
//        for(String im : ZoneId.getAvailableZoneIds()) {
//            System.out.println(im);
//        }
        listView.setItems(observableList);
        
        listView.setCellFactory(
            new Callback<ListView<String>, ListCell<String>>() {
                @Override public ListCell<String> call(ListView<String> param) {
                    final ListCell<String> cell = new ListCell<String>() {
                        {
                            super.setPrefWidth(100);
                        }    
                        @Override public void updateItem(String item, 
                            boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);    
                                    if (item.contains("IP")) {
                                        setTextFill(Color.RED);
                                    }
                                    else if (item.contains("Low")){
                                        setTextFill(Color.GREEN);
                                    }
                                    else {
                                        setTextFill(Color.BLACK);
                                    }
                                }
                                else {
                                    setText(null);
                                }
                            }
                };
                return cell;
            }
        });
    } 
}
