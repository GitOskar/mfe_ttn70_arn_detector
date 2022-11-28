package pl.oscar.mfe_ttn70_arn_detector.controller;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ArnViewGenerator {

    public static String prepareArnsForView(List<String> arns) {
        return arns.stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
