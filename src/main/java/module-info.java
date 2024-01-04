module per.amazondb.database_connection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens per.amazondb.database_connection to javafx.fxml;
    exports per.amazondb.database_connection;
}