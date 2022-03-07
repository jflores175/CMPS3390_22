module a4.jflores.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;

    opens a4.jflores.weather to javafx.fxml;
    exports a4.jflores.weather;
}