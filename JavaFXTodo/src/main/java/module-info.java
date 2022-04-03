module a8.jflores.javafxtodo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens a8.jflores.javafxtodo to javafx.fxml;
    exports a8.jflores.javafxtodo;
}