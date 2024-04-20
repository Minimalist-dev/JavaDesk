package javafx.dev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.JavaFX;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sql.GetAndSet;
import sql.insert.ImgCargada;

public class CargarImg extends JavaFX {
    public static ImageView foto = new ImageView();
    public static Text text      = new Text();
    
    private static final int ANCHO = 200;
    
    public StackPane 
    cargarImg() {
        GridPane gridPane = new GridPane();
       
        gridPane.setHgap(10);
        gridPane.setVgap(10);
//        gridPane.setGridLinesVisible(true);
        Image image = new Image(getClass().getResourceAsStream("/i/img/dev/avatar.jpg"));

        foto.setImage(image);
        foto.setFitWidth(ANCHO);
        foto.setFitHeight(ANCHO);
        foto.setPreserveRatio(true);
        foto.setImage(image);
        foto.setSmooth(true);
        foto.setCache(true); 
        Rectangle clip = new Rectangle(ANCHO, ANCHO);
        clip.setArcWidth(ANCHO);
        clip.setArcHeight(ANCHO);
        foto.setClip(clip);
        GridPane.setConstraints(foto, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        
        Button seleccionarImg = new Button("Seleccionar imagen");
        GridPane.setConstraints(seleccionarImg, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        seleccionarImg.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent eventObject) {
                if(seleccionarImg.equals(eventObject.getSource())) {
                    Img.seleccionar();
                }
            }
        });

        Img.guardar.setDisable(true);
        GridPane.setConstraints(Img.guardar, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);

        GridPane.setConstraints(text, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER);
        
        gridPane.getChildren().addAll(
            foto, seleccionarImg, Img.guardar, text
        );
        
        gridPane.getStylesheets().add("/i/css/dev/cargar_img.css");
        gridPane.getStyleClass().add("grid-pane");
        
        borderPane.setCenter(gridPane);
        
        return center;
    } 
/* L
--------------------------------------------------------------------------------*/
    static class Img {
        public static Button guardar = new Button("Guardar imagen");

        public static void 
        seleccionar() {
            FileChooser fileChooser = new FileChooser();

            configurar(fileChooser);

            File file = fileChooser.showOpenDialog(GetAndSet.getStage());

            if (file != null) {
                guardar(file);
                guardar.setDisable(false);

                try {
                    FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                    GetAndSet.setImagen(fileInputStream);

                    int longitud = longitud = (int) file.getAbsoluteFile().length();
                    GetAndSet.setLongitudDeImagen(longitud);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Img.class.getName()).log(Level.SEVERE, null, ex);
                }             
            }     
        }
        private static void 
        configurar(FileChooser fileChooser){                           
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Seleccionar Imagen", "*.png", "*.jpg", "*.gif")); 
        }
        private static void 
        guardar(File file){
            Stage stage = new Stage();

            Image image = new Image(file.toURI().toString());

            guardar.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save Image");
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); 

                    File file = fileChooser.showSaveDialog(stage);

                    if (file != null) {
                        try {
                            ImageIO.write(SwingFXUtils.fromFXImage(CargarImg.foto.getImage(), null), "jpg", file);

                            GetAndSet.setNombreDeFoto(file.getName());
                            GetAndSet.setUrlAbsoluta(file.getAbsolutePath());

                            ImgCargada.guardar();
                        } catch (IOException ex) {
                            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
                        }                              
                    }

                    guardar.setDisable(true);
                }
            });

            CargarImg.foto.setImage(image);   
        }
    }
}
