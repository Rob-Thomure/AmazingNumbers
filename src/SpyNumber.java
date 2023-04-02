import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SpyNumber {

    private static Function<Long, Long> sum1 = num -> {
        List<Long> numList = new ArrayList<>();
        long newNum = num;
        while (newNum > 0) {
            long digit = newNum % 10;
            newNum /= 10;
            numList.add(digit);
        }

        return numList.stream()
                .collect(Collectors.summingLong(Long::longValue));
    };


    public static void main(String[] args) {

        Long sum =Arrays.stream(Long.toString(123L).split(""))
                .mapToLong(Long::parseLong)
                .sum();

        Long product = Arrays.stream(Long.toString(1234L).split(""))
                .mapToLong(Long::parseLong)
                .reduce(1, (a, b) -> a * b);

        System.out.println(sum);
        System.out.println(product);



    }

}
