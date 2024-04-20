package javafx;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

/**
 *
 * @author neury-dev
 */
public class Center extends JavaFX {
    private Pane pane           = new Pane();
    private GridPane gridPane   = new GridPane();
    private Pane grafico        = new Pane();

    private SVGPath path    = new SVGPath();
    private Line lineaY     = new Line();
    private Line lineaX     = new Line();
    
    private static Label lenguajes = new Label("Lenguajes");

    private Text texto10 = new Text("10");
    private Text texto9  = new Text("9");
    private Text texto8  = new Text("8");
    private Text texto7  = new Text("7");
    private Text texto6  = new Text("6");
    private Text texto5  = new Text("5");
    private Text texto4  = new Text("4");
    private Text texto3  = new Text("3");
    private Text texto2  = new Text("2");
    private Text texto1  = new Text("1");
    private Text texto0  = new Text("0");
    
    private Line linea10 = new Line();
    private Line linea9  = new Line();
    private Line linea8  = new Line();
    private Line linea7  = new Line();
    private Line linea6  = new Line();
    private Line linea5  = new Line();
    private Line linea4  = new Line();
    private Line linea3  = new Line();
    private Line linea2  = new Line();
    private Line linea1  = new Line();
    private Line linea0  = new Line();

    private static Circle circleHTML           = new Circle();
    private static Circle circleXML            = new Circle();
    private static Circle circleCSS            = new Circle();
    private static Circle circleJavaScript     = new Circle();
    private static Circle circleJava           = new Circle();
    private static Circle circlePHP            = new Circle();
    private static Circle circleSQL            = new Circle();
    private static Circle circleJSON           = new Circle();
    private static Circle circleARIA           = new Circle();
    private static Circle circleSVG            = new Circle();
    private static Circle circleApacheNetBeans = new Circle();
    private static Circle circleGitAndGitHub   = new Circle();
        
