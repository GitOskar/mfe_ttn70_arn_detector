package pl.oscar.mfe_ttn70_arn_detector.model.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@Slf4j
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

    public static void createDirAndSaveFiles(String basePath, Collection<File> files) {
        LocalDateTime now = LocalDateTime.now();
        String dirName = CONST_DIR_NAME + now.format(FILE_DATE_TIME_FORMATTER);
        Path path = Path.of(basePath);
        File dir = path.resolve(dirName).toFile();

        if (!dir.mkdirs()) {
            log.error("Dir {} cannot be created", dirName);
            throw new UnsupportedOperationException("Dir: " + dirName + " cannot be created");
        }

        Path dirPath = dir.toPath();

        files.forEach(src -> {
            try {
                Files.copy(src.toPath(), dirPath.resolve(src.getName()));
            } catch (IOException e) {
                log.error("Error occurred during coping file: {}", src.getName(), e);
            }
        });
    }
}
