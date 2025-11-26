package streamsApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Find word from Given String Which Not have Vowel Using Java8 Streams API */

public class FindWordWithoutVowelFromGivenString {

    public static void main(String[] args) {
        String input = "Bob is good Software Developer having Skills like Java and SQL";
        List<String> result = Arrays.stream(input.split(" ")).filter(item -> item.chars().noneMatch(character -> "aeiouAEIOU".indexOf(character)!=-1)).collect(Collectors.toList());
        System.out.println(result);
    }
}
