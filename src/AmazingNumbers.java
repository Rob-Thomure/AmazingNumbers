import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AmazingNumbers {
    List<AmazingNumber> amazingNumbers;

    public AmazingNumbers(int num) {
        this.amazingNumbers = new ArrayList<>();
        amazingNumbers.add(new AmazingNumber(num));
    }

    public AmazingNumbers(int num, int quantityOfAmazingNumbers) {
        this.amazingNumbers = new ArrayList<>();
        while (amazingNumbers.size() < quantityOfAmazingNumbers) {
            amazingNumbers.add(new AmazingNumber(num));
            num++;
        }
    }

    public AmazingNumbers(int num, int quantityOfAmazingNumbers, List<String> parameters) {
        this.amazingNumbers = new ArrayList<>();
        while (amazingNumbers.size() < quantityOfAmazingNumbers) {
            AmazingNumber amazingNumber = new AmazingNumber(num);
            Map<String, Boolean> amazingNumberProperties = amazingNumber.getProperties();
            List<Boolean> results = new ArrayList<>();
            for (String parameter : parameters) {
                results.add(amazingNumberProperties.get(parameter));
            }
            if (results.stream().allMatch(e -> e)) {
                amazingNumbers.add(amazingNumber);
            }
            num++;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (AmazingNumber amazingNumber : amazingNumbers) {
            string.append(amazingNumber.toString()).append("\n");
        }
        return string.toString();
    }

    public void printAmazingNumbers() {
        if (amazingNumbers.size() == 1) {
            printSingleNumProperties();
        } else printMultiNumProperties();
    }

    private void printSingleNumProperties() {
        Map<String, Boolean> properties = amazingNumbers.get(0).getProperties();
        int num = amazingNumbers.get(0).getNum();
        System.out.printf("Properties of %,d\n", num);
        properties.forEach((key, value) -> System.out.printf("%14s: %b\n", key, value));
        System.out.println();
    }

    private void printPropertyList(AmazingNumber amazingNumber) {
        Map<String, Boolean> properties = amazingNumber.getProperties();
        String propertyString = properties.entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
        System.out.printf("   %,d is %s\n", amazingNumber.getNum(), propertyString);
    }

    private void printMultiNumProperties() {
        amazingNumbers.forEach(this::printPropertyList);
        System.out.println();
    }
}
