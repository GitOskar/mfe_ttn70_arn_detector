module pl.oscar.mfe_ttn70_arn_detector {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires lombok;
    requires org.slf4j;
    opens pl.oscar.mfe_ttn70_arn_detector to javafx.fxml;
    exports pl.oscar.mfe_ttn70_arn_detector;
}