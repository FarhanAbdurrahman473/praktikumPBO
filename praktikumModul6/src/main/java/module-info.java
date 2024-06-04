module org.example.praktikummodul6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.praktikummodul6 to javafx.fxml;
    exports org.example.praktikummodul6;
}