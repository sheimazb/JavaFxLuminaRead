module com.melocode.lread {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens com.melocode.lread to javafx.fxml;
    exports com.melocode.lread;
}