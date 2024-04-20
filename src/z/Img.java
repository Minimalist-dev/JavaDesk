package z;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.dev.CargarImg;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sql.GetAndSet;
import sql.insert.ImgCargada;

public class Img {
//    public static Button guardar = new Button("Guardar imagen");
//    
//    public static void 
//    seleccionar() {
//        FileChooser fileChooser = new FileChooser();
//        
//        configurar(fileChooser);
//        
//        File file = fileChooser.showOpenDialog(GetAndSet.getStage());
//        
//        if (file != null) {
//            guardar(file);
//            guardar.setDisable(false);
//
//            try {
//                FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
//                GetAndSet.setImagen(fileInputStream);
//                
//                int longitud = longitud = (int) file.getAbsoluteFile().length();
//                GetAndSet.setLongitudDeImagen(longitud);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Img.class.getName()).log(Level.SEVERE, null, ex);
//            }             
//        }     
//    }
//    private static void 
//    configurar(FileChooser fileChooser){                           
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 
//        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Seleccionar Imagen", "*.png", "*.jpg", "*.gif")); 
//    }
//    private static void 
//    guardar(File file){
//        Stage stage = new Stage();
//
//        Image image = new Image(file.toURI().toString());
//
//        guardar.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent event) {
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Save Image");
//                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 
//                
//                File file = fileChooser.showSaveDialog(stage);
//                
//                if (file != null) {
//                    try {
//                        ImageIO.write(SwingFXUtils.fromFXImage(CargarImg.foto.getImage(), null), "jpg", file);
//
//                        GetAndSet.setNombreDeFoto(file.getName());
//                        GetAndSet.setUrlAbsoluta(file.getAbsolutePath());
//                        
//                        ImgCargada.guardar();
//                    } catch (IOException ex) {
//                        Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
//                    }                              
//                }
//                
//                guardar.setDisable(true);
//            }
//        });
//
//        CargarImg.foto.setImage(image);   
//    }
}
