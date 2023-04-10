import java.util.Arrays;
import java.util.List;

public enum ValidProperties {
    EVEN("EVEN"),
    NOT_EVEN("-EVEN"),
    ODD("ODD"),
    NOT_ODD("-ODD"),
    BUZZ("BUZZ"),
    NOT_BUZZ("-BUZZ"),
    DUCK("DUCK"),
    NOT_DUCK("-DUCK"),
    PALINDROMIC("PALINDROMIC"),
    NOT_PALINDROMIC("-PALINDROMIC"),
    GAPFUL("GAPFUL"),
    NOT_GAPFUL("-GAPFUL"),
    SPY("SPY"),
    NOT_SPY("-SPY"),
    SQUARE("SQUARE"),
    NOT_SQUARE("-SQUARE"),
    SUNNY("SUNNY"),
    NOT_SUNNY("-SUNNY"),
    JUMPING("JUMPING"),
    NOT_JUMPING("-JUMPING"),
    HAPPY("HAPPY"),
    NOT_HAPPY("-HAPPY"),
    SAD("SAD"),
    NOT_SAD("-SAD");

    public String validProperty;

    ValidProperties(String validProperty) {
        this.validProperty = validProperty;
    }

    public static List<String> getStringValues() {
        return Arrays.stream(ValidProperties.values())
                .map(element -> element.validProperty)
                .toList();
    }

    public static boolean contains(String property) {
        List<String> stringValues = getStringValues();
        return stringValues.contains(property.toUpperCase());
    }

    public static boolean containsAll(List<String> properties) {
        List<String> validPropertyValues = getStringValues();
        List<String> capitalProperties = properties.stream()
                .map(String::toUpperCase)
                .toList();
        return validPropertyValues.containsAll(capitalProperties);

    }
}
