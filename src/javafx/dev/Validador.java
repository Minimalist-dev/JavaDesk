package javafx.dev;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.JavaFX;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import l.fx.Check;
import l.fx.Decoration;
import l.fx.TooltipWrapper;
import l.fx.ValidationMessage;
import l.fx.Validator;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Validador extends JavaFX {
    private Validator validator = new Validator();
    private StringBinding problemsText;
    private GridPane grid;

    public StackPane
    validador() {
        grid = createGrid();

        Text sceneTitle = new Text("Bienvenido");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Button signUp = new Button("Sign up");
        TooltipWrapper<Button> signUpWrapper = new TooltipWrapper<>(
            signUp,
            validator.containsErrorsProperty(),
            Bindings.concat("No puedo registrarme:\n", validator.createStringBinding())
        );

        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.getChildren().add(signUpWrapper);
        signUp.setOnAction(e -> signUp());

        TextField userTextField = new TextField();
        validator.createCheck().withMethod(new Consumer<Check.Context>() {
            @Override
            public void accept(Check.Context c) {
                String userName = c.get("username");
                if (!userName.toLowerCase().equals(userName)) {
                    c.error("Por favor use solo letras minúsculas.");
                }
            }
        }).dependsOn("username", userTextField.textProperty())
        .decorates(userTextField)
        .immediate();

        grid.add(new Label("Usuario:"), 0, 1);
        grid.add(userTextField, 1, 1);

        PasswordField password = new PasswordField();
        PasswordField passwordConfirmation = new PasswordField();
        
        validator.createCheck()
            .withMethod(c -> {
                if (!c.get("password").equals(c.get("passwordConfirmation"))) {
                    c.error("Las contraseñas no coinciden.");
                }
            })
            .dependsOn("password", password.textProperty())
            .dependsOn("passwordConfirmation", passwordConfirmation.textProperty())
            .decorates(password)
            .decorates(passwordConfirmation)
            .immediate();
        
        validator.createCheck()
            .withMethod(c -> {
                String pwd = c.get("password");
                if (pwd.length() < 8) {
                    c.warn("La contraseña debe tener al menos 8 caracteres.");
                }
            })
            .dependsOn("password", password.textProperty())
            .decorates(signUp)
            .immediate();
        
        grid.add(new Label("Contraseña:"), 0, 2);
        grid.add(password, 1, 2);
        grid.add(new Label("Confirmar contraseña:"), 0, 3);
        grid.add(passwordConfirmation, 1, 3);

        TextField summand1 = new TextField("17");
        TextField summand2 = new TextField("4");
        TextField sum = new TextField("21");
        Label check = new Label("");
        check.setMinWidth(200);

        validator.createCheck()
            .dependsOn("s1", summand1.textProperty())
            .dependsOn("s2", summand2.textProperty())
            .dependsOn("sum", sum.textProperty())
            .withMethod(this::checkSum)
            .decoratingWith(this::sumDecorator)
            .decorates(check)
            .immediate();

        HBox hb = new HBox(5);
        hb.setAlignment(Pos.BASELINE_LEFT);
        hb.getChildren().addAll(summand1, new Text("+"), summand2, new Text("="), sum, check);
        grid.add(hb, 0, 4, 2, 1);

        TextArea problems = createProblemOutput();
        grid.add(problems, 0, 5, 2, 1);

        grid.add(bottomBox, 1, 7);
        grid.getStylesheets().add("/i/css/dev/validador.css");
        grid.getStyleClass().add("grid-pane");

        borderPane.setCenter(grid);

        return center;
    }

    private void 
    checkSum(Check.Context c) {
        try {
            int s1 = Integer.parseInt(c.get("s1"));
            int s2 = Integer.parseInt(c.get("s2"));
            int sum = Integer.parseInt(c.get("sum"));
            if (s1 + s2 != sum) {
                c.error("la suma debe ser " + (s1 + s2));
            }
        } catch (NumberFormatException e) {
            c.error("formato numérico");
        }
    }
    private Decoration 
    sumDecorator(ValidationMessage m) {
        return new Decoration() {
            @Override
            public void remove(Node target) {
                ((Label) target).setText("OK");
            }

            @Override
            public void add(Node target) {
                ((Label) target).setText("ERROR - " + m.getText());
            }
        };
    }
    private TextArea 
    createProblemOutput() {
        TextArea problems = new TextArea();
        problems.setEditable(false);
        problems.setPrefHeight(80);
        problems.setBackground(Background.EMPTY);
        problems.setFocusTraversable(false);
        problemsText = Bindings.createStringBinding(this::getProblemText, validator.validationResultProperty());
        problems.textProperty().bind(problemsText);
        return problems;
    }
    private String 
    getProblemText() {
        return validator.validationResultProperty().get().getMessages().stream()
            .map(msg -> msg.getSeverity().toString() + ": " + msg.getText())
            .collect(Collectors.joining("\n"));
    }
    private GridPane 
    createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }
    private void
    signUp() {
        grid.getChildren().setAll(new Text("Ahora estás registrado."));

        signUpClicked();
    }
    private void 
    signUpClicked() {
//        private void signUpClicked(ActionEvent e) {
//        boton.seAction(this::signUpClicked);
        if (validator.validate()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Regístrate completo");
            alert.setHeaderText(null);
            alert.setContentText("Te registraste exitosamente");

            alert.showAndWait();
        }
    }
}
