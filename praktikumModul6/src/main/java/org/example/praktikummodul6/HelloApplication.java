package org.example.praktikummodul6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage primaryStage;
    private Scene loginScene;
    private Scene inputDataScene;
    private Scene inputAdminScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Library System");

        // Create login scene
        GridPane loginGrid = createLoginGrid();
        loginScene = new Scene(loginGrid, 300, 200);

        // Create input data scene
        VBox inputDataVBox = createInputDataVBox();
        inputDataScene = new Scene(inputDataVBox, 300, 200);

        VBox inputAdminVBox = createInputAdminVBox();
        inputAdminScene = new Scene(inputAdminVBox, 300, 200);

        // Set login scene as the initial scene
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private GridPane createLoginGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label titleLabel = new Label("Library System");
        GridPane.setConstraints(titleLabel, 1, 0);

        Button studentButton = new Button("Login as Student");
        GridPane.setConstraints(studentButton, 1, 1);

        Button adminButton = new Button("Login as Admin");
        GridPane.setConstraints(adminButton, 1, 2);

        Button exitButton = new Button("Exit");
        GridPane.setConstraints(exitButton, 1, 3);

        studentButton.setOnAction(e -> primaryStage.setScene(inputDataScene));
        adminButton.setOnAction(e -> primaryStage.setScene(inputAdminScene));

        grid.getChildren().addAll(titleLabel, studentButton, adminButton, exitButton);
        return grid;
    }


    private VBox createInputAdminVBox() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));


        Label titleLabel = new Label("Username");
        TextField dataField = new TextField();
        Label passLabel = new Label("Password");
        TextField datpassField = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction((e) -> {
            String username = dataField.getText();
            String password = datpassField.getText();
            if (username.equals("admin") && password.equals("admin")) {
                this.showWelcomePage(primaryStage, username);
            } else {
                this.showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect username or password");
            }

        });


        vbox.getChildren().addAll(titleLabel, dataField,passLabel,datpassField,  submitButton);

        submitButton.setOnAction(e -> {
            // Handle submit button action, e.g., save data or process input
            // After processing, you may switch back to the login scene if needed
            primaryStage.setScene(loginScene);
        });

        return vbox;
    }

    private void showWelcomePage(Stage primaryStage, String username) {
    }

    private void showAlert(Alert.AlertType alertType, String loginFailed, String incorrectUsernameOrPassword) {

    }

    private VBox createInputDataVBox() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));


        Label titleLabel = new Label("Masukkan NIM: ");
        TextField dataField = new TextField();
        Button submitButton = new Button("Submit");

        vbox.getChildren().addAll(titleLabel, dataField, submitButton);

        submitButton.setOnAction(e -> {
            // Handle submit button action, e.g., save data or process input
            // After processing, you may switch back to the login scene if needed
            primaryStage.setScene(loginScene);
        });

        return vbox;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
