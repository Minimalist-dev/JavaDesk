package l.fxml;

import l.fx.Tabla;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import l.fx.Paginacion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DateTimeStringConverter;
import sql.Usuario;

public class SistemaMySQLController implements Initializable {
    
    @FXML private TextField entradaId;
    @FXML private TextField entradaNombre;
    @FXML private TextField entradaMarca;
    @FXML private TextField entradaPrecio;
    @FXML private Label res;
    
    @FXML private TableView<Tabla> tabla;
    @FXML private TableColumn<Tabla, Integer> columnaId;
    @FXML private TableColumn<Tabla, String> columnaNombre;
    @FXML private TableColumn<Tabla, String> columnaMarca;
    @FXML private TableColumn<Tabla, Integer> columnaPrecio;
    @FXML private TableColumn<Tabla, String> columnaFecha;
    
    @FXML private Button insertar;
    @FXML private Button actualizar;
    @FXML private Button eliminar;
    @FXML private Button limpiar;
    @FXML private Button anterior;
    @FXML private Button siguiente;
    @FXML private TextField paginaciones;
    
    private String sql;
    private DateTimeStringConverter colocarFormato  = new DateTimeStringConverter("yy-MM-dd HH:mm:ss");
    private Date fecha                              = new Date();
    private DateTimeStringConverter obtenerFormato  = new DateTimeStringConverter("dd/MM/yy");
    private Paginacion paginacion                   = new Paginacion();
    private int limite;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        columnas(0);
        limite();
        
        entradaNombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String viejo, String nuevo) {
                dasactivar(false, true, true);
            }
        });
        
        dasactivar(true, true, true);
    }    
    @FXML public void 
    handle(ActionEvent event) {
        if(event.getSource() == insertar) {
            insertar(); 
            columnas(0);
            limpiar();
        } else if(event.getSource() == actualizar) {
            actualizar(); 
            columnas(0);
            limpiar();
        } else if(event.getSource() == eliminar) {
            eliminar(); 
            columnas(0);
            limpiar();
        } else if(event.getSource() == limpiar) {
            limpiar();
        } else if(event.getSource() == anterior) {
            anterior();
        } else if(event.getSource() == siguiente) {
            siguiente();
        }
    }
    @FXML public void 
    handleTabla(MouseEvent event) {
        Tabla tab = tabla.getSelectionModel().getSelectedItem();
        
        entradaId.setText("" + tab.getId());
        entradaNombre.setText("" + tab.getNombre()); 
        entradaMarca.setText("" + tab.getMarca());
        entradaPrecio.setText("" + tab.getPrecio());
        res.setText("Fila seleccionada.");
        
        dasactivar(true, false, false);
    }
    public void 
    columnas(int pagina) {
        ObservableList<Tabla> lista;

        if(pagina == 0) {
            lista = filas(0);
        } else {
            lista = filas(pagina);
        }
        
        columnaId.setCellValueFactory(new PropertyValueFactory<Tabla, Integer>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Tabla, String>("nombre"));
        columnaMarca.setCellValueFactory(new PropertyValueFactory<Tabla, String>("marca"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<Tabla, Integer>("precio"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<Tabla, String>("fecha"));
        
        tabla.setItems(lista);
    }
    public ObservableList<Tabla> 
    filas(int pagina) {
        ObservableList<Tabla> lista = FXCollections.observableArrayList();

        if(pagina == 0) {
            sql = "SELECT * FROM SistemaMySQL LIMIT 10";
        } else {
            sql = "SELECT * FROM SistemaMySQL LIMIT 10 OFFSET " + pagina;
        }
        
        try {
            ResultSet res = Usuario.getStatement().executeQuery(sql);
            
            Tabla tabla;
            
            while(res.next()) {
                fecha = colocarFormato.fromString(res.getString("fecha"));

                tabla = new Tabla(
                    res.getInt("id"), 
                    res.getString("nombre"), 
                    res.getString("marca"), 
                    res.getDouble("precio"), 
                    obtenerFormato.toString(fecha)
                );
                
                lista.add(tabla);
            }
        } catch(Exception error) {
            error.printStackTrace();
        }
        return lista;
    } 
    private void 
    insertar() {
        String sql = "INSERT INTO SistemaMySQL VALUES(NULL, '" 
            + entradaNombre.getText() + "', '" 
            + entradaMarca.getText() + "', " 
            + entradaPrecio.getText() + ", " 
            + "NOW())";
        
        Usuario.executeUpdate(sql);
        
        if(Usuario.executeUpdate(sql) == 1) {
            res.setText("Inserción exitosa.");
        } else {
            res.setText("Error de inserción.");
        }
        
        dasactivar(true, true, true);
    }
    private void 
    actualizar() {
        String sql = "UPDATE SistemaMySQL SET "
            + "nombre   = '" + entradaNombre.getText() + "', "
            + "marca    = '" + entradaMarca.getText()  + "', "
            + "precio   = "  + entradaPrecio.getText() + " WHERE id = " + entradaId.getText();
        
        Usuario.executeUpdate(sql);
       
        if(Usuario.executeUpdate(sql) == 1) {
            res.setText("Actualización exitosa.");
        } else {
            res.setText("Error de actualización.");
        }
        
        dasactivar(true, true, true);
    }
    private void 
    eliminar() {
        String sql = "DELETE FROM SistemaMySQL WHERE id = " + entradaId.getText();
        
        int estado = Usuario.executeUpdate(sql);
        
        if(estado == 1) {
           res.setText("Eliminación exitosa.");
        } else {
           res.setText("Error de eliminación.");
        }
        
        dasactivar(true, true, true);
    }
    private void 
    limpiar() {
        entradaId.setText("");
        entradaNombre.setText(""); 
        entradaMarca.setText("");
        entradaPrecio.setText("");
        res.setText("");
        
        dasactivar(true, true, true);
    }
    private void 
    dasactivar(Boolean a, Boolean e, Boolean i) {
        insertar.setDisable(a);
        actualizar.setDisable(e);
        eliminar.setDisable(i);
    }
    public void
    limite() {
        String sql = "SELECT COUNT(id) AS limite FROM SistemaMySQL";

        try {
            ResultSet res = Usuario.getStatement().executeQuery(sql);
            
            while(res.next()) {
                limite = res.getInt("limite");
            }
        } catch (SQLException error) {
            Logger.getLogger(SistemaMySQLController.class.getName()).log(Level.SEVERE, null, error);
        }
    }
    private void 
    anterior() {
        columnas(paginacion.anterior());  
        paginaciones();
    }
    private void 
    siguiente() {
        columnas(paginacion.siguiente());
        paginaciones();
    }
    public void
    paginaciones() {
        int paginas = (int) Math.ceil(limite / paginacion.filasPorPagina());
        
        if(paginacion.getPaso() > 0) {
            paginaciones.setText((paginacion.getPaso() + 1) + "/" + (paginas + 1));
        } else {
            paginaciones.setText("1/" + (paginas + 1));
        }
        
        if(paginacion.getPaso() <= 0) {
            anterior.setDisable(true);
        } else if(paginacion.getPaso() >= paginas) {
            siguiente.setDisable(true);
        } else {
            anterior.setDisable(false);
            siguiente.setDisable(false);
        }
    }
}
