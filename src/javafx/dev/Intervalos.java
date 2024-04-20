package javafx.dev;

import java.util.Iterator;
import javafx.JavaFX;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/* Filtral datos, personalizar funciones rando...
--------------------------------------------------------------------------------*/
public class Intervalos extends JavaFX {
    
    private static TextField req1                = new TextField("0");
    private static TextField req2               = new TextField("2");
    private static ToggleButton toggleButton    = new ToggleButton ("Resolver");
    private static TextArea textArea            = new TextArea();
    
    public StackPane 
    intervalos() {
        toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void
            handle(ActionEvent actionEvent) {
                textArea.clear();

                Intervalos ds = new Intervalos();
                ds.printEven();
            }
        });

        textArea.setWrapText(true);
        textArea.setText("Silve para filtral datos, personalizar funciones rando...");
        
        GridPane grid = new GridPane();
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        GridPane.setConstraints(textArea, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(req1, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(req2, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(toggleButton, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER);

        grid.getStylesheets().add("/i/css/dev/center.css");
        grid.getStyleClass().add("center");

        grid.getChildren().addAll(
            textArea,
            req1,
            req2,
            toggleButton
            
        );
        borderPane.setCenter(grid);
        
        return center;
    }
    
    /** Ejemplo de clase interna */
    // Create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];
    
    public Intervalos() {
        // llenar la matriz con valores enteros ascendentes
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }
    
    public void printEven() {
        
        // Imprimir los valores de los índices pares de la matriz
        IntervalosIterator iterator = this.new EvenIterator();
        
        while (iterator.hasNext()) {
            textArea.appendText(iterator.next() + " ");
//            System.out.print(iterator.next() + " ");
        }
//        System.out.println();
    }
    
    interface IntervalosIterator extends Iterator<Integer> { } 
    // La clase interna implementa la interfaz IntervalosIterator,
    // que extiende la interfaz Iterator <Integer>
    private class EvenIterator implements IntervalosIterator {
        // Comenzar a recorrer la matriz desde el principio
        private int nextIndex = Integer.parseInt(req1.getText());
        
        public boolean hasNext() {
            // Comprueba si el elemento actual es el último en la matriz
            return (nextIndex <= SIZE - 1);
        }        
        
        public Integer next() {
            // Registrar un valor de un índice par de la matriz
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);
            // Obtener el siguiente elemento par
            nextIndex += Integer.parseInt(req2.getText());
            return retValue;
        }
    }
}
