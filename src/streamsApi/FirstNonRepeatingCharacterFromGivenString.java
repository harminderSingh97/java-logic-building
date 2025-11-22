package streamsApi;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**  Find the First Non-Repeating Character in Given String ,
    and Logic should be Dynamic not according to static string input */

public class FirstNonRepeatingCharacterFromGivenString {
    public static void main(String[] args) {
        String input = "I am a Professional Java Developer";
        input.chars().mapToObj(characrter -> (char) characrter)
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                )
                .entrySet()
                .stream()
                .filter(characterValue-> characterValue.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
