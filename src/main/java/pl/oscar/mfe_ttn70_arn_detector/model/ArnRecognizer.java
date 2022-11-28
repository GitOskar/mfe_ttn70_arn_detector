package pl.oscar.mfe_ttn70_arn_detector.model;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toCollection;

@UtilityClass
public class ArnRecognizer {

    private static final String ARN_SEPARATOR_REGEXP = "\n|,| |\t|" + System.lineSeparator();
    private static final String ARN_REGEXP = "\\d{23}";

    public static List<String> recognizeArns(String input) {
        return stream(input.split(ARN_SEPARATOR_REGEXP))
                .filter(not(String::isBlank))
                .filter(ArnRecognizer::isCorrectArn)
                .distinct()
                .collect(toCollection(ArrayList::new));
    }

    private static boolean isCorrectArn(String arn) {
        return arn.matches(ARN_REGEXP);
    }
}
