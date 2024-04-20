package javafx;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Left extends JavaFX {
    public Center nivel = new Center();
    public VBox 
    izquierda() {
        VBox vbox = new VBox();
        
        Button html             = new Button("HTML");
        Button xml              = new Button();
        Button css              = new Button("CSS");
        Button javascript       = new Button("JavaScript");
        Button java             = new Button("Java");
        Button php              = new Button("PHP");
        Button sql              = new Button("SQL");
        Button json             = new Button("JSON");
        Button aria             = new Button("ARIA");
        Button svg              = new Button("SVG");
        Button apacheNetBeans   = new Button("Apache NetBeans");
        Button gitAndGitHub     = new Button("Git y GitHub");
        
//        Text createIconHTML = GlyphsDude.createIcon(FontAwesomeIcons.ANGELLIST);
//        createIconHTML.setFill(Color.WHITE);
//        html.setGraphic(createIconHTML);
        Text createIcon = GlyphsDude.createIcon(FontAwesomeIcons.JSFIDDLE, "1.5em");
        createIcon.setFill(Color.WHITE);
        xml.setGraphic(createIcon);
//        Text createIconCSS = .createIcon(MaterialDesignIcon.HELP, "1.5em");
//        createIconCSS.setFill(Color.WHITE);
//        css.setGraphic(createIconCSS);

//        Text weat = WeatherIconView(WeatherIcon);
        
        html.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.html();
            }
        });
        xml.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.xml();
            }
        });
        css.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.css();
            }
        });
        javascript.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.javascript();
            }
        });
        java.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.java();
            }
        });
        php.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.php();
            }
        });
        sql.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.sql();
            }
        });
        json.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.json();
            }
        });
        aria.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.aria();
            }
        });
        svg.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.svg();
            }
        });
        apacheNetBeans.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.apacheNetBeans();
            }
        });
        gitAndGitHub.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void 
            handle(MouseEvent event) {
                nivel.gitAndGitHub();
            }
        });
        vbox.getChildren().addAll(html, xml, css, javascript, java, php, sql, json, aria, svg, apacheNetBeans, gitAndGitHub);
        left.getChildren().add(vbox);
        
        left.getStylesheets().add("/i/css/left.css");
        vbox.setId("skills");
        
        return left;
    }
}
