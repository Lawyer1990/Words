module com.example.englishwords {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.apache.log4j;
    requires lombok;
    requires slf4j.api;

    opens com.example.englishwords to javafx.fxml;
    exports com.example.englishwords;
}