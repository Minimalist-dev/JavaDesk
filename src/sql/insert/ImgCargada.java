package sql.insert;

import javafx.dev.CargarImg;
import sql.GetAndSet;
import sql.Usuario;

public class ImgCargada {
    public static boolean 
    guardar() {
        String sql = "INSERT INTO Imagen VALUES(NULL, 'Cargar Imagen', '" + 
            GetAndSet.getNombreDeFoto() + "', '" + GetAndSet.getUrlAbsoluta() + "', NOW())";
        
        if(Usuario.executeUpdate(sql) == 1) { CargarImg.text.setText("Guardada con exitosa."); } 
        else { CargarImg.text.setText("Error de guardado."); }

        return false;   
    }
}
