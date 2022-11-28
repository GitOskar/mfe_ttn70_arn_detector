package pl.oscar.mfe_ttn70_arn_detector.model.utils;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@UtilityClass
public class FileUtils {

    public static List<String> readAllLines(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("Error occurred during reading lines from file: " + file);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<String> getFileNames(Collection<File> files) {
        return files.stream().map(File::getName).collect(toCollection(ArrayList::new));
    }
}
