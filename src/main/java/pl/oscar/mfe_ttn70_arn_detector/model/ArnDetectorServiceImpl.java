package pl.oscar.mfe_ttn70_arn_detector.model;

import pl.oscar.mfe_ttn70_arn_detector.model.utils.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.oscar.mfe_ttn70_arn_detector.model.ttn70filefinder.Ttn70FileFinder.findTtn70FilesInCurrentDir;
import static pl.oscar.mfe_ttn70_arn_detector.model.utils.FileUtils.getFileNames;
import static pl.oscar.mfe_ttn70_arn_detector.model.utils.Ttn70FileUtils.createDirAndSaveFiles;
import static pl.oscar.mfe_ttn70_arn_detector.model.utils.Ttn70FileUtils.extractArnFromLine;

public class ArnDetectorServiceImpl implements ArnDetectorService {

    @Override
    public synchronized List<String> recognizeArns(String input) {
        return ArnRecognizer.recognizeArns(input);
    }

    @Override
    public List<String> findTtn70FilesByArns(List<String> arns) {
        List<File> ttn70Files = findTtn70FilesInCurrentDir();
        Set<File> ttn70FilesWithArns = new HashSet<>();

        for (File file : ttn70Files) {
            List<String> lines = FileUtils.readAllLines(file);
            for (String line : lines) {
                String arn = extractArnFromLine(line);
                if (arns.contains(arn)) {
                    System.out.println("File: " + file.getName() + " contains ARN: " + arn);
                    ttn70FilesWithArns.add(file);
                    arns.remove(arn);
                }
            }
        }

        createDirAndSaveFiles(ttn70FilesWithArns);

        return getFileNames(ttn70FilesWithArns);
    }


}