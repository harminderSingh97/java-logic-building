package streamsApi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create different groups for one type of anagrams from given Single List
 */

public class GroupAnagramFromList {
    public static void main(String[] args) {
        List<String> data = List.of("cat", "act", "listen", "silent", "dog", "eat", "tea");
        Map<String, List<String>> anagramsGroups = data.stream().collect(
                Collectors.groupingBy(word -> {
                    return word.chars()
                            .sorted()
                            .collect(
                                    StringBuilder::new,
                                    (
                                            (stringBuilder, character) -> {
                                                stringBuilder.append((char) character);
                                            }
                                    ),
                                    StringBuilder::append
                            )
                            .toString();
                })
        );
        System.out.println("AnagramGroup List: "+anagramsGroups);
        anagramsGroups.forEach((key, value) ->
                System.out.println("Key: " + key + " -> Anagrams: " + value)
        );

    }





























}
