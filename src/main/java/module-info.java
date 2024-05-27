module com.melocode.lread {
    //needed for JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    //needed for HTTP
    requires unirest.java;

    //needed for JSON
    requires gson;
    requires java.sql;
    requires httpclient;
    requires httpcore;
    requires json;
    //needed for JavaFX
    opens com.melocode.lread.controllers to javafx.fxml;

    opens com.melocode.lread to javafx.fxml;
    exports com.melocode.lread;
}