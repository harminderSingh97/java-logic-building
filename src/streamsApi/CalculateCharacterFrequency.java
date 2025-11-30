package streamsApi;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CalculateCharacterFrequency {
    public static void main(String[] args) {
        String str = "I am a Professional JAVA Developer";
        str.chars().mapToObj(character-> (char)character ).collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        ).forEach((character, count )-> {
            System.out.println("Character: "+ character+ " frequency: "+ count);
        });
    }
}
