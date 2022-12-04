module pl.oscar.mfe_ttn70_arn_detector {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires lombok;
    requires org.slf4j;

    opens pl.oscar.mfe_ttn70_arn_detector to javafx.fxml;
    exports pl.oscar.mfe_ttn70_arn_detector;
}