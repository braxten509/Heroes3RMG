module org.example.heroes3rmg {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.heroes3rmg to javafx.fxml;
    exports org.example.heroes3rmg;
    exports org.example.heroes3rmg.records;
    opens org.example.heroes3rmg.records to javafx.fxml;
}