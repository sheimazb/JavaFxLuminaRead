module com.melocode.lread {
    // Needed for JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Needed for HTTP
    requires unirest.java;

    // Needed for JSON
    requires gson;
    requires java.sql;
    requires httpclient;
    requires httpcore;
    requires json;

    // Export packages
    exports com.melocode.lread.controllers;
    exports com.melocode.lread.models;
    exports com.melocode.lread;

    // Open packages to specific modules
    opens com.melocode.lread.controllers to javafx.fxml;
    opens com.melocode.lread to javafx.fxml;
    opens com.melocode.lread.models to gson;
}
