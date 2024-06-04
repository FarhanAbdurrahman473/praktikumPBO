package org.example.praktikummodul6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

public class HelloController {
    private static final String CORRECT_USERNAME = "farhan";
    private static final String CORRECT_PASSWORD = "farhanaja";

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    protected void loginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
            // Jika login sukses, tampilkan pesan dan pindah ke halaman baru
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username);
            // Menampilkan halaman baru
            ((Button) event.getSource()).getScene().setRoot(new StackPane(new Label("Login berhasil, selamat datang " + username)));
        } else {
            // Jika login gagal, tampilkan pesan kesalahan
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect username or password");
        }
    }

    // Method untuk menampilkan pesan
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
