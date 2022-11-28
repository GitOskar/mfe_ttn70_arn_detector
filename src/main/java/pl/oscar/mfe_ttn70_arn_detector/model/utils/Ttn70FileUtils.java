package pl.oscar.mfe_ttn70_arn_detector.model.utils;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@UtilityClass
public class Ttn70FileUtils {

    private static final int ARN_BEGIN_POS = 100;
    private static final int ARN_END_POS = ARN_BEGIN_POS + 23;
    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy_MM_dd'T'HH_mm_ss");
    private static final String CONST_DIR_NAME = "TTN70_to_parse_";

    public static String extractArnFromLine(String line) {
        return Optional.of(line)
                .filter(l -> l.length() >= ARN_END_POS)
                .map(l -> l.substring(ARN_BEGIN_POS, ARN_END_POS))
                .orElse(null);
    }

    public static void createDirAndSaveFiles(Collection<File> files) {
        LocalDateTime now = LocalDateTime.now();
        String dirName = CONST_DIR_NAME + now.format(FILE_DATE_TIME_FORMATTER);
        File dir = new File(dirName);

        if (!dir.mkdirs()) {
            System.out.println("Dir " + dirName + " cannot be created");
            throw new UnsupportedOperationException("Dir: " + dirName + " cannot be created");
        }

        Path dirPath = dir.toPath();

        files.forEach(src -> {
            try {
                Files.copy(src.toPath(), dirPath.resolve(src.getName()));
            } catch (IOException e) {
                System.out.println("Error occurred during coping file: " + src.getName());
            }
        });
    }
}
