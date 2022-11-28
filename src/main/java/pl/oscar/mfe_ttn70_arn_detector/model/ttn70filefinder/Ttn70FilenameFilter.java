package pl.oscar.mfe_ttn70_arn_detector.model.ttn70filefinder;

import java.io.File;
import java.io.FilenameFilter;

public class Ttn70FilenameFilter implements FilenameFilter {

    private static final String TTN70_BEGIN_NAME = "TTN70";
    private static final String TTN70_FILE_EXTENSION = ".001";

    @Override
    public boolean accept(File dir, String name) {
        return name.startsWith(TTN70_BEGIN_NAME) && name.endsWith(TTN70_FILE_EXTENSION);
    }
}
