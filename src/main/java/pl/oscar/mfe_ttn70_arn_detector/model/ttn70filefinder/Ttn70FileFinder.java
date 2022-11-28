package pl.oscar.mfe_ttn70_arn_detector.model.ttn70filefinder;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

@UtilityClass
public class Ttn70FileFinder {

    private static final FilenameFilter FILENAME_FILTER = new Ttn70FilenameFilter();

    public static List<File> findTtn70FilesInCurrentDir() {
        File currentDir = new File(".");
        File[] ttn70Files = Optional.ofNullable(currentDir.listFiles(FILENAME_FILTER)).orElseGet(() -> new File[]{});
        return stream(ttn70Files).filter(File::isFile).collect(toCollection(ArrayList::new));
    }
}
