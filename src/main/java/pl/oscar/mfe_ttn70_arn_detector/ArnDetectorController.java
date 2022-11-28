package pl.oscar.mfe_ttn70_arn_detector;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.oscar.mfe_ttn70_arn_detector.model.ArnDetectorService;
import pl.oscar.mfe_ttn70_arn_detector.model.ArnDetectorServiceImpl;

import java.util.List;

import static pl.oscar.mfe_ttn70_arn_detector.controller.ArnViewGenerator.prepareArnsForView;
import static pl.oscar.mfe_ttn70_arn_detector.controller.Ttn70FilesViewGenerator.prepareTtn70ForView;

public class ArnDetectorController {

    private final ArnDetectorService arnDetectorService = new ArnDetectorServiceImpl();

    @FXML
    private TextArea taInput;

    @FXML
    private TextArea taRecognizedArns;

    @FXML
    private TextArea taTtn70Files;

    @FXML
    protected void findTtnsBtnClick() {
        System.out.println("Find TTN70 method call.");
        List<String> recognizedArns = refreshArnBtnClick();
        List<String> ttn70FilesByArns = arnDetectorService.findTtn70FilesByArns(recognizedArns);
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
}