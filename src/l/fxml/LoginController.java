package l.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sql.consulta.Login;

/**
 * Login Controller.
 */
public class LoginController extends AnchorPane implements Initializable {

    @FXML private Label error;
    @FXML private TextField usuario;
    @FXML private PasswordField codigo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setText("");
        usuario.setPromptText("Usuario");
        codigo.setPromptText("Contraseña");
    }

    @FXML 
    public void 
    login(ActionEvent event) {
        Login.sesion(usuario.getText(), codigo.getText());
        error.setText(Login.error);
    }
}
