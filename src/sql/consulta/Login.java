package sql.consulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import l.DesarrollosController;
import sql.GetAndSet;
import sql.Usuario;

public class Login {
    public static String error;
    
    public static boolean 
    sesion(String usuario, String codigo) {
        GetAndSet.setUsuario(usuario);
        GetAndSet.setCodigo(codigo);
        
        if (GetAndSet.getUsuario() == "" || GetAndSet.getCodigo() == ""){
            error = "Los datos no coinciden.";
        } else {
            String sql = "SELECT correo, codigo FROM Login WHERE correo = ? AND codigo = ? LIMIT 1";
            
            try {
                PreparedStatement preparedObject = Usuario.getConexion().prepareStatement(sql);
                preparedObject.setString(1, GetAndSet.getUsuario());
                preparedObject.setString(2, GetAndSet.getCodigo());
                
                ResultSet resultSetObject = preparedObject.executeQuery();

                if (resultSetObject.next()) {
                    if (
                        GetAndSet.getUsuario().equals(resultSetObject.getString(1)) && 
                        GetAndSet.getCodigo().equals(resultSetObject.getString(2))
                    ) {
                        error = "";

                        DesarrollosController dev = new DesarrollosController();
                        dev.loginSesion();
                        
                        return true;
                    } else {
                        return false;
                    }
                } else { 
                    error = "Los datos son incorrectos"; 
                }

                return false;
            } catch(SQLException sqlError) {
                error = "No hay conexion"; return false; 
            } finally { 
                try {
                    Usuario.getConexion().close();
                } catch (SQLException sqleObject) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, sqleObject);
                }
            }
        }
        
        return false;   
    }
}