    public StackPane 
    niveles() {
        pane.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        pane.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        
        GridPane.setConstraints(grafico, 1, 0, 1, 1);
        GridPane.setConstraints(lenguajes, 1, 1, 1, 1, HPos.CENTER, VPos.TOP);
        
        path.setContent("M 0 350 l 100 -200 q 50 -100 100 0 q 50 100 100 0 l 100 -200");
        path.setLayoutX(46.0);
        path.setLayoutY(-2.0);
        path.setScaleY(0.64);
        
        lineaY.setLayoutX(43.0);
        lineaY.setLayoutY(20.0);
        lineaY.setEndY(259.0);

        lineaX.setLayoutX(43.0);
        lineaX.setLayoutY(279.0);
        lineaX.setEndX(402.0);
        
        texto10.setLayoutX(14.0);
        texto9.setLayoutX(22.0);
        texto8.setLayoutX(22.0);
        texto7.setLayoutX(22.0);
        texto6.setLayoutX(22.0);
        texto5.setLayoutX(22.0);
        texto4.setLayoutX(22.0);
        texto3.setLayoutX(22.0);
        texto2.setLayoutX(22.0);
        texto1.setLayoutX(22.0);
        texto0.setLayoutX(22.0);
        
        texto10.setLayoutY(20.0);
        texto9.setLayoutY(45.9);
        texto8.setLayoutY(71.8);
        texto7.setLayoutY(97.7);
        texto6.setLayoutY(123.6);
        texto5.setLayoutY(149.5);
        texto4.setLayoutY(175.4);
        texto3.setLayoutY(201.3);
        texto2.setLayoutY(227.2);
        texto1.setLayoutY(253.1);
        texto0.setLayoutY(279.0);
        
        linea10.setStartX(32.0);
        linea9.setStartX(32.0);
        linea8.setStartX(32.0);
        linea7.setStartX(32.0);
        linea6.setStartX(32.0);
        linea5.setStartX(32.0);
        linea4.setStartX(32.0);
        linea3.setStartX(32.0);
        linea2.setStartX(32.0);
        linea1.setStartX(32.0);
        linea0.setStartX(32.0);
        
        linea10.setEndX(42.0);
        linea9.setEndX(42.0);
        linea8.setEndX(42.0);
        linea7.setEndX(42.0);
        linea6.setEndX(42.0);
        linea5.setEndX(42.0);
        linea4.setEndX(42.0);
        linea3.setEndX(42.0);
        linea2.setEndX(42.0);
        linea1.setEndX(42.0);
        linea0.setEndX(42.0);
        
        linea10.setLayoutY(20.0);
        linea9.setLayoutY(45.9);
        linea8.setLayoutY(71.8);
        linea7.setLayoutY(97.7);
        linea6.setLayoutY(123.6);
        linea5.setLayoutY(149.5);
        linea4.setLayoutY(175.4);
        linea3.setLayoutY(201.3);
        linea2.setLayoutY(227.2);
        linea1.setLayoutY(253.1);
        linea0.setLayoutY(279.0);
        
        circleHTML.setRadius(6);
        circleXML.setRadius(6);
        circleCSS.setRadius(6);
        circleJavaScript.setRadius(6);
        circleJava.setRadius(6);
        circlePHP.setRadius(6);
        circleSQL.setRadius(6);
        circleJSON.setRadius(6);
        circleARIA.setRadius(6);
        circleSVG.setRadius(6);
        circleApacheNetBeans.setRadius(6);
        circleGitAndGitHub.setRadius(6);
        
        circleHTML.setLayoutX(430.0);
        circleXML.setLayoutX(110.0);
        circleCSS.setLayoutX(422.0);
        circleJavaScript.setLayoutX(386.0);
        circleJava.setLayoutX(364.0);
        circlePHP.setLayoutX(375.0);
        circleSQL.setLayoutX(406.0);
        circleJSON.setLayoutX(396.0);
        circleARIA.setLayoutX(414.0);
        circleSVG.setLayoutX(324.0);
        circleApacheNetBeans.setLayoutX(130.0);
        circleGitAndGitHub.setLayoutX(152.0);
        
        circleHTML.setLayoutY(40.0);
        circleXML.setLayoutY(194.0);
        circleCSS.setLayoutY(50.0);
        circleJavaScript.setLayoutY(98.0);
        circleJava.setLayoutY(124.0);
        circlePHP.setLayoutY(111.0);
        circleSQL.setLayoutY(72.0);
        circleJSON.setLayoutY(84.0);
        circleARIA.setLayoutY(62.0);
        circleSVG.setLayoutY(170.0);
        circleApacheNetBeans.setLayoutY(168.0);
        circleGitAndGitHub.setLayoutY(140.0);
        
        circleHTML.setVisible(false);
        circleXML.setVisible(false);
        circleCSS.setVisible(false);
        circleJavaScript.setVisible(false);
        circleJava.setVisible(false);
        circlePHP.setVisible(false);
        circleSQL.setVisible(false);
        circleJSON.setVisible(false);
        circleARIA.setVisible(false);
        circleSVG.setVisible(false);
        circleApacheNetBeans.setVisible(false);
        circleGitAndGitHub.setVisible(false);
        
        grafico.getChildren().addAll(
            path, lineaY, lineaX, 
            texto0, texto1, texto2, texto3, texto4, texto5, texto6, texto7, texto8, texto9, texto10,
            linea10, linea9, linea8, linea7, linea6, linea5, linea4, linea3, linea2, linea1, linea0,
            circleHTML, circleXML, circleCSS, circleJavaScript, circleJava, circlePHP, circleSQL, circleJSON, circleARIA, 
            circleSVG, circleApacheNetBeans, circleGitAndGitHub, 
            lenguajes
        );
        gridPane.getChildren().addAll(
            grafico, 
            lenguajes
        );
        pane.getChildren().addAll(gridPane);
        center.getChildren().add(pane);

        pane.getStylesheets().add("/i/css/center.css");
        pane.getStyleClass().add("niveles");
        gridPane.getStyleClass().add("grid-pane");
        path.getStyleClass().add("path");
        lineaX.getStyleClass().add("line");
        lineaY.getStyleClass().add("line");
        texto10.getStyleClass().add("text");
        texto9.getStyleClass().add("text");
        texto8.getStyleClass().add("text");
        texto7.getStyleClass().add("text");
        texto6.getStyleClass().add("text");
        texto5.getStyleClass().add("text");
        texto4.getStyleClass().add("text");
        texto3.getStyleClass().add("text");
        texto2.getStyleClass().add("text");
        texto1.getStyleClass().add("text");
        texto0.getStyleClass().add("text");
        linea10.getStyleClass().add("line");
        linea9.getStyleClass().add("line");
        linea8.getStyleClass().add("line");
        linea7.getStyleClass().add("line");
        linea6.getStyleClass().add("line");
        linea5.getStyleClass().add("line");
        linea4.getStyleClass().add("line");
        linea3.getStyleClass().add("line");
        linea2.getStyleClass().add("line");
        linea1.getStyleClass().add("line");
        linea0.getStyleClass().add("line");
        circleHTML.getStyleClass().add("circle");
        circleXML.getStyleClass().add("circle");
        circleCSS.getStyleClass().add("circle");
        circleJavaScript.getStyleClass().add("circle");
        circleJava.getStyleClass().add("circle");
        circlePHP.getStyleClass().add("circle");
        circleSQL.getStyleClass().add("circle");
        circleJSON.getStyleClass().add("circle");
        circleARIA.getStyleClass().add("circle");
        circleSVG.getStyleClass().add("circle");
        circleApacheNetBeans.getStyleClass().add("circle");
        circleGitAndGitHub.getStyleClass().add("circle");
        
        return center;
    }
    public void 
    html() {
        ocultarNiveles();
        circleHTML.setVisible(true);
        lenguajes.setText("HTML");
    }
    public void 
    xml() {
        ocultarNiveles();
        circleXML.setVisible(true);
        lenguajes.setText("XML");
    }
    public void 
    css() {
        ocultarNiveles();
        circleCSS.setVisible(true);
        lenguajes.setText("CSS");
    }
    public void 
    javascript() {
        ocultarNiveles();
        circleJavaScript.setVisible(true);
        lenguajes.setText("JavaScript y Node.js");
    }
    public void 
    java() {
        ocultarNiveles();
        circleJava.setVisible(true);
        lenguajes.setText("Java SE y JavaFX");
    }
    public void 
    php() {
        ocultarNiveles();
        circlePHP.setVisible(true);
        lenguajes.setText("PHP");
    }
    public void 
    sql() {
        ocultarNiveles();
        circleSQL.setVisible(true);
        lenguajes.setText("SQL y MySQL");
    }
    public void 
    json() {
        ocultarNiveles();
        circleJSON.setVisible(true);
        lenguajes.setText("JSON y MongoDB");
    }
    public void 
    aria() {
        ocultarNiveles();
        circleARIA.setVisible(true);
        lenguajes.setText("ARIA");
    }
    public void 
    svg() {
        ocultarNiveles();
        circleSVG.setVisible(true);
        lenguajes.setText("SVG Vanilla y SVG con editores");
    }
    public void 
    apacheNetBeans() {
        ocultarNiveles();
        circleApacheNetBeans.setVisible(true);
        lenguajes.setText("Apache NetBeans");
    }
    public void 
    gitAndGitHub() {
        ocultarNiveles();
        circleGitAndGitHub.setVisible(true);
        lenguajes.setText("Git y GitHub con Apache NetBeans");
    }
    public void
    ocultarNiveles() {
        circleHTML.setVisible(false);
        circleXML.setVisible(false);
        circleCSS.setVisible(false);
        circleJavaScript.setVisible(false);
        circleJava.setVisible(false);
        circlePHP.setVisible(false);
        circleSQL.setVisible(false);
        circleJSON.setVisible(false);
        circleARIA.setVisible(false);
        circleSVG.setVisible(false);
        circleApacheNetBeans.setVisible(false);
        circleGitAndGitHub.setVisible(false);
    }
}
