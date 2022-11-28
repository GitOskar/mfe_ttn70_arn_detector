package pl.oscar.mfe_ttn70_arn_detector.model;

import java.util.List;

public interface ArnDetectorService {

    List<String> recognizeArns(String input);

    /**
     * Method finds and returns ttn70 files when arn was occurred at least 1 time. If any file was found new dir will be created with copy of these files
     * @param arns - arns to find in files
     * @return list of files where any ARN was occurred
     */
    List<String> findTtn70FilesByArns(List<String> arns);
}
