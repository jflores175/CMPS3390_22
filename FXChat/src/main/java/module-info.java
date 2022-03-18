module a6.jflores.fxchat.fxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens a6.jflores.fxchat.fxchat to javafx.fxml;
    exports a6.jflores.fxchat.fxchat;
}