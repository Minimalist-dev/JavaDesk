package javafx.dev;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.JavaFX;
import static javafx.JavaFX.borderPane;
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

public class Anagramas extends JavaFX {
    private static TextField req                = new TextField("Anagramas");
    private static ToggleButton toggleButton    = new ToggleButton ("Resolver");
    private static TextArea textArea            = new TextArea();
    
    public StackPane 
    anagramas() {
        toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void
            handle(ActionEvent actionEvent) {
                textArea.clear();
                
                if(toggleButton.isSelected()) {
                    resolver(req.getText());
                } else {
                    extraer();
                    extraerMas();
                }
            }
        });

        textArea.setWrapText(true);
        textArea.setText("Los anagramas se pueden usar como logotipos, nombre, frases en clave, seudónimos de autor y "
            + "mensajes ocultos. Son un divertido juego de palabras, como acertijos, además de un reto intelectual para "
            + "reorganizar las letras y encontrar nuevas frases así como significados dentro de ellas."
        );
        
        GridPane grid = new GridPane();
        
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        GridPane.setConstraints(req, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(toggleButton, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER);
        GridPane.setConstraints(textArea, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER);

        grid.getStylesheets().add("/i/css/dev/center.css");
        grid.getStyleClass().add("center");

        grid.getChildren().addAll(
            req,
            toggleButton,
            textArea
        );
        borderPane.setCenter(grid);
        
        return center;
    }
    private static String 
    alphabetize(String s) {
        char[] a = s.toCharArray();
        
        Arrays.sort(a);
        
        return new String(a);
    }
    private static void 
    resolver(String frase) {
        int minGroupSize            = Integer.parseInt("1");
        // Read words from file and put into a simulated multimap
        Map<String, List<String>>   m = new HashMap<String, List<String>>();

        Scanner s = new Scanner(frase);
        
        while (s.hasNext()) {
            String word     = s.next();
            String alpha    = alphabetize(word);
            List<String> l  = m.get(alpha);

            if(l == null)
                m.put(alpha, l = new ArrayList<String>());
            l.add(word);
        }

        // Print all permutation groups above size threshold
        for (List<String> l : m.values())
            if (l.size() >= minGroupSize)
                textArea.appendText(l.size() + ": " + l + "\n");
    }
    private static void 
    extraer() {
        int minGroupSize            = Integer.parseInt("1");
        // Read words from file and put into a simulated multimap
        Map<String, List<String>>   m = new HashMap<String, List<String>>();

        try {
            Scanner s = new Scanner(new File("/home/neury-dev/Documentos/dictionary.txt"));
            
            while (s.hasNext()) {
                String word     = s.next();
                String alpha    = alphabetize(word);
                List<String> l  = m.get(alpha);
                
                if(l == null)
                    m.put(alpha, l = new ArrayList<String>());
                l.add(word);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        // Print all permutation groups above size threshold
        for (List<String> l : m.values()) {
            if (l.size() >= minGroupSize)
//                textArea.appendText(l.size() + ": " + l + "\n"); //Demasiada carga.
                System.out.println(l.size() + ": " + l);
        }
    }
    private static void
    extraerMas() {
        int minGroupSize            = Integer.parseInt("8");
        // Read words from file and put into simulated multimap
        Map<String, List<String>> m = new HashMap<String, List<String>>();
        
        try {
            Scanner s = new Scanner(new File("/home/neury-dev/Documentos/dictionary.txt"));
            
            while (s.hasNext()) {
		String word     = s.next();
                String alpha    = alphabetize(word);
                List<String> l  = m.get(alpha);
                
                if (l == null)
                    m.put(alpha, l=new ArrayList<String>());
                l.add(word);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        // Make a List of all permutation groups above size threshold
        List<List<String>> winners = new ArrayList<List<String>>();
        
        for (List<String> l : m.values())
            if (l.size() >= minGroupSize)
                winners.add(l);

        // Sort permutation groups according to size
        Collections.sort(winners, new Comparator<List<String>>() {
            public int compare(List<String> o1, List<String> o2) {
                return o2.size() - o1.size();
            }});

        // Print permutation groups
        for (List<String> l : winners ) {
            textArea.appendText(l.size() + ": " + l + "\n");
//            System.out.println(l.size() + ": " + l);
        }
    }
}
