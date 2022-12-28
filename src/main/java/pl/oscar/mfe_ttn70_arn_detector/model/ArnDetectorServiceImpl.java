package pl.oscar.mfe_ttn70_arn_detector.model;

import lombok.extern.slf4j.Slf4j;
import pl.oscar.mfe_ttn70_arn_detector.model.utils.FileUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.oscar.mfe_ttn70_arn_detector.model.ttn70filefinder.Ttn70FileFinder.findTtn70FilesInDir;
import static pl.oscar.mfe_ttn70_arn_detector.model.utils.FileUtils.getFileNames;
import static pl.oscar.mfe_ttn70_arn_detector.model.utils.Ttn70FileUtils.createDirAndSaveFiles;
import static pl.oscar.mfe_ttn70_arn_detector.model.utils.Ttn70FileUtils.extractArnFromLine;

@Slf4j
public class ArnDetectorServiceImpl implements ArnDetectorService {

    @Override
    public synchronized List<String> recognizeArns(String input) {
        return ArnRecognizer.recognizeArns(input);
    }

    @Override
    public List<String> findTtn70FilesByArns(String pathToTtnFiles, List<String> arns) {
        List<File> ttn70Files = findTtn70FilesInDir(pathToTtnFiles);
        Set<File> ttn70FilesWithArns = new HashSet<>();

        for (File file : ttn70Files) {
            List<String> lines = FileUtils.readAllLines(file);
            for (String line : lines) {
                String arn = extractArnFromLine(line);
                if (arns.contains(arn)) {
                    log.info("File: {} contains ARN: {}", file.getName(), arn);
                    ttn70FilesWithArns.add(file);
                    arns.remove(arn);
                }
            }
        }
        createDirAndSaveFiles(pathToTtnFiles, ttn70FilesWithArns);

        return getFileNames(ttn70FilesWithArns);
    }
}
