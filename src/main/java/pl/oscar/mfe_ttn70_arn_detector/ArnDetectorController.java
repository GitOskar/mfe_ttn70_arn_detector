package pl.oscar.mfe_ttn70_arn_detector;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pl.oscar.mfe_ttn70_arn_detector.model.ArnDetectorService;
import pl.oscar.mfe_ttn70_arn_detector.model.ArnDetectorServiceImpl;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static pl.oscar.mfe_ttn70_arn_detector.controller.ArnViewGenerator.prepareArnsForView;
import static pl.oscar.mfe_ttn70_arn_detector.controller.Ttn70FilesViewGenerator.prepareTtn70ForView;

public class ArnDetectorController {

    private static final String PATH_PLACEHOLDER = "Path ...";

    private final ArnDetectorService arnDetectorService = new ArnDetectorServiceImpl();

    @FXML
    private TextArea taInput;

    @FXML
    private TextArea taRecognizedArns;

    @FXML
    private TextArea taTtn70Files;

    @FXML
    private Label pathLabel;

    @FXML
    protected void findTtnsBtnClick() {

        String path = pathLabel.getText();
        if (Objects.equals(PATH_PLACEHOLDER, path)) {
            System.out.println("Path is not set.");
            return;
        }

        System.out.println("Find TTN70 method call.");
        List<String> recognizedArns = refreshArnBtnClick();
        List<String> ttn70FilesByArns = arnDetectorService.findTtn70FilesByArns(path, recognizedArns);
        System.out.println("Funded TTN70 files by ARN: " + ttn70FilesByArns);
        String ttn70ForView = prepareTtn70ForView(ttn70FilesByArns);
        taTtn70Files.setText(ttn70ForView);
    }

    @FXML
    protected synchronized List<String> refreshArnBtnClick() {
        String input = taInput.getText();
        System.out.println("Refresh ARN button input: {}." + input);
        List<String> recognizedArns = arnDetectorService.recognizeArns(input);
        System.out.println("Recognized ARNs: {}" + recognizedArns);
        String recognizedArnsView = prepareArnsForView(recognizedArns);
        taRecognizedArns.setText(recognizedArnsView);
        return recognizedArns;
    }

    @FXML
    protected synchronized void changePath() {
        System.out.println("Change path click");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(new Stage());

        if (isNull(file)) {
            System.out.println("Directory not set");
            return;
        }

        if (!file.isDirectory()) {
            System.out.println("File is not a directory");
            return;
        }
        String absolutePath = file.getAbsolutePath();
        pathLabel.setText(absolutePath);
    }
}