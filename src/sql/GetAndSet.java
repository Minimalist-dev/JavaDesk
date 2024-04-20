package sql;

import java.io.File;
import java.io.FileInputStream;
import javafx.stage.Stage;

public class GetAndSet {

    private static int id;
    private static String usuario;
    private static String codigo;
    private static Stage stage;
    private static File fila;
    private static FileInputStream imagen;
    private static int longitudDeImagen;
    private static String nombreDeFoto;
    private static String nombreDeImagen;
    private static String urlAbsoluta;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        GetAndSet.id = id;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        GetAndSet.usuario = usuario;
    }

    public static String getCodigo() {
        return codigo;
    }

    public static void setCodigo(String codigo) {
        GetAndSet.codigo = codigo;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GetAndSet.stage = stage;
    }

    public static File getFila() {
        return fila;
    }

    public static void setFila(File fila) {
        GetAndSet.fila = fila;
    }

    public static FileInputStream getImagen() {
        return imagen;
    }

    public static void setImagen(FileInputStream imagen) {
        GetAndSet.imagen = imagen;
    }

    public static int getLongitudDeImagen() {
        return longitudDeImagen;
    }

    public static void setLongitudDeImagen(int longitudDeImagen) {
        GetAndSet.longitudDeImagen = longitudDeImagen;
    }

    public static String getNombreDeFoto() {
        return nombreDeFoto;
    }

    public static void setNombreDeFoto(String nombreDeFoto) {
        GetAndSet.nombreDeFoto = nombreDeFoto;
    }

    public static String getNombreDeImagen() {
        return nombreDeImagen;
    }

    public static void setNombreDeImagen(String nombreDeImagen) {
        GetAndSet.nombreDeImagen = nombreDeImagen;
    }

    public static String getUrlAbsoluta() {
        return urlAbsoluta;
    }

    public static void setUrlAbsoluta(String urlAbsoluta) {
        GetAndSet.urlAbsoluta = urlAbsoluta;
    }
    
}
