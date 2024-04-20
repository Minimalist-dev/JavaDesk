package javafx.dev;

import javafx.JavaFX;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class InvertirArray extends JavaFX {
    private static TextArea textArea = new TextArea();
    
    public StackPane 
    invertirArray() {
        textArea.clear();
        numeros();
        palabras();
    
        GridPane grid = new GridPane();
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        GridPane.setConstraints(textArea, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        grid.getStylesheets().add("/i/css/dev/center.css");
        grid.getStyleClass().add("center");
        
        grid.getChildren().addAll(
            textArea
        );
        borderPane.setCenter(grid);
        return center;
    }
    private static void 
    numero(int[] array){
        String cadena = "Numeros de Array: ";
        
        for (int i = 0; i < array.length; i++) {
            cadena += array[i] + ", ";
        }
        
        cadena = cadena.substring(0, cadena.length() -2);
        
        textArea.appendText(cadena + "\n");
    }
    private static void 
    texto(String[] array){
        String cadena = "Palabras de Array: ";
        
        for (int i = 0; i < array.length; i++) {
            cadena += array[i] + ", ";
        }
        
        cadena = cadena.substring(0, cadena.length() -2);
        
        textArea.appendText(cadena + "\n");
    }
    private static void 
    numeros() {
        int aux;
        int[] numeros = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        
        numero(numeros);
        
        /* 1ª forma: Sin array auxiliar
        -----------------------------------------------------------------------*/
        for (int i = 0; i < numeros.length/2; i++) {
            aux = numeros[i];
            numeros[i] = numeros[numeros.length -1 -i];
            numeros[numeros.length -1 -i] = aux;
        }
        
        numero(numeros);
 
        /* 2ª forma: Con array auxiliar, for 1 variable
        -----------------------------------------------------------------------*/
        int[] invertido = new int[numeros.length];
        
        for(int i = 0; i < numeros.length; i++){
            invertido[i] = numeros[numeros.length -1 -i];
        }
        
        numeros = invertido;
        
        numero(numeros);
 
        /* 3ª forma: Con array auxiliar, for 2 variables
        -----------------------------------------------------------------------*/
        invertido = new int[10];
        
        for (int i = 0, j = numeros.length-1; i < numeros.length; i++, j--) {
            invertido[j] = numeros[i];
        }
        
        numeros = invertido;
        
        numero(numeros);
    }
    private static void 
    palabras() {
        String aux;
        String[] numeros = { "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez" };
        
        texto(numeros);

        /* 1ª forma: Sin array auxiliar
        -----------------------------------------------------------------------*/
        for (int i = 0; i < numeros.length/2; i++) {
            aux = numeros[i];
            numeros[i] = numeros[numeros.length -1 -i];
            numeros[numeros.length -1 -i] = aux;
        }
        
        texto(numeros);
 
        /* 2ª forma: Con array auxiliar, for 1 variable
        -----------------------------------------------------------------------*/
        String[] invertido = new String[numeros.length];
        
        for(int i = 0; i < numeros.length; i++){
            invertido[i] = numeros[numeros.length -1 -i];
        }
        
        numeros = invertido;
        
        texto(numeros);
 
        /* 3ª forma: Con array auxiliar, for 2 variables
        -----------------------------------------------------------------------*/
        invertido = new String[10];
        
        for (int i = 0, j = numeros.length-1; i < numeros.length; i++, j--) {
            invertido[j] = numeros[i];
        }
        
        numeros = invertido;
        
        texto(numeros);
    }
}

