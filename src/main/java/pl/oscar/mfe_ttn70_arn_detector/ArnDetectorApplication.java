package pl.oscar.mfe_ttn70_arn_detector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ArnDetectorApplication extends Application {

    private static final String TITLE = "MFE TTN 70 ARN Detector";
    private static final String VIEW_RESOURCE_PATH = "view.fxml";
    private static final String LOGO_PATH = "images/logo.png";

    @Override
    public void start(Stage stage) throws IOException {
        setupStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    private void setupStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(VIEW_RESOURCE_PATH));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(TITLE);
        setupLogo(stage);
        stage.setScene(scene);
        stage.show();
    }

    private void setupLogo(Stage stage) {
        Image logo = new Image(getClass().getResourceAsStream(LOGO_PATH));
        stage.getIcons().add(logo);
    }
}