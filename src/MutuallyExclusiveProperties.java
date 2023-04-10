import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MutuallyExclusiveProperties {
    EVEN_ODD(List.of("EVEN", "ODD")),
    NOT_EVEN_NOT_ODD(List.of("-EVEN", "-ODD")),
    DUCK_SPY(List.of("DUCK", "SPY")),
    SUNNY_SQUARE(List.of("SUNNY", "SQUARE")),
    SAD_HAPPY(List.of("SAD", "HAPPY")),
    EVEN_NOT_EVEN(List.of("EVEN", "-EVEN")),
    ODD_NOT_ODD(List.of("ODD", "-ODD")),
    DUCK_NOT_DUCK(List.of("DUCK", "-DUCK")),
    SPY_NOT_SPY(List.of("SPY", "-SPY")),
    SUNNY_NOT_SUNNY(List.of("SUNNY", "-SUNNY")),
    SQUARE_NOT_SQUARE(List.of("SQUARE", "-SQUARE")),
    SAD_NOT_SAD(List.of("SAD", "-SAD")),
    HAPPY_NOT_HAPPY(List.of("HAPPY", "-HAPPY"));

    public List<String> mutuallyExclusiveValue;

    MutuallyExclusiveProperties(List<String> mutuallyExclusiveValue) {
        this.mutuallyExclusiveValue =mutuallyExclusiveValue;
    }

    public static List<List<String>> getMutuallyExclusivePropertiesList() {
        return Arrays.stream(MutuallyExclusiveProperties.values())
                .map(element -> element.mutuallyExclusiveValue)
                .toList();
    }

    public static boolean isMutuallyExclusiveProperties(List<String> properties) {
        List<String> capitalProperties = properties
                .stream().
                map(str -> str.toUpperCase())
                .collect(Collectors.toList());
        for (var mutuallyExclusive : getMutuallyExclusivePropertiesList()) {
            if (capitalProperties.containsAll(mutuallyExclusive)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getMatchingMutuallyExclusiveProperties(List<String> properties) {
        List<String> capitalProperties = properties.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> matchingProperties = getMutuallyExclusivePropertiesList().stream()
                .filter(element -> capitalProperties.containsAll(element))
                .flatMap(e -> e.stream())
                .collect(Collectors.toList());
        return capitalProperties.stream()
                .filter(element -> matchingProperties.contains(element))
                .toList();
    }



}
