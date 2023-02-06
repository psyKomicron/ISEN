module com.psy.rockfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.psy.rockfx to javafx.fxml;
    exports com.psy.rockfx;
    exports com.psy.rockfx.calculator;
    opens com.psy.rockfx.calculator to javafx.fxml;
}