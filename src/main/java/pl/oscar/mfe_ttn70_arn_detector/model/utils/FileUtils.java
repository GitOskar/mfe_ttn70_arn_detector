package pl.oscar.mfe_ttn70_arn_detector.model.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.util.stream.Collectors.toCollection;

@Slf4j
@UtilityClass
public class FileUtils {

    public static List<String> readAllLines(File file) {
        try {
            return Files.readAllLines(file.toPath(), ISO_8859_1);
        } catch (IOException e) {
            log.error("Error occurred during reading lines from file: {}", file, e);
            return new ArrayList<>();
        }
    }

    public static List<String> getFileNames(Collection<File> files) {
        return files.stream().map(File::getName).collect(toCollection(ArrayList::new));
    }
}
