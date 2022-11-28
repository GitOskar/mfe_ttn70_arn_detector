package pl.oscar.mfe_ttn70_arn_detector.controller;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class Ttn70FilesViewGenerator {

    public static String prepareTtn70ForView(List<String> arns) {
        return arns.stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
