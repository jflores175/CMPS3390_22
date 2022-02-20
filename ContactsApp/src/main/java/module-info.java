module a3.jflores.contactsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens a3.jflores.contactsapp to javafx.fxml;
    exports a3.jflores.contactsapp;
}